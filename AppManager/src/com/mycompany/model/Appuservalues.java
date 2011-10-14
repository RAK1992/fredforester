package com.mycompany.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 * Appuservalues entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APPUSERVALUES")
public class Appuservalues extends BaseObject implements java.io.Serializable {

    private static final long serialVersionUID = -3822570293915875035L;
    // Fields

    private Long appuservalueid;
    private String appuservaluesource;
    private String appuservaluesourcelocation;
    private String appuservaluefieldname;
    private String appuservaluetitle;
    private Timestamp lastUpdate;
    private Long activeFlag;
    private String userName;	
/*    private String userValue;*/

    // Constructors

    /** default constructor */
    public Appuservalues() {
    }


    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "Appuservalues_SEQ")
    @SequenceGenerator(name = "Appuservalues_SEQ", sequenceName = "Appuservalues_SEQ")
    @Column(name = "APPUSERVALUEID")
    public Long getAppuservalueid() {
        return this.appuservalueid;
    }

    public void setAppuservalueid(Long appuservalueid) {
        this.appuservalueid = appuservalueid;
    }

    @Column(name = "APPUSERVALUESOURCE")
    public String getAppuservaluesource() {
        return this.appuservaluesource;
    }

    public void setAppuservaluesource(String appuservaluesource) {
        this.appuservaluesource = appuservaluesource;
    }

    @Column(name = "APPUSERVALUEFIELDNAME")
    public String getAppuservaluefieldname() {
        return this.appuservaluefieldname;
    }

    public void setAppuservaluefieldname(String appuservaluefieldname) {
        this.appuservaluefieldname = appuservaluefieldname;
    }

    @Column(name = "APPUSERVALUETITLE")
    public String getAppuservaluetitle() {
        return this.appuservaluetitle;
    }

    public void setAppuservaluetitle(String appuservaluetitle) {
        this.appuservaluetitle = appuservaluetitle;
    }
    @Column(name = "APPUSERVALUESOURCELOCATION")
    public String getAppuservaluesourcelocation() {
		return appuservaluesourcelocation;
	}


	public void setAppuservaluesourcelocation(String appuservaluesourcelocation) {
		this.appuservaluesourcelocation = appuservaluesourcelocation;
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

    /*public void setUserValue(String val)
    {
        userValue = val;
    }
    
    public String getUserValue()
    {
        return userValue;
    }*/
    
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
        
        retValue = "Appuservalues ( "
            + "appuservalueid = " + this.appuservalueid + TAB
            + "appuservaluesource = " + this.appuservaluesource + TAB
            + "appuservaluesourcelocation = " + this.appuservaluesourcelocation + TAB
            + "appuservaluefieldname = " + this.appuservaluefieldname + TAB
            + "appuservaluetitle = " + this.appuservaluetitle + TAB
            + "lastUpdate = " + this.lastUpdate + TAB
            + "userName = " + this.userName + TAB
            + "activeFlag = " + this.activeFlag + TAB
            + " )";
    
        return retValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 0;
        result =  ((appuservaluefieldname == null) ? 0
                        : appuservaluefieldname.hashCode());
        result = prime * result
                + ((appuservalueid == null) ? 0 : appuservalueid.hashCode());
        result = prime
                * result
                + ((appuservaluesource == null) ? 0 : appuservaluesource
                        .hashCode());
        result = prime
        * result
        + ((appuservaluesourcelocation == null) ? 0 : appuservaluesourcelocation
                .hashCode());
        result = prime
                * result
                + ((appuservaluetitle == null) ? 0 : appuservaluetitle
                        .hashCode());
        result = prime * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = prime * result + (activeFlag != null ? activeFlag.hashCode() : 0);
        result = prime * result + (userName != null ? userName.hashCode() : 0);

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Appuservalues))
            return false;
        final Appuservalues other = (Appuservalues) obj;
        if (appuservaluefieldname == null) {
            if (other.appuservaluefieldname != null)
                return false;
        } else if (!appuservaluefieldname.equals(other.appuservaluefieldname))
            return false;
        if (appuservalueid == null) {
            if (other.appuservalueid != null)
                return false;
        } else if (!appuservalueid.equals(other.appuservalueid))
            return false;
        if (lastUpdate == null) {
            if (other.lastUpdate != null)
                return false;
        } else if (!lastUpdate.equals(other.lastUpdate))
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        if (appuservaluesource == null) {
            if (other.appuservaluesource != null)
                return false;
        } else if (!appuservaluesource.equals(other.appuservaluesource))
            return false;
        if (appuservaluesourcelocation == null) {
            if (other.appuservaluesourcelocation != null)
                return false;
        } else if (!appuservaluesourcelocation.equals(other.appuservaluesourcelocation))
            return false;
        if (appuservaluetitle == null) {
            if (other.appuservaluetitle != null)
                return false;
        } else if (!appuservaluetitle.equals(other.appuservaluetitle))
            return false;
        if (activeFlag == null) {
            if (other.activeFlag != null)
                return false;
        } else if (!activeFlag.equals(other.activeFlag))
            return false;
        return true;
    }


	

}
