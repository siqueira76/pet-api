package ccom.siqueira76.petapi;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ccom.siqueira76.petapi.model.Cliente;
import ccom.siqueira76.petapi.model.Pet;
import ccom.siqueira76.petapi.repository.ClienteRepository;
import ccom.siqueira76.petapi.repository.PetRepository;

@SpringBootApplication
public class PetApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PetApiApplication.class, args);
	}
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	PetRepository petRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Cliente cli1 = new Cliente(null, "Leonel Messy", "l.messy@barca.com", "9999999");
		cli1.getTelefones().addAll(Arrays.asList("+34 981275982", "+34 874238789"));
		
		Pet pet1 = new Pet(null, "R7", "07", "pudo");
		pet1.setClient(cli1);
		Pet pet2 = new Pet(null, "Ronaldo", "09", "pug");
		pet2.setClient(cli1);
		
		cli1.getPets().addAll(Arrays.asList(pet1, pet2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		petRepository.saveAll(Arrays.asList(pet1, pet2));
		
		
	}

}
