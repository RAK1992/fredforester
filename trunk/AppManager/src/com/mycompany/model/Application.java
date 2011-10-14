package com.mycompany.model;

import com.mycompany.model.BaseObject;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import java.sql.Timestamp;


@Entity
@Table(name="APPLICATION")
public class Application extends BaseObject implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 5572485092073622226L;
    private Long applicationid;
    private String appShortname;
    private String appLongname;
    private String appDesc;
    private Long activeFlag;
    private Timestamp lastUpdate;
    private String userName;
    private Long appIsintranetpublicflag;
    private String appHref;
    private Long appHreflaunchmethodid;
    private Long appHrefversion;
    private Long hostFamilyid;
    private String appUserdescription;
    private Long appDepartmentid;
    private Long appTimeoutmin;
    private Long appIsesigclientflag;
    private String familyname;
    //private Set<Applicationpriv> applicationprivs = new HashSet<Applicationpriv>(0);

	@Id  @GeneratedValue(strategy = GenerationType.AUTO,generator="APPLICATION_SEQ")
    @SequenceGenerator(name="APPLICATION_SEQ", sequenceName="APPLICATION_SEQ")
    public Long getApplicationid() {
        return this.applicationid;
    }
    
    public void setApplicationid(Long applicationid) {
        this.applicationid = applicationid;
    }
    
    @Column(name="APP_SHORTNAME")
    public String getAppShortname() {
        return this.appShortname;
    }
    
    public void setAppShortname(String appShortname) {
        this.appShortname = appShortname;
    }
    
    @Column(name="APP_LONGNAME")
    public String getAppLongname() {
        return this.appLongname;
    }
    
    public void setAppLongname(String appLongname) {
        this.appLongname = appLongname;
    }
    
    @Column(name="APP_DESC")
    public String getAppDesc() {
        return this.appDesc;
    }
    
    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }
    
    @Column(name="ACTIVEFLAG")
    public Long getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Long activeFlag) {
		this.activeFlag = activeFlag;
	}
    
    
    @Column(name="LAST_UPDATE")
    public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Column(name="USERNAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    
    @Column(name="APP_ISINTRANETPUBLICFLAG", precision=3, scale=0)
    public Long getAppIsintranetpublicflag() {
        return this.appIsintranetpublicflag;
    }
    
    public void setAppIsintranetpublicflag(Long appIsintranetpublicflag) {
        this.appIsintranetpublicflag = appIsintranetpublicflag;
    }
    
    /*
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="application")
    public Set<Applicationpriv> getApplicationprivs() {
        return this.applicationprivs;
    }
    
    
    public void setApplicationprivs(Set<Applicationpriv> applicationprivs) {
        this.applicationprivs = applicationprivs;
    }
    */
    @Column(name="APP_HREF")
    public String getAppHref() {
		return appHref;
	}

	public void setAppHref(String appHref) {
		this.appHref = appHref;
	}
	@Column(name="APP_HREF_LAUNCHMETHODID")
	public Long getAppHreflaunchmethodid() {
		return appHreflaunchmethodid;
	}

	public void setAppHreflaunchmethodid(Long appHreflaunchmethodid) {
		this.appHreflaunchmethodid = appHreflaunchmethodid;
	}
	@Column(name="APP_HREF_VERSION")
	public Long getAppHrefversion() {
		return appHrefversion;
	}

	public void setAppHrefversion(Long appHrefversion) {
		this.appHrefversion = appHrefversion;
	}
	@Column(name="HOSTFAMILYID")
	public Long getHostFamilyid() {
		return hostFamilyid;
	}

	public void setHostFamilyid(Long hostFamilyid) {
		this.hostFamilyid = hostFamilyid;
	}
	@Column(name="APP_USER_DESCRIPTION")
	public String getAppUserdescription() {
		return appUserdescription;
	}

	public void setAppUserdescription(String appUserdescription) {
		this.appUserdescription = appUserdescription;
	}
	@Column(name="APP_DEPARTMENTID")
	public Long getAppDepartmentid() {
		return appDepartmentid;
	}

	public void setAppDepartmentid(Long appDepartmentid) {
		this.appDepartmentid = appDepartmentid;
	}
	@Column(name="APP_TIMEOUTMIN")
	public Long getAppTimeoutmin() {
		return appTimeoutmin;
	}

	public void setAppTimeoutmin(Long appTimeoutmin) {
		this.appTimeoutmin = appTimeoutmin;
	}
	@Column(name="APP_ISESIGCLIENTFLAG")
	public Long getAppIsesigclientflag() {
		return appIsesigclientflag;
	}

	public void setAppIsesigclientflag(Long appIsesigclientflag) {
		this.appIsesigclientflag = appIsesigclientflag;
	}
	
	@Transient
	public String getFamilyname() {
		return familyname;
	}

	public void setFamilyname(String familyname) {
		this.familyname = familyname;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Application pojo = (Application) o;

        if (appShortname != null ? !appShortname.equals(pojo.appShortname) : pojo.appShortname != null) return false;
        if (appLongname != null ? !appLongname.equals(pojo.appLongname) : pojo.appLongname != null) return false;
        if (appDesc != null ? !appDesc.equals(pojo.appDesc) : pojo.appDesc != null) return false;
        if (activeFlag != null ? !activeFlag.equals(pojo.activeFlag) : pojo.activeFlag != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(pojo.lastUpdate) : pojo.lastUpdate != null) return false;
        if (userName != null ? !userName.equals(pojo.userName) : pojo.userName != null) return false;
        if (appIsintranetpublicflag != null ? !appIsintranetpublicflag.equals(pojo.appIsintranetpublicflag) : pojo.appIsintranetpublicflag != null) return false;
        if (appHref != null ? !appHref.equals(pojo.appHref) : pojo.appHref != null) return false;
        if (appHreflaunchmethodid != null ? !appHreflaunchmethodid.equals(pojo.appHreflaunchmethodid) : pojo.appHreflaunchmethodid != null) return false;
        if (appHrefversion != null ? !appHrefversion.equals(pojo.appHrefversion) : pojo.appHrefversion != null) return false;
        if (hostFamilyid != null ? !hostFamilyid.equals(pojo.hostFamilyid) : pojo.hostFamilyid != null) return false;
        if (appUserdescription != null ? !appUserdescription.equals(pojo.appUserdescription) : pojo.appUserdescription != null) return false;
        if (appDepartmentid != null ? !appDepartmentid.equals(pojo.appDepartmentid) : pojo.appDepartmentid != null) return false;
        if (appTimeoutmin != null ? !appTimeoutmin.equals(pojo.appTimeoutmin) : pojo.appTimeoutmin != null) return false;
        if (appIsesigclientflag != null ? !appIsesigclientflag.equals(pojo.appIsesigclientflag) : pojo.appIsesigclientflag != null) return false;
        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (appShortname != null ? appShortname.hashCode() : 0);
        result = 31 * result + (appLongname != null ? appLongname.hashCode() : 0);
        result = 31 * result + (appDesc != null ? appDesc.hashCode() : 0);
        result = 31 * result + (activeFlag != null ? activeFlag.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (appIsintranetpublicflag != null ? appIsintranetpublicflag.hashCode() : 0);
        result = 31 * result + (appHref != null ? appHref.hashCode() : 0);
        result = 31 * result + (appHreflaunchmethodid != null ? appHreflaunchmethodid.hashCode() : 0);
        result = 31 * result + (appHrefversion != null ? appHrefversion.hashCode() : 0);
        result = 31 * result + (hostFamilyid != null ? hostFamilyid.hashCode() : 0);
        result = 31 * result + (appUserdescription != null ? appUserdescription.hashCode() : 0);
        result = 31 * result + (appDepartmentid != null ? appDepartmentid.hashCode() : 0);
        result = 31 * result + (appTimeoutmin != null ? appTimeoutmin.hashCode() : 0);
        result = 31 * result + (appIsesigclientflag != null ? appIsesigclientflag.hashCode() : 0);
        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("applicationid").append("='").append(getApplicationid()).append("', ");
        sb.append("appShortname").append("='").append(getAppShortname()).append("', ");
        sb.append("appLongname").append("='").append(getAppLongname()).append("', ");
        sb.append("appDesc").append("='").append(getAppDesc()).append("', ");
        sb.append("activeFlag").append("='").append(getActiveFlag()).append("', ");
        sb.append("lastUpdate").append("='").append(getLastUpdate()).append("', ");
        sb.append("userName").append("='").append(getUserName()).append("', ");
        sb.append("appIsintranetpublicflag").append("='").append(getAppIsintranetpublicflag()).append("', ");
        sb.append("appHref").append("='").append(getAppHref()).append("', ");
        sb.append("appHreflaunchmethodid").append("='").append(getAppHreflaunchmethodid()).append("', ");
        sb.append("appHrefversion").append("='").append(getAppHrefversion()).append("', ");
        sb.append("hostFamilyid").append("='").append(getHostFamilyid()).append("', ");
        sb.append("appUserdescription").append("='").append(getAppUserdescription()).append("', ");
        sb.append("appDepartmentid").append("='").append(getAppDepartmentid()).append("', ");
        sb.append("appTimeoutmin").append("='").append(getAppTimeoutmin()).append("', ");
        sb.append("appIsesigclientflag").append("='").append(getAppIsesigclientflag()).append("', ");
        sb.append("]");
      
        return sb.toString();
    }


}
