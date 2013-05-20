package com.crowdgame.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.google.common.collect.Lists;

@Entity
public class GameUser {

	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	private String email;
	
	private boolean dyslexic;
	
	private Integer age;
	
	private boolean isSpanishSpeaker;
	
	private Integer platformId;
	
	private Integer score = 0;
	
	@ElementCollection(fetch=FetchType.EAGER)
	List<String> roles;
	
	@SuppressWarnings("deprecation")
	@OneToOne(fetch=FetchType.EAGER)
	@Cascade({CascadeType.DELETE_ORPHAN, CascadeType.ALL})
	private PasswordResetRequest passwordResetRequest;
	
	public GameUser() {
		
	}

	public GameUser(Registration registration) {
		this.username = registration.getUsername();
		this.password = registration.getPassword();
		this.email = registration.getEmail();
		this.dyslexic = registration.isDyslexic();
		this.age = registration.getAge();
		this.isSpanishSpeaker = registration.isSpanishSpeaker();
		this.roles = Lists.newArrayList("ROLE_USER");
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isDyslexic() {
		return dyslexic;
	}

	public void setDyslexic(boolean dyslexic) {
		this.dyslexic = dyslexic;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public boolean isSpanishSpeaker() {
		return isSpanishSpeaker;
	}

	public void setSpanishSpeaker(boolean isSpanishSpeaker) {
		this.isSpanishSpeaker = isSpanishSpeaker;
	}

	public Integer getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Integer platformId) {
		this.platformId = platformId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	public void increaseScore(Integer increase) {
		this.score += increase;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public PasswordResetRequest getPasswordResetRequest() {
		return passwordResetRequest;
	}

	public void setPasswordResetRequest(PasswordResetRequest passwordResetRequest) {
		this.passwordResetRequest = passwordResetRequest;
	}
}
