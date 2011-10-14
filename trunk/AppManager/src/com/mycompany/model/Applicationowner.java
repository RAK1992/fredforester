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
 * Applicationowner entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APPLICATIONOWNER")
public class Applicationowner extends BaseObject implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9073982066035351053L;
	// Fields

	/**
	 * 
	 */
	private Long applicationownid;
	private Long applicationid;
    private String appShortname;
	private String owner;
	private String firstName;
	private String lastName;
	private Timestamp lastUpdate;
	private String userName;
	private Long activeFlag;

	// Constructors

	/** default constructor */
	public Applicationowner() {
	}


	/** full constructor */
	public Applicationowner(Long applicationid, String owner,
			Timestamp lastUpdate, String userName, Long activeFlag) {
		this.applicationid = applicationid;
		this.owner = owner;
		this.lastUpdate = lastUpdate;
		this.userName = userName;
		this.activeFlag = activeFlag;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Applicationowner_SEQ")
	@SequenceGenerator(name = "Applicationowner_SEQ", sequenceName = "Applicationowner_SEQ")
	@Column(name = "APPLICATIONOWNID")
	public Long getApplicationownid() {
		return this.applicationownid;
	}

	public void setApplicationownid(Long applicationownid) {
		this.applicationownid = applicationownid;
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

    @Column(name = "ACTIVEFLAG")
    public Long getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Long activeFlag) {
		this.activeFlag = activeFlag;
	}

	@Column(name = "USERNAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME")
	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Transient
	public String getAppShortname() {
		return appShortname;
	}


	public void setAppShortname(String appShortname) {
		this.appShortname = appShortname;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = prime * result
				+ ((activeFlag == null) ? 0 : activeFlag.hashCode());
		result = prime * result
				+ ((applicationid == null) ? 0 : applicationid.hashCode());
		result = prime * result
		+ ((owner == null) ? 0 : owner.hashCode());
		result = prime
				* result
				+ ((applicationownid == null) ? 0 : applicationownid.hashCode());
		result = prime * result
				+ ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		result = prime * result
		+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
		+ ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	public String toString()
    {
        final String TAB = "    ";
        
        String retValue = "";
        
        retValue = "Applicationowner ( "
            + "Applicationowner " + TAB
            + "applicationownid = " + this.applicationownid + TAB
            + "applicationid = " + this.applicationid + TAB
            + "owner = " + this.owner + TAB
            + "lastUpdate = " + this.lastUpdate + TAB
            + "activeFlag = " + this.activeFlag + TAB
            + "userName = " + this.userName + TAB
            + "firstName = " + this.firstName + TAB
            + "lastName = " + this.lastName + TAB
            + " )";
    
        return retValue;
    }
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Applicationowner))
            return false;
		final Applicationowner other = (Applicationowner) obj;
		if (applicationid == null) {
			if (other.applicationid != null)
				return false;
		} else if (!applicationid.equals(other.applicationid))
			return false;
		if (applicationownid == null) {
			if (other.applicationownid != null)
				return false;
		} else if (!applicationownid.equals(other.applicationownid))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
		if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
		if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (lastUpdate == null) {
            if (other.lastUpdate != null)
                return false;
        } else if (!lastUpdate.equals(other.lastUpdate))
            return false;
        if (activeFlag == null) {
            if (other.activeFlag != null)
                return false;
        } else if (!activeFlag.equals(other.activeFlag))
            return false;

		return true;
	}


	


}