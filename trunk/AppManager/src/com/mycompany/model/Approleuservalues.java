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

/**
 * ApproleuservaluesId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APPROLEUSERVALUES")
public class Approleuservalues extends BaseObject implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6756759740497768855L;
	// Fields

	private Long id;
	private Long appuservalueid;
	private Long applicationid;
	private Timestamp lastUpdate;
	private String userName;
	private Long activeFlag;
	private Long roleNo;
	private String roleId;
	private Long privNo;
    private String privId;
	private String appShortname;
	private String appuservaluetitle;
	private String appuservaluefieldname;
	private String appuservaluesource;

	// Constructors

	/** default constructor */
	public Approleuservalues() {
	}

	/** minimal constructor */
	public Approleuservalues(Long id, Long appuservalueid,
			Long applicationid, String username, Long activeflag, Long roleNo) {
		this.id = id;
		this.appuservalueid = appuservalueid;
		this.applicationid = applicationid;
		this.userName = username;
		this.activeFlag = activeflag;
		this.roleNo = roleNo;
	}

	/** full constructor */
	public Approleuservalues(Long id, Long appuservalueid,
			Long applicationid, Timestamp lastUpdate, String userName,
			Long activeFlag, Long roleNo) {
		this.id = id;
		this.appuservalueid = appuservalueid;
		this.applicationid = applicationid;
		this.lastUpdate = lastUpdate;
		this.userName = userName;
		this.activeFlag = activeFlag;
		this.roleNo = roleNo;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Approleuservalues_SEQ")
	@SequenceGenerator(name = "Approleuservalues_SEQ", sequenceName = "Approleuservalues_SEQ")
	@Column(name = "ID")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "APPUSERVALUEID")
	public Long getAppuservalueid() {
		return this.appuservalueid;
	}

	public void setAppuservalueid(Long appuservalueid) {
		this.appuservalueid = appuservalueid;
	}

	@Column(name = "APPLICATIONID")
	public Long getApplicationid() {
		return this.applicationid;
	}

	public void setApplicationid(Long applicationid) {
		this.applicationid = applicationid;
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

	public void setActiveFlag(Long activeflag) {
		this.activeFlag = activeflag;
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
        return privNo;
    }

    public void setPrivNo(Long privNo) {
        this.privNo = privNo;
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
    
    @Transient
    public String getAppuservaluetitle() {
        return this.appuservaluetitle;
    }

    public void setAppuservaluetitle(String appuservaluetitle) {
        this.appuservaluetitle = appuservaluetitle;
    }
    
    
    @Transient
	public String getAppuservaluefieldname() {
        return appuservaluefieldname;
    }

    public void setAppuservaluefieldname(String appuservaluefieldname) {
        this.appuservaluefieldname = appuservaluefieldname;
    }

    @Transient
    public String getAppuservaluesource() {
        return appuservaluesource;
    }

    public void setAppuservaluesource(String appuservaluesource) {
        this.appuservaluesource = appuservaluesource;
    }
    
    @Transient
    public String getPrivId() {
        return privId;
    }

    public void setPrivId(String privId) {
        this.privId = privId;
    }

    @Override
	public boolean equals(Object o) {
		if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Approleuservalues pojo = (Approleuservalues) o;

        if (id != null ? !id.equals(pojo.id) : pojo.id != null) return false;
        if (roleNo != null ? !roleNo.equals(pojo.roleNo) : pojo.roleNo != null) return false;
        if (appuservalueid != null ? !appuservalueid.equals(pojo.appuservalueid) : pojo.appuservalueid != null) return false;
        if (applicationid != null ? !applicationid.equals(pojo.applicationid) : pojo.applicationid != null) return false;
        if (activeFlag != null ? !activeFlag.equals(pojo.activeFlag) : pojo.activeFlag != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(pojo.lastUpdate) : pojo.lastUpdate != null) return false;
        if (userName != null ? !userName.equals(pojo.userName) : pojo.userName != null) return false;    	
		return true;
	}

	@Override
	public int hashCode() {
		int result = 0;
        result = (id != null ? id.hashCode() : 0);
        result = 31 * result + (roleNo != null ? roleNo.hashCode() : 0);
        result = 31 * result + (appuservalueid != null ? appuservalueid.hashCode() : 0);
        result = 31 * result + (applicationid != null ? applicationid.hashCode() : 0);
        result = 31 * result + (activeFlag != null ? activeFlag.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("roleNo").append("='").append(getRoleNo()).append("', ");
        sb.append("appuservalueid").append("='").append(getAppuservalueid()).append("', ");
        sb.append("applicationid").append("='").append(getApplicationid()).append("', ");
        sb.append("privNo").append("='").append(getPrivNo()).append("', ");
        sb.append("activeFlag").append("='").append(getActiveFlag()).append("', ");
        sb.append("lastUpdate").append("='").append(getLastUpdate()).append("', ");
        sb.append("userName").append("='").append(getUserName()).append("', ");
        sb.append("]");
        
        return sb.toString();
	}

}
