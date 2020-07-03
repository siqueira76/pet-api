package ccom.siqueira76.petapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ccom.siqueira76.petapi.dto.ClienteDTO;
import ccom.siqueira76.petapi.dto.ClienteNewDTO;
import ccom.siqueira76.petapi.model.Cliente;
import ccom.siqueira76.petapi.model.Pet;
import ccom.siqueira76.petapi.repository.ClienteRepository;
import ccom.siqueira76.petapi.repository.PetRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository repo;

	@Autowired
	PetRepository petRepository;
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
	public Cliente findByEmail(String email) {

		Cliente obj = repo.findByEmail(email);
		if (obj == null) {
			return null;
		}

		return obj;
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		Cliente newObj = repo.save(obj);
		obj.getPets().forEach(p -> p.setClient(newObj));
		petRepository.saveAll(obj.getPets());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		Cliente obj = findById(id);
		obj.getPets().forEach(p -> petRepository.deleteById(p.getId()));
		repo.deleteById(id);
	}

	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null);
	}

	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj());
		Pet pet = new Pet(null, objDTO.getPetName(), objDTO.getPetAge(), objDTO.getPetBreed());
		cli.getPets().add(pet);
		cli.getTelefones().add(objDTO.getTelefone1());
		
		if (objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if (objDTO.getTelefone3() != null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		
		return cli;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
