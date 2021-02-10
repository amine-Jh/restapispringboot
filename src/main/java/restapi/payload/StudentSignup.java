package restapi.payload;

import java.util.Set;

import restapi.model.PhotoInfo;
public class StudentSignup {

	
	 
    private String username;
    
    private String annee;
    
    private String telephone;
    private String password;
    
    Set<String> roles ;
    private String filliere;
   private String email;
       private String name;
      private PhotoInfo photo;

	public PhotoInfo getPhoto() {
		return photo;
	}

	public void setPhoto(PhotoInfo photo) {
		this.photo = photo;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	
   
   
    public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public String getFilliere() {
		return filliere;
	}

	public void setFilliere(String filliere) {
		this.filliere = filliere;
	}

	
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
  
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
	
	
}
