package ccom.siqueira76.petapi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import ccom.siqueira76.petapi.model.Pet;

public class PetNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento Obrigatorio")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;

	@NotEmpty(message = "Preenchimento Obrigatorio")
	private String age;
	
	@NotEmpty(message = "Preenchimento Obrigatorio")
	private String breed;
	
	@NotNull(message = "Preenchimento Obrigatorio")
	private Integer parentId;

	public PetNewDTO() {
	}

	public PetNewDTO(Pet obj) {
		nome = obj.getName();
		age = obj.getAge();
		breed = obj.getBreed();
		parentId = obj.getParents();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

}	