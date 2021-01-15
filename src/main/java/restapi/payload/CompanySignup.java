package restapi.payload;

import java.util.Set;

public class CompanySignup {

	
	
	 private String username;
	    
	    private String adresse;
	    
	    private String telephone;
	    
	    public String getTelephone() {
			return telephone;
		}

		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}

		private String type;
	   
	   
	  
		  Set<String> roles ;
		    
		   

			public Set<String> getRoles() {
				return roles;
			}

			public void setRoles(Set<String> roles) {
				this.roles = roles;
			}
		


		private String email;
	    
	    
	    private String name;
	    
	    public String getAdresse() {
			return adresse;
		}

		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}

		public CompanySignup(String username, String adresse, String telephone, String type, String email, String name,
				String password ,Set<String>  roles  ) {
			this.username = username;
			this.adresse = adresse;
			this.telephone = telephone;
			this.type = type;
			this.email = email;
			this.name = name;
			this.password = password;
			this.roles=roles;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		private String password;
	  
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
