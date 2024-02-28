package com.smartcontact.entities;



import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Table(name="USER")
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	@NotBlank(message = "Name should not be blanck !!!")
	@Size(min = 2,max = 10,message = "please Enter a valid name")
	private String Name;
	
	@Column(unique = true)
	//@NotBlank(message = "Email Should be Uniqe and not blank")
	private String Email;
	private String Password;
	private String ImageUrl;
	@Column(length = 400)
	private String About;
	private String Role;
	private boolean enable;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
	List<Contact> contact=new ArrayList<>();
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public String getEmail() {
		return Email;
	}


	public void setEmail(String email) {
		Email = email;
	}


	public String getPassword() {
		return Password;
	}


	public void setPassword(String password) {
		Password = password;
	}


	public String getImageUrl() {
		return ImageUrl;
	}


	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}


	public String getAbout() {
		return About;
	}


	public void setAbout(String about) {
		About = about;
	}


	public String getRole() {
		return Role;
	}


	public void setRole(String role) {
		Role = role;
	}


	public boolean isEnable() {
		return enable;
	}


	public void setEnable(boolean enable) {
		this.enable = enable;
	}


	public List<Contact> getContact() {
		return contact;
	}


	public void setContact(List<Contact> contact) {
		this.contact = contact;
	}


	@Override
	public String toString() {
		return "User [Id=" + Id + ", Name=" + Name + ", Email=" + Email + ", Password=" + Password + ", ImageUrl="
				+ ImageUrl + ", About=" + About + ", Role=" + Role + ", enable=" + enable + ", contact=" + contact
				+ "]";
	}
	
	
	
	
	
	
	
	

}
