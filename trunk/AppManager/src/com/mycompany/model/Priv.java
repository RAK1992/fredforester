package com.mycompany.model;

import com.mycompany.model.BaseObject;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Type;

import java.sql.Timestamp;


@Entity
@Table(name="PRIV")
public class Priv extends BaseObject implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -6118623278004948230L;
    private Long privNo;
    private String privId;
    private String privDpt;
    private String privUrl;
    private String privDesc;
    private String privDescExt;
    private Boolean privShowmenu;
    private Timestamp lastUpdate;
    private Long activeFlag;
    private String userName;
    private Long isforcedredirect;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="Priv_SEQ")
    @SequenceGenerator(name="Priv_SEQ", sequenceName="Priv_SEQ")
    @Column(name="PRIV_NO")
    public Long getPrivNo() {
        return this.privNo;
    }
    
    public void setPrivNo(Long privNo) {
        this.privNo = privNo;
    }
     
    @Column(name="PRIV_ID", nullable=false, length=50) 
    public String getPrivId() {
        return this.privId;
    }
    
    public void setPrivId(String privId) {
        this.privId = privId;
        if (this.privId != null)
        	this.privId = this.privId.toUpperCase();
    }
     
    @Column(name="PRIV_DPT", length=50) 
    public String getPrivDpt() {
        return this.privDpt;
    }
    
    public void setPrivDpt(String privDpt) {
        this.privDpt = privDpt;
    }
     
    @Column(name="PRIV_URL", length=256) 
    public String getPrivUrl() {
        return this.privUrl;
    }
    
    public void setPrivUrl(String privUrl) {
        this.privUrl = privUrl;
    }
     
    @Column(name="PRIV_DESC", length=200) 
    public String getPrivDesc() {
        return this.privDesc;
    }
    
    public void setPrivDesc(String privDesc) {
        this.privDesc = privDesc;
    }
     
    @Column(name="PRIV_DESC_EXT", length=1000) 
    public String getPrivDescExt() {
        return this.privDescExt;
    }
    
    public void setPrivDescExt(String privDescExt) {
        this.privDescExt = privDescExt;
    }
     
    @Column(name="PRIV_SHOWMENU", length=1)
    @Type(type="yes_no")
    public Boolean getPrivShowmenu() {
        return this.privShowmenu;
    }
    
    public void setPrivShowmenu(Boolean privShowmenu) {
        this.privShowmenu = privShowmenu;
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
     
    @Column(name="ISFORCEDREDIRECT", nullable=false, precision=3, scale=0) 
    public Long getIsforcedredirect() {
        return this.isforcedredirect;
    }
    
    public void setIsforcedredirect(Long isforcedredirect) {
        this.isforcedredirect = isforcedredirect;
    }
    

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Priv pojo = (Priv) o;

        if (privId != null ? !privId.equals(pojo.privId) : pojo.privId != null) return false;
        if (privDpt != null ? !privDpt.equals(pojo.privDpt) : pojo.privDpt != null) return false;
        if (privUrl != null ? !privUrl.equals(pojo.privUrl) : pojo.privUrl != null) return false;
        if (privDesc != null ? !privDesc.equals(pojo.privDesc) : pojo.privDesc != null) return false;
        if (privDescExt != null ? !privDescExt.equals(pojo.privDescExt) : pojo.privDescExt != null) return false;
        if (privShowmenu != null ? !privShowmenu.equals(pojo.privShowmenu) : pojo.privShowmenu != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(pojo.lastUpdate) : pojo.lastUpdate != null) return false;
        if (activeFlag != null ? !activeFlag.equals(pojo.activeFlag) : pojo.activeFlag != null) return false;
        if (userName != null ? !userName.equals(pojo.userName) : pojo.userName != null) return false;
        if (isforcedredirect != null ? !isforcedredirect.equals(pojo.isforcedredirect) : pojo.isforcedredirect != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (privId != null ? privId.hashCode() : 0);
        result = 31 * result + (privDpt != null ? privDpt.hashCode() : 0);
        result = 31 * result + (privUrl != null ? privUrl.hashCode() : 0);
        result = 31 * result + (privDesc != null ? privDesc.hashCode() : 0);
        result = 31 * result + (privDescExt != null ? privDescExt.hashCode() : 0);
        result = 31 * result + (privShowmenu != null ? privShowmenu.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (activeFlag != null ? activeFlag.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (isforcedredirect != null ? isforcedredirect.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("privNo").append("='").append(getPrivNo()).append("', ");
        sb.append("privId").append("='").append(getPrivId()).append("', ");
        sb.append("privDpt").append("='").append(getPrivDpt()).append("', ");
        sb.append("privUrl").append("='").append(getPrivUrl()).append("', ");
        sb.append("privDesc").append("='").append(getPrivDesc()).append("', ");
        sb.append("privDescExt").append("='").append(getPrivDescExt()).append("', ");
        sb.append("privShowmenu").append("='").append(getPrivShowmenu()).append("', ");
        sb.append("lastUpdate").append("='").append(getLastUpdate()).append("', ");
        sb.append("activeFlag").append("='").append(getActiveFlag()).append("', ");
        sb.append("userName").append("='").append(getUserName()).append("', ");
        sb.append("isforcedredirect").append("='").append(getIsforcedredirect()).append("', ");
        
        
        sb.append("]");
      
        return sb.toString();
    }

}

