package com.mycompany.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 * Applicationpriv entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APPLICATIONPRIV")
public class Applicationpriv extends BaseObject implements java.io.Serializable {

    // Fields

    private static final long serialVersionUID = -8416862103932424524L;
    private Long applicationprivid;
    private Long applicationid;
    private Long privNo;
    private Timestamp lastUpdate;
    private Long activeFlag;
    private String userName;
    private String privId;
    private String appShortname;

    // Constructors

    /** default constructor */
    public Applicationpriv() {
    }

    /** full constructor */
    public Applicationpriv(Long applicationid, Long privNo, Timestamp lastUpdate, Long activeFlag, String userName) {
        this.applicationid = applicationid;
        this.privNo = privNo;
        this.lastUpdate = lastUpdate;
        this.activeFlag = activeFlag;
        this.userName = userName;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "Applicationpriv_SEQ")
    @SequenceGenerator(name = "Applicationpriv_SEQ", sequenceName = "Applicationpriv_SEQ")
    @Column(name = "APPLICATIONPRIVID")
    public Long getApplicationprivid() {
        return this.applicationprivid;
    }
    public void setApplicationprivid(Long applicationprivid) {
        this.applicationprivid = applicationprivid;
    }
    @Column(name = "PRIV_NO")
    public Long getPrivNo() {
        return this.privNo;
    }
    public void setPrivNo(Long privNo) {
        this.privNo = privNo;
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
		return lastUpdate;
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
    @Transient
	public String getPrivId() {
		return privId;
	}
	public void setPrivId(String privId) {
		this.privId = privId;
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
        int result = 0;
        result = (privNo != null ? privNo.hashCode() : 0);
        result = 31 * result + (applicationid != null ? applicationid.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (activeFlag != null ? activeFlag.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        
        if (!(obj instanceof Applicationpriv))
            return false;
        final Applicationpriv other = (Applicationpriv) obj;
        if (applicationid == null) {
            if (other.applicationid != null)
                return false;
        } else if (!applicationid.equals(other.applicationid))
            return false;
        if (privNo == null) {
            if (other.privNo != null)
                return false;
        } else if (!privNo.equals(other.privNo))
            return false;
        if (applicationprivid == null) {
            if (other.applicationprivid != null)
                return false;
        } else if (!applicationprivid.equals(other.applicationprivid))
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
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
        
        retValue = "Applicationpriv ( "
            + "Applicationpriv " + TAB
            + "applicationprivid = " + this.applicationprivid + TAB
            + "applicationid = " + this.applicationid + TAB
            + "privNo = " + this.privNo + TAB
            + "lastUpdate = " + this.lastUpdate + TAB
            + "userName = " + this.userName + TAB
            + "activeFlag = " + this.activeFlag + TAB
            + " )";
    
        return retValue;
    }
}