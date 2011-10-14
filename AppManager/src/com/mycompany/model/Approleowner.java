package com.mycompany.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 * Approleowner entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APPROLEOWNER")
public class Approleowner extends BaseObject implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2515207792733742043L;
	private Long approleownid;
	private Long roleNo;
	private Long applicationid;
	private String owner;
	private Timestamp lastUpdate;
	private String userName;
	private Long activeFlag;
	private String firstName;
	private String lastName;
	private String roleId;
	private String appShortname;

	// Constructors

	/** default constructor */
	public Approleowner() {
	}

	/** minimal constructor */
	public Approleowner(Long roleNo, String userName, Long activeFlag) {
		this.roleNo = roleNo;
		this.userName = userName;
		this.activeFlag = activeFlag;
	}

	/** full constructor */
	public Approleowner(Long roleNo, Long applicationid, String owner,
			Timestamp lastUpdate, String userName, Long activeFlag,
			String firstName, String lastName) {
		this.roleNo = roleNo;
		this.applicationid = applicationid;
		this.owner = owner;
		this.lastUpdate = lastUpdate;
		this.userName = userName;
		this.activeFlag = activeFlag;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Approleowner_SEQ")
	@SequenceGenerator(name = "Approleowner_SEQ", sequenceName = "Approleowner_SEQ")
	@Column(name = "APPROLEOWNID", unique = true, nullable = false, insertable = true, updatable = true, precision = 22, scale = 0)
	public Long getApproleownid() {
		return this.approleownid;
	}

	public void setApproleownid(Long approleownid) {
		this.approleownid = approleownid;
	}

	@Column(name = "ROLE_NO")
	public Long getRoleNo() {
        return this.roleNo;
    }

    public void setRoleNo(Long roleNo) {
        this.roleNo = roleNo;
    }

	
	@Column(name = "APPLICATIONID")
	public Long getApplicationid() {
		return this.applicationid;
	}

	public void setApplicationid(Long applicationid) {
		this.applicationid = applicationid;
	}

	@Column(name = "OWNER")
	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
		if (this.owner != null)
			this.owner = this.owner.toUpperCase();
	}

	
	@Column(name = "LAST_UPDATE")
	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Column(name = "USERNAME")
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "ACTIVEFLAG")
	public Long getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(Long activeFlag) {
		this.activeFlag = activeFlag;
	}

	@Column(name = "FIRST_NAME")
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME")
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Transient
	public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    
    @Transient
    public String getAppShortname() {
        return this.appShortname;
    }
    
    public void setAppShortname(String appShortname) {
        this.appShortname = appShortname;
    }
    
	

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Approleowner pojo = (Approleowner) o;

        if (approleownid != null ? !approleownid.equals(pojo.approleownid) : pojo.approleownid != null) return false;
        if (roleNo != null ? !roleNo.equals(pojo.roleNo) : pojo.roleNo != null) return false;
        if (applicationid != null ? !applicationid.equals(pojo.applicationid) : pojo.applicationid != null) return false;
        if (owner != null ? !owner.equals(pojo.owner) : pojo.owner != null) return false;    	
        if (firstName != null ? !firstName.equals(pojo.firstName) : pojo.firstName != null) return false;
        if (lastName != null ? !lastName.equals(pojo.lastName) : pojo.lastName != null) return false;
        if (activeFlag != null ? !activeFlag.equals(pojo.activeFlag) : pojo.activeFlag != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(pojo.lastUpdate) : pojo.lastUpdate != null) return false;
        if (userName != null ? !userName.equals(pojo.userName) : pojo.userName != null) return false;    	
		return true;
	}

	@Override
	public int hashCode() {
		int result = 0;
        result = (approleownid != null ? approleownid.hashCode() : 0);
        result = 31 * result + (roleNo != null ? roleNo.hashCode() : 0);
        result = 31 * result + (applicationid != null ? applicationid.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (activeFlag != null ? activeFlag.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("approleownid").append("='").append(approleownid).append("', ");
        sb.append("roleNo").append("='").append(roleNo).append("', ");
        sb.append("applicationid").append("='").append(applicationid).append("', ");
        sb.append("owner").append("='").append(owner).append("', ");
        sb.append("firstName").append("='").append(firstName).append("', ");
        sb.append("lastName").append("='").append(lastName).append("', ");
        sb.append("activeFlag").append("='").append(activeFlag).append("', ");
        sb.append("lastUpdate").append("='").append(lastUpdate).append("', ");
        sb.append("userName").append("='").append(userName).append("', ");
        sb.append("]");
        
        return sb.toString();
	}

}