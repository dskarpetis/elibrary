/**
 * Role.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Role domain object
 * 
 * @author dskarpetis
 */
@Entity
@Table(name = "role", schema="public")
public class Role implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Id
	@SequenceGenerator(name = "seq-gen", sequenceName = "auto_increment_role", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-gen")
    @Column(name = "role_id")
	private int roleID;
    
	@Column(name = "role_name")
	private String roleName;

	/**
	 * 
	 */
	public Role() {
	}

	/**
	 * @param roleName
	 */
	public Role(String roleName) {
		super();
		this.roleName = roleName;
	}

	/**
	 * @return roleID
	 */
	public int getRoleID() {
		return roleID;
	}

	/**
	 * @param roleID
	 */
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	/**
	 * @return roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
