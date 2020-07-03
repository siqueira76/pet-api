package ccom.siqueira76.petapi.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ccom.siqueira76.petapi.dto.PetDTO;
import ccom.siqueira76.petapi.dto.PetNewDTO;
import ccom.siqueira76.petapi.model.Cliente;
import ccom.siqueira76.petapi.model.Pet;
import ccom.siqueira76.petapi.repository.PetRepository;

@Service
public class PetService {
	
	@Autowired
	PetRepository repo;
	
	@Autowired
	ClienteService clientService;

	public List<Pet> findAll() {
		List<Pet> obj = repo.findAll();
		return obj;
	}
	
	public Pet findById(Integer id) {
		Optional<Pet> obj = repo.findById(id);
		return obj.orElse(null);
	}

	public List<Pet> findByParentId(Integer id) {
		Cliente cli = clientService.findById(id);
		return cli.getPets();
	}
	
	public Pet insert(Pet obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Pet update(Pet obj) {
		Pet newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		repo.deleteById(id);
	}

	public Pet fromDTO(@Valid PetNewDTO objDTO) {
		Cliente cli = clientService.findById(objDTO.getParentId());
		Pet pet = new Pet(null, objDTO.getNome(), objDTO.getAge(), objDTO.getBreed());
		pet.setClient(cli);
		return pet;
	}

	public Pet fromDTO(@Valid PetDTO objDTO) {
		Pet pet = new Pet(null, null, objDTO.getAge(), null);
		return pet;
	}
	
	private void updateData(Pet newObj, Pet obj) {
		newObj.setAge(obj.getAge());
	}

}
