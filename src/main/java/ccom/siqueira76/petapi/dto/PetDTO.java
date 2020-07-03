package ccom.siqueira76.petapi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import ccom.siqueira76.petapi.model.Pet;

public class PetDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento Obrigatorio")
	private String age;

	public PetDTO() {
	}

	public PetDTO(Pet obj) {
		age = obj.getAge();
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}	