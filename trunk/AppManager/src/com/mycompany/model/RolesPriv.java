package com.mycompany.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

/**
 * RolesPriv entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ROLES_PRIV")
public class RolesPriv extends BaseObject implements java.io.Serializable {

    // Fields

    /**
	 * 
	 */
	private static final long serialVersionUID = -2171797125678956204L;
	private Long rolesPrivNo;
    private Long roleNo;
    private Long privNo;
    private Long activeFlag;
    private Timestamp lastUpdate;
    private String userName;
    private String roleId;
    private String privId;
    private String roleDescription;

    // Constructors


	/** default constructor */
    public RolesPriv() {
    }

    /** minimal constructor */
    public RolesPriv(Long roleNo, Long privNo) {
        this.roleNo = roleNo;
        this.privNo = privNo;
    }

    /** full constructor */
    public RolesPriv(Long roleNo, Long privNo, Long activeFlag, Timestamp lastUpdate, String userName) {
        this.roleNo = roleNo;
        this.privNo = privNo;
        this.activeFlag = activeFlag;
        this.lastUpdate = lastUpdate;
        this.userName = userName;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ROLES_PRIV_SEQ")
    @SequenceGenerator(name = "ROLES_PRIV_SEQ", sequenceName = "ROLES_PRIV_SEQ")
    @Column(name = "ROLES_PRIV_NO", unique = true, nullable = false, insertable = true, updatable = true, precision = 22, scale = 0)
    public Long getRolesPrivNo() {
        return this.rolesPrivNo;
    }

    public void setRolesPrivNo(Long rolesPrivNo) {
        this.rolesPrivNo = rolesPrivNo;
    }

    @Column(name = "ROLE_NO")
    public Long getRoleNo() {
        return this.roleNo;
    }

    public void setRoleNo(Long roleNo) {
        this.roleNo = roleNo;
    }

    @Column(name = "PRIV_NO")
    public Long getPrivNo() {
        return this.privNo;
    }

    public void setPrivNo(Long privNo) {
        this.privNo = privNo;
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


    @Column(name = "LAST_UPDATE")
    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
    // fields used in join queries defined as transient since they are not part
    // of the actual table.
    
    @Transient
    public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Transient
	public String getPrivId() {
		return privId;
	}

	public void setPrivId(String privId) {
		this.privId = privId;
	}
	
	@Transient
    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
    
    /**
     * Constructs a <code>String</code> with all attributes
     * in name = value format.
     *
     * @return a <code>String</code> representation 
     * of this object.
     */
    public String toString()
    {
        final String TAB = "    ";
        
        String retValue = "";
        
        retValue = "RolesPriv ( "
            + "RolesPriv " + TAB
            + "rolesPrivNo = " + this.rolesPrivNo + TAB
            + "roleNo = " + this.roleNo + TAB
            + "privNo = " + this.privNo + TAB
            + "activeFlag = " + this.activeFlag + TAB
            + "lastUpdate = " + this.lastUpdate + TAB
            + "userName = " + this.userName + TAB
            + " )";
    
        return retValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 0;;
        result = ((userName == null) ? 0 : userName.hashCode());
        result = prime * result
                + ((activeFlag == null) ? 0 : activeFlag.hashCode());
        result = prime * result
        + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
        result = prime * result + ((privNo == null) ? 0 : privNo.hashCode());
        result = prime * result + ((roleNo == null) ? 0 : roleNo.hashCode());
        result = prime * result
                + ((rolesPrivNo == null) ? 0 : rolesPrivNo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof RolesPriv))
            return false;
        final RolesPriv other = (RolesPriv) obj;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        if (activeFlag == null) {
            if (other.activeFlag != null)
                return false;
        } else if (!activeFlag.equals(other.activeFlag))
            return false;
        if (lastUpdate == null) {
            if (other.lastUpdate != null)
                return false;
        } else if (!lastUpdate.equals(other.lastUpdate))
            return false;
        if (privNo == null) {
            if (other.privNo != null)
                return false;
        } else if (!privNo.equals(other.privNo))
            return false;
        if (roleNo == null) {
            if (other.roleNo != null)
                return false;
        } else if (!roleNo.equals(other.roleNo))
            return false;
        if (rolesPrivNo == null) {
            if (other.rolesPrivNo != null)
                return false;
        } else if (!rolesPrivNo.equals(other.rolesPrivNo))
            return false;
        return true;
    }
}