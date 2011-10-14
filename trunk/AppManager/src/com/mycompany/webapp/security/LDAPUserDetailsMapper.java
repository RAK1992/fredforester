package com.mycompany.webapp.security;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;

import com.mycompany.model.AuthUser;

public class LDAPUserDetailsMapper extends LdapUserDetailsMapper {

	protected final Log logger = LogFactory.getLog(getClass());
	private String[] roleAttributes = null;

	@Override
	public UserDetails mapUserFromContext(DirContextOperations context,String userName, Collection<GrantedAuthority> authorities) {

		logger.debug("mapUserFromContext start");
		AuthUser user = new AuthUser();

		user.setUsername(userName);
		user.setEmail(context.getStringAttribute("email"));
		user.setFirstName(context.getStringAttribute("givenname"));
		user.setLastName(context.getStringAttribute("sn"));
		user.setPhoneNumber(context.getStringAttribute("telephonenumber"));
		// Map the roles
		for (int i = 0; (roleAttributes != null) && (i < roleAttributes.length); i++) {
			String[] rolesForAttribute = context
					.getStringAttributes(roleAttributes[i]);

			if (rolesForAttribute == null) {
				logger.debug("Couldn't read role attribute '"
						+ roleAttributes[i] + "' for user " + userName);
				continue;
			}

			for (int j = 0; j < rolesForAttribute.length; j++) {
				GrantedAuthority authority = createAuthority(rolesForAttribute[j]);

				if (authority != null) {
					logger.debug("Authority " + authority.getAuthority());
				}
			}
		}
		user.setAuthorities(authorities);
		logger.debug("mapUserFromContext end with user " + user);
		return user;
	}

}
