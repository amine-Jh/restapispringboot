package restapi.model;


import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.ManyToAny;

import java.util.Set;


@Entity
@DiscriminatorValue("2")
public class Etudiant extends User {
	
	
	private String filliere;
	
	private String annee;
	
	@ManyToMany
	@JoinTable(
	  name = "postuler", 
	  joinColumns = @JoinColumn(name = "student_id"), 
	  inverseJoinColumns = @JoinColumn(name = "company_id"))
	  Set<Company> companies;




	public Set<Company> getCompanies() {
		return companies;
	}




	public void setCompanies(Set<Company> companies) {
		this.companies = companies;
	}




	public Etudiant() {}

	


	public Etudiant(String name, String password, String email, String filliere,String username,String annee,String telephone) {
		super(name, password, email,username);
		this.setFilliere(filliere);
		this.setAnnee(annee);
		this.setTelephone(telephone);
		
		// TODO Auto-generated constructor stub
	}
	

	

	public String getFilliere() {
		return filliere;
	}
	public void setFilliere(String filliere) {
		this.filliere = filliere;
	}
	public String getAnnee() {
		return annee;
	}
	public void setAnnee(String annee) {
		this.annee = annee;
	}
	
	  @Override
	  public int hashCode() {
	    return Objects.hash(this.id, this.name, this.password);
	  }

	  @Override
	  public String toString() {
	    return "User{" + "id=" +this.id+ ", name='" + this.annee + '\'' + ", password='" + this.password + '\''+"email"+  this.email+ '}';
	  }

	
}
