package com.mycompany.webapp.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;


public class ACLdapAuthoritiesPopulator implements LdapAuthoritiesPopulator
{
	protected final Log log = LogFactory.getLog(getClass());
    DefaultLdapAuthoritiesPopulator defaultLdapPopulator;
    private String[] adGroups;
    private String[] appRoles;
    private String[] xtraRoles;
    private String defaultRole;
    
    private String groupSearchFilter;


	public ACLdapAuthoritiesPopulator(DefaultSpringSecurityContextSource didcf,String groupSearchBase) {
    	
    	defaultLdapPopulator = new DefaultLdapAuthoritiesPopulator(didcf,groupSearchBase);
	}
    
	public void setGroupSearchFilter(String groupSearchFilter) {
		defaultLdapPopulator.setGroupSearchFilter(groupSearchFilter);
		this.groupSearchFilter = groupSearchFilter;
	}

	public void setGroupRoleAttribute(String groupRoleAttribute) {
        defaultLdapPopulator.setGroupRoleAttribute(groupRoleAttribute);
    }

    public void setAdGroupMap(String adGroupStr) {
        if (adGroupStr != null && adGroupStr.length() > 0)
        {
            String[] groups = adGroupStr.split(",");
            if (groups != null && groups.length > 0)
            {
                adGroups = new String[groups.length];
                System.arraycopy(groups,0,adGroups,0,groups.length);
            }
        }
        
    }
    
    public void setAppRoleMap(String appRolesStr) {
        if (appRolesStr != null && appRolesStr.length() > 0)
        {
            String[] roles = appRolesStr.split(",");
            if (roles != null && roles.length > 0)
            {
                appRoles = new String[roles.length];
                System.arraycopy(roles,0,appRoles,0,roles.length);
            }
        }
        
    }
    public void setXtraRoles(String xtraRolesStr) {
        if (xtraRolesStr != null && xtraRolesStr.length() > 0)
        {
            String[] roles = xtraRolesStr.split(",");
            if (roles != null && roles.length > 0)
            {
                xtraRoles = new String[roles.length];
                System.arraycopy(roles,0,xtraRoles,0,roles.length);
            }
        }
        
    }

    public Set<GrantedAuthority> getAdditionalRoles(Collection<GrantedAuthority> gas) {

        if (gas == null || gas.isEmpty())
            return null;
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(GrantedAuthority ga : gas)
        {
            String role = ga.getAuthority();
            log.debug("CHECKING ROLE " + role);
            int index = getAdGroupIndex(role);
            if (index >= 0)
            {
                role = appRoles[index];
                log.debug("MATCHED ROLE TO " + role);
                authorities.add(new GrantedAuthorityImpl(role));
            }
        }   
        if (authorities.size() > 0)
        {
            if (xtraRoles != null && xtraRoles.length > 0)
            {
                for(int a=0;a<xtraRoles.length;a++)
                    authorities.add(new GrantedAuthorityImpl(xtraRoles[a]));
            }
        }
        if (defaultRole != null && defaultRole.length() > 0)
        {
            log.debug("Setting defaultRole");
            authorities.add(new GrantedAuthorityImpl(defaultRole));
        }
        return authorities; 
    }
    
    public Set<GrantedAuthority> getGroupMembershipRoles(String userDn, String username) 
    {
    	Set<GrantedAuthority> gas = defaultLdapPopulator.getGroupMembershipRoles(userDn,username);
    	
    	for(GrantedAuthority ga : gas)
    	{
    		log.debug("GroupMembershipRole " + ga.getAuthority());
    	}
        return gas;
    }

    public final Collection<GrantedAuthority> getGrantedAuthorities(DirContextOperations userDetails, String user) {
        
    	Collection<GrantedAuthority> gas = defaultLdapPopulator.getGrantedAuthorities(userDetails,user);
        Set<GrantedAuthority> roles = getAdditionalRoles(gas);
        if (roles == null)
        {
        	roles = new HashSet<GrantedAuthority>();
            log.debug("No Roles Found.");
            // if we have a default role the user can login anyway
            if (defaultRole != null && defaultRole.length() > 0)
            {
                log.debug("Setting default role " + defaultRole);
                roles.add(new GrantedAuthorityImpl(defaultRole));
            }
            else
            {
            	// no default role means user auth fails
            	log.debug("Setting failed role ROLE_FAIL");
                roles.add(new GrantedAuthorityImpl("ROLE_FAIL"));
            }
        }
        
        if (log.isDebugEnabled())
        {
        	if (roles != null && roles.size() > 0)
        	{
        		log.debug("Roles Assigned");
        		for(GrantedAuthority ga : roles)
        		{
        			log.debug("Role " + ga.getAuthority());
        		}
        	}
        	else
        		log.debug("No Roles Assigned");
        }
        
        return roles;
    }

    private int getAdGroupIndex(String group)
    {
        if (adGroups == null || adGroups.length == 0)
            return -1;
        for(int i=0;i<adGroups.length;i++)
        {
            if (adGroups[i].equals(group))
                return i;
        }
        return -1;
    }
    
    public void setDefaultRole(String defaultRole) {
    	log.debug("Default Role " + defaultRole);
        this.defaultRole = defaultRole;
    }

}
