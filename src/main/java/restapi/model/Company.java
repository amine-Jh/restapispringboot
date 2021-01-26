package restapi.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@DiscriminatorValue("3")


public class Company  extends User {
	
	private String  adresse;
	
	private String type;
	
	private String telephone;
	
	
	
	
	
	@ManyToMany(mappedBy = "companies")
	@JsonIgnoreProperties("companies")
	private Set <Etudiant> etudiants = new HashSet<>(); 
	
	
	
	

	public Set<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(Set<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Company(String name, String password, String email, String username ,String adress, String type , String telephone) {
		super(name, password, email, username);
		// TODO Auto-generated constructor stub
		this.setAdresse(adress);
		this.setType(type);
		this.setTelephone(telephone);
		
		
	}

	
	
}
