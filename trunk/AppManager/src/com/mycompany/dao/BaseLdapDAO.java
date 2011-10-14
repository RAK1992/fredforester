package com.mycompany.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.ldap.core.LdapTemplate;

import sun.misc.BASE64Encoder;

public class BaseLdapDAO {
	
	protected LdapTemplate ldapTemplate;

    protected String objectClassPerson;
    protected String base;
    protected String groupBase;
    protected String userOu;
    protected String groupSearch;
    protected String objectClassForPerson;
    protected String groupAttribute;

    public void setBase(String base) {
        this.base = base;
    }

    public void setGroupSearch(String groupSearch) {
        this.groupSearch = groupSearch;
    }

    public void setObjectClassPerson(String objectClassPerson) {
        this.objectClassPerson = objectClassPerson;
        this.objectClassForPerson = "objectclass=" + this.objectClassPerson;
    }

    public void setUserOu(String userOu) {
        this.userOu = userOu;
    }

    public String getGroupAttribute() {
        return groupAttribute;
    }

    public void setGroupAttribute(String groupAttribute) {
        this.groupAttribute = groupAttribute;
    }

    public String getGroupBase() {
        return groupBase;
    }

    public void setGroupBase(String groupBase) {
        this.groupBase = groupBase;
    }

    public void setLdapTemplate(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    protected String encryptSHA(final String plaintext) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
        try {
            md.update(plaintext.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
        byte raw[] = md.digest();
        String hash = (new BASE64Encoder()).encode(raw);
        return hash;
    }

}
