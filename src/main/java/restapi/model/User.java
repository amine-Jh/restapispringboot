package restapi.model;
import java.util.Objects;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;





@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( name="role",discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("user")

public class User {
  
  protected @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  Long id;
  
 

  protected String name;
  
  protected String password;
  
  
  protected String email;

  protected String username;
  
  
  public String getUsername() {
	return username;
}



public void setUsername(String username) {
	this.username = username;
}



public User() {}
  


  public User( String name, String password, String email, String role,String username) {
	
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

