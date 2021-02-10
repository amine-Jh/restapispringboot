package restapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity

public class PhotoInfo {
	
      @Id
      @Column(length = 64)
	  @GeneratedValue(generator = "uuid")
	  @GenericGenerator(name = "uuid", strategy = "uuid2")
	  private String id;
      private String name;

	  private String type;

	  @Lob
	  private byte[] data;
	  
	  @OneToOne(mappedBy = "photo")
	    private User user;

	  public PhotoInfo() {
	  }

	  public  PhotoInfo (String name, String type, byte[] data) {
	    this.name = name;
	    this.type = type;
	    this.data = data;
	    
	  }

	  public String getId() {
	    return id;
	  }

	  public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public String getType() {
	    return type;
	  }

	  public void setType(String type) {
	    this.type = type;
	  }

	  public byte[] getData() {
	    return data;
	  }

	  public void setData(byte[] data) {
	    this.data = data;
	  }

	}
