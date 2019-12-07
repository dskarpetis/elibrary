/**
 * UserLogin.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.domain;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;

import dskarpetis.elibrary.util.hash.PasswordEncryptionAuthorized;

/**
 * UserLogin domain object
 * 
 * @author dskarpetis
 */
@Entity
@Table(name = "user_login", schema = "public")
public class UserLogin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq-gen2", sequenceName = "auto_increment_userlogin", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-gen2")
	@Column(name = "user_login_id")
	private int userLoginID;

	@Column(name = "username")
	private String username;

	@Column(name = "salt")
	private byte[] salt;

	@Column(name = "hash")
	private byte[] hash;

	@Column(name = "role_id")
	private int roleID;

	@Transient
	private Roles roles;

	/**
	 * Constructor that sets <code>final</code> fields.
	 * 
	 * @param username
	 *            User name
	 */
	public UserLogin(String username) {
		super();
		if (username == null) {
			throw new IllegalArgumentException("Missing data");
		}
		this.username = username;

	}

	/**
	 * @return the roles
	 */
	public Roles getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	/**
	 * Whether this user has the given role.
	 * 
	 * @param role
	 * @return whether this user has the given role
	 */
	public boolean hasRole(String role) {
		return roles.hasRole(role);
	}

	/**
	 * Whether this user has any of the given roles.
	 * 
	 * @param roles
	 *            set of roles
	 * @return whether this user has any of the given roles
	 */
	public boolean hasAnyRole(Roles roles) {
		return this.roles.hasAnyRole(roles);
	}

	/**
	 * 
	 */
	public UserLogin() {
		super();

	}

	/**
	 * Indicates whether the current User has the given name (for equality
	 * purposes).
	 * 
	 * @param username
	 * 
	 * @return <code>true</code> if the user has the given username, otherwise
	 *         <code>false</code>
	 */
	public boolean hasUsername(String username) {
		return this.username.equals(username);
	}

	/**
	 * Indicates whether the current User has the given password (for
	 * authentication purposes).
	 * 
	 * @param password
	 * 
	 * @return <code>true</code> if the user has the given password, otherwise
	 *         <code>false</code>
	 */

	public boolean verifyPassword(String password) {
		return new PasswordEncryptionAuthorized().isExpectedPassword(password.toCharArray(), getSalt(), getHash());
	}

	/**
	 * @return the userLoginID
	 */
	public int getUserLoginID() {
		return userLoginID;
	}

	/**
	 * @param userLoginID
	 *            the userLoginID to set
	 */
	public void setUserLoginID(int userLoginID) {
		this.userLoginID = userLoginID;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the roleID
	 */
	public int getRoleID() {
		return roleID;
	}

	/**
	 * @param roleID
	 *            the roleID to set
	 */
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	/**
	 * @return the salt
	 */
	public byte[] getSalt() {
		return salt;
	}

	/**
	 * @param salt
	 *            the salt to set
	 */
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	/**
	 * @return the hash
	 */
	public byte[] getHash() {
		return hash;
	}

	/**
	 * @param hash
	 *            the hash to set
	 */
	public void setHash(byte[] hash) {
		this.hash = hash;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserLogin [userLoginID=" + userLoginID + ", username=" + username + ", salt=" + Arrays.toString(salt) + ", hash="
				+ Arrays.toString(hash) + ", roleID=" + roleID + ", roles=" + roles + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(hash);
		result = prime * result + roleID;
		result = prime * result + Arrays.hashCode(salt);
		result = prime * result + userLoginID;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserLogin other = (UserLogin) obj;
		if (!Arrays.equals(hash, other.hash))
			return false;
		if (roleID != other.roleID)
			return false;
		if (!Arrays.equals(salt, other.salt))
			return false;
		if (userLoginID != other.userLoginID)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
