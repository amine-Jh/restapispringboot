package restapi.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("company")
public class Company  extends User {
	
	private String  adresse;
	
	private String type;
	
	private String telephone;

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
