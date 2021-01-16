package restapi.model;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;





@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( name="state",discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("1")

public class User {
  
  protected @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  Long id;
  
 
  
  protected String name;
  
  @JsonIgnore
  protected String password;
  
  
  @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "users_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
				private Set <Role> roles = new HashSet<>(); 
  
  
  @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "etudiant_companies", 
				joinColumns = @JoinColumn(name = "student_id"), 
				inverseJoinColumns = @JoinColumn(name = "company_id"))
				private Set <Company> companies = new HashSet<>(); 
  
  public Set<Role> getRoles() {
	return roles;
}



public void setRoles(Set<Role> roles) {
	this.roles = roles;
}

protected String email;
  protected String telephone;

  public String getTelephone() {
	return telephone;
}



public void setTelephone(String telephone) {
	this.telephone = telephone;
}

protected String username;
  
  
  public String getUsername() {
	return username;
}



public void setUsername(String username) {
	this.username = username;
}




  public User () {}


  public User( String name, String password, String email,String username) {
	
	this.name = name;
	this.password = password;
	this.email = email;
	this.username=username;
	
}





public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getPassword() {
    return this.password;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
 

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof User))
      return false;
    User user = (User) o;
    return Objects.equals(this.id, user.id) && Objects.equals(this.name, user.name)
        && Objects.equals(this.password, user.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.password);
  }

  
}

