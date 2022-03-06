package com.example.demo.model;
import javax.validation.constraints.*;


import org.hibernate.annotations.NaturalId;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.Constant.UserConstant;

import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "username"
        }),
        @UniqueConstraint(columnNames = {
            "email"
        })
})
public class User{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=3, max = 50)
    private String prenom;
    
    @NotBlank
    @Size(min=3, max = 50)
    private String nom;

    @NotBlank
    @Size(min=3, max = 50)
    private String username;
  
    private Date datenaissance;
    
    @NotBlank
    @Size(min=3, max = 50)
    private String codepostal;
    
    @NotBlank
    @Size(min=3, max = 50)
    private String telephone;
   
    @NotBlank
    @Size(min=3, max = 50)
    private String ville;
    
    @Temporal(TemporalType.TIMESTAMP) 
    private Date dateajout = new Date(System.currentTimeMillis());
    
    @NotBlank
    @Size(max = 9)
    private String cin;

	@NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min=6, max = 100)
    private String password;
    
    @Column(name = "reset_password_token")
    private String resetPasswordToken;
    
    private int active;
    private String roles;
    
    @OneToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)@JoinColumn(name="code_id")
    private Code code;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}







	public User(@NotBlank @Size(min = 3, max = 50) String prenom, 
			    @NotBlank @Size(min = 3, max = 50) String nom,
		    	@NotBlank @Size(min = 3, max = 50) String username,
		     	Date datenaissance,
		     	@NotBlank @Size(min = 3, max = 50) String codepostal,
		     	@NotBlank @Size(min = 3, max = 50) String telephone,
			    @NotBlank @Size(min = 3, max = 50) String ville,
			    Date dateajout,
			    @NotBlank @Size(max = 9) String cin,
			    @NotBlank @Size(max = 50) @Email String email,
			    @NotBlank @Size(min = 6, max = 100) String password) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.username = username;
		this.datenaissance = datenaissance;
		this.codepostal = codepostal;
		this.telephone = telephone;
		this.ville = ville;
		this.dateajout = dateajout;
		this.cin = cin;
		this.email = email;
		this.password = password;
	}







	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    

    public String getPrenom() {
		return prenom;
	}







	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}







	public String getNom() {
		return nom;
	}







	public void setNom(String nom) {
		this.nom = nom;
	}







	public Date getDatenaissance() {
		return datenaissance;
	}







	public void setDatenaissance(Date datenaissance) {
		this.datenaissance = datenaissance;
	}







	public String getCodepostal() {
		return codepostal;
	}







	public void setCodepostal(String codepostal) {
		this.codepostal = codepostal;
	}







	public String getTelephone() {
		return telephone;
	}







	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}







	public String getVille() {
		return ville;
	}







	public void setVille(String ville) {
		this.ville = ville;
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

  
    
    public Date getDateajout() {
		return dateajout;
	}















	











		public Code getCode() {
		return code;
	}







	public void setCode(Code code) {
		this.code = code;
	}







		public void setDateajout(Date dateajout) {
		this.dateajout = dateajout;
	}







	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}









	public String getRoles() {
		return roles;
	}







	public void setRoles(String roles) {
		this.roles = roles;
	}







	public String getResetPasswordToken() {
		return resetPasswordToken;
	}







	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}







	public int getActive() {
		return active;
	}







	public void setActive(int active) {
		this.active = active;
	}

















	
}