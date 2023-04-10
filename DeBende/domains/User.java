package com.shopr.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name="findAllUsers", query="SELECT u from User u"),
        @NamedQuery(name="findUserById", query="SELECT u from User u where u.id = :id"),
        @NamedQuery(name="checkIfLoginMatches", query="SELECT u from User u where u.userName = :name and u.userPassword = :password"),
        @NamedQuery(name="findUserBySearch", query="SELECT u from User u where u.id=:id or u.userName=:userName or u.userPassword=:userPassword or u.securityLevel=:userSecLvl"),
})
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^(([a-zA-Z]){1})(([a-zA-Z])|([-])){0,13}(([a-zA-Z]){1})$",
            message = "Your name can't contain a number, whitespace or a special character, maximum of 15 characters")
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String userPassword;
    @Column(nullable = false)
    private int securityLevel = 2;
	
    public int getSecurityLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setSecurityLevel(int securityLevel) {
		this.securityLevel = securityLevel;
	}
    
    


}