package com.mycompany.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * Roles entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ROLES")
public class Roles extends BaseObject implements java.io.Serializable {

    // Fields

    /**
	 * 
	 */
	private static final long serialVersionUID = 3240343601069310812L;
	private Long roleNo;
    private Long roletypeid;
    private String roleId;
    private String roleDesc;
    private String roleDescExt;
    private Timestamp lastUpdate;
    private Long activeFlag;
    private String userName;
    private Long appAccessrequestmethodid;
    private String requestmethodtitle;
    private String roletypename;

    // Constructors

    /** default constructor */
    public Roles() {
    }

    /** minimal constructor */
    public Roles(String roleId, Long activeFlag) {
        this.roleId = roleId;
        this.activeFlag = activeFlag;
    }

    /** full constructor */
    public Roles(Long roletypeid, String roleId, String roleDesc,
            String roleDescExt, Timestamp lastUpdate, Long activeFlag, Long appAccessrequestmethodid) {
        this.roletypeid = roletypeid;
        this.roleId = roleId;
        this.roleDesc = roleDesc;
        this.roleDescExt = roleDescExt;
        this.lastUpdate = lastUpdate;
        this.activeFlag = activeFlag;
        this.appAccessrequestmethodid = appAccessrequestmethodid;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "Roles_SEQ")
    @SequenceGenerator(name = "Roles_SEQ", sequenceName = "Roles_SEQ")
    @Column(name = "ROLE_NO")
    public Long getRoleNo() {
        return this.roleNo;
    }

    public void setRoleNo(Long roleNo) {
        this.roleNo = roleNo;
    }

    @Column(name = "ROLETYPEID")
    public Long getRoletypeid() {
        return this.roletypeid;
    }

    public void setRoletypeid(Long roletypeid) {
        this.roletypeid = roletypeid;
    }

    @Column(name = "ROLE_ID")
    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
        if (this.roleId != null)
        	this.roleId = this.roleId.toUpperCase();
    }

    @Column(name = "ROLE_DESC")
    public String getRoleDesc() {
        return this.roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Column(name = "ROLE_DESC_EXT")
    public String getRoleDescExt() {
        return this.roleDescExt;
    }

    public void setRoleDescExt(String roleDescExt) {
        this.roleDescExt = roleDescExt;
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
    
    @Column(name = "APP_ACCESSREQUESTMETHODID")
	public Long getAppAccessrequestmethodid() {
		return appAccessrequestmethodid;
	}

	public void setAppAccessrequestmethodid(Long appAccessrequestmethodid) {
		this.appAccessrequestmethodid = appAccessrequestmethodid;
	}

	@Transient
	public String getRequestmethodtitle() {
		return requestmethodtitle;
	}

	public void setRequestmethodtitle(String requestmethodtitle) {
		this.requestmethodtitle = requestmethodtitle;
	}
	
	@Transient
	public String getRoletypename() {
		return roletypename;
	}

	public void setRoletypename(String roletypename) {
		this.roletypename = roletypename;
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
        
        retValue = "Roles ( "
            + "Role " + TAB
            + "roleNo = " + this.roleNo + TAB
            + "roletypeid = " + this.roletypeid + TAB
            + "roleId = " + this.roleId + TAB
            + "roleDesc = " + this.roleDesc + TAB
            + "roleDescExt = " + this.roleDescExt + TAB
            + "lastUpdate = " + this.lastUpdate + TAB
            + "activeFlag = " + this.activeFlag + TAB
            + "appAccessrequestmethodid = " + this.appAccessrequestmethodid + TAB
            + " )";
    
        return retValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 0;
        result = ((appAccessrequestmethodid == null) ? 0 : appAccessrequestmethodid.hashCode());
        result = prime * result
                + ((activeFlag == null) ? 0 : activeFlag.hashCode());
        result = prime * result
                + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
        result = prime * result
                + ((roleDesc == null) ? 0 : roleDesc.hashCode());
        result = prime * result
                + ((roleDescExt == null) ? 0 : roleDescExt.hashCode());
        result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
        result = prime * result + ((roleNo == null) ? 0 : roleNo.hashCode());
        result = prime * result
                + ((roletypeid == null) ? 0 : roletypeid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        
        if (!(obj instanceof Roles))
            return false;
        final Roles other = (Roles) obj;
        if (appAccessrequestmethodid == null) {
            if (other.appAccessrequestmethodid != null)
                return false;
        } else if (!appAccessrequestmethodid.equals(other.appAccessrequestmethodid))
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
        if (roleDesc == null) {
            if (other.roleDesc != null)
                return false;
        } else if (!roleDesc.equals(other.roleDesc))
            return false;
        if (roleDescExt == null) {
            if (other.roleDescExt != null)
                return false;
        } else if (!roleDescExt.equals(other.roleDescExt))
            return false;
        if (roleId == null) {
            if (other.roleId != null)
                return false;
        } else if (!roleId.equals(other.roleId))
            return false;
        if (roleNo == null) {
            if (other.roleNo != null)
                return false;
        } else if (!roleNo.equals(other.roleNo))
            return false;
        if (roletypeid == null) {
            if (other.roletypeid != null)
                return false;
        } else if (!roletypeid.equals(other.roletypeid))
            return false;
        return true;
    }

	



}