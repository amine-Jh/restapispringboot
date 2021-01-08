package restapi.model;


import java.util.Objects;



import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("etudiant")
public class Etudiant extends User {
	
	
	private String filliere;
	
	private String annee;
	
	
	
 
	 
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
