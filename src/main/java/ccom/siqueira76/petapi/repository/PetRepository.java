package ccom.siqueira76.petapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ccom.siqueira76.petapi.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer> {

}
