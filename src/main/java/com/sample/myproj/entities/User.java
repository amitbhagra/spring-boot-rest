package com.sample.myproj.entities;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(unique = true, length = 32)
	@NotNull
	private String userName;
	@Column(length = 50)
	private String email;
	@JsonIgnore
	@Column(length = 100)
	private String password;
	@Column(unique = true, length = 25)
	private String contact;
	@Column(length = 30)
	private String firstName;
	@Column(length = 30)
	private String lastName;
	@Column(length = 50)
	private String state;
	@NotNull
	private Boolean enabled;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_authority",  joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "authority_id", referencedColumnName = "id") })
	private List<Authority> authorities;

	private Integer loginMode;
	private Timestamp createdOn;
	@JsonIgnore
	private Timestamp updatedOn;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getLoginMode() {
		return loginMode;
	}

	public void setLoginMode(Integer loginMode) {
		this.loginMode = loginMode;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	@PrePersist
	protected void onCreate() {
		createdOn = new Timestamp(new Date().getTime());
		updatedOn = new Timestamp(new Date().getTime());
	}

	@PreUpdate
	protected void onUpdate() {
		updatedOn = new Timestamp(new Date().getTime());
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + "]";
	}
	
	
}

