package com.example.demo.message.request;

import java.util.Date;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;

public class SignUpForm {
	@NotBlank
	@Size(min=3, max = 50)
	private String prenom;
	    
	@NotBlank
	@Size(min=3, max = 50)
	private String nom;

    @NotBlank
    @Size(min = 3, max = 50)
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
    @Size(max = 60)
    @Email
    private String email;
    
    private String role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private int active;

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
    
    public String getRole() {
    	return this.role;
    }
    
    public void setRole(String role) {
    	this.role = role;
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

	public Date getDateajout() {
		return dateajout;
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


	public int getActive() {
		return active;
	}







	public void setActive(int active) {
		this.active = active;
	}


}