package com.cairone.olingo.ext.demo.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cairone.olingo.ext.demo.edm.enums.GenderEnum;

@Entity @Table(name="people")
public class PersonEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @Column(name="person_id")
	private Integer id = null;
	
	@Column(name="name", nullable=false)
	private String name = null;
	
	@Column(name="surname", nullable=false)
	private String surname = null;
	
	@Column(name="gender", nullable=false)
	private GenderEnum gender = null;
	
	@OneToOne @JoinColumn(name="region_id", nullable=true)
	private RegionEntity region = null;
	
	@OneToOne @JoinColumn(name="form_name", nullable=true)
	private FormEntity form = null;
	
	@Column(name="address_street", nullable=true, length=100)
	private String addressStreet = null;

	@Column(name="address_number", nullable=true, length=10)
	private String addressNumber = null;
	
	@OneToOne @JoinColumn(name="state_id", nullable=true)
	private StateEntity state = null;
	
	@Column(name="birth_date")
	private LocalDate birthDate = null;
	
	public PersonEntity() {}

	public PersonEntity(Integer id, String name, String surname, GenderEnum gender) {
		this(id, name, surname, gender, null, null, null);
	}
	
	public PersonEntity(Integer id, String name, String surname, GenderEnum gender, RegionEntity regionEntity, FormEntity form, LocalDate birthDate) {
		this(id, name, surname, gender, regionEntity, form, null, null, null, birthDate);
	}
	
	public PersonEntity(Integer id, String name, String surname, GenderEnum gender, RegionEntity regionEntity, FormEntity form, String addressStreet, String addressNumber, StateEntity state, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.region = regionEntity;
		this.form = form;
		this.addressStreet = addressStreet;
		this.addressNumber = addressNumber;
		this.state = state;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	public RegionEntity getRegion() {
		return region;
	}

	public void setRegion(RegionEntity region) {
		this.region = region;
	}

	public FormEntity getForm() {
		return form;
	}

	public void setForm(FormEntity form) {
		this.form = form;
	}

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public String getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

	public StateEntity getState() {
		return state;
	}

	public void setState(StateEntity state) {
		this.state = state;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonEntity other = (PersonEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PersonEntity [id=" + id + ", name=" + name + "]";
	}
	
}
