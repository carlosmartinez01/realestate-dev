/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
public class ActiveUser extends User {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private String userFullName;

    public ActiveUser(String username, String password, boolean enabled,
	    boolean accountNonExpired, boolean credentialsNonExpired,
	    boolean accountNonLocked,
	    Collection<? extends GrantedAuthority> authorities) {
	super(username, password, enabled, accountNonExpired,
		credentialsNonExpired, accountNonLocked, authorities);
    }

    public String getUserFullName() {
	return userFullName;
    }

    @Override
    public int hashCode() {
	return Objects.hash(super.getUsername(), this.userFullName);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final ActiveUser other = (ActiveUser) obj;
	return Objects.equals(this.userFullName, other.userFullName)
		&& Objects.equals(super.getUsername(), other.getUsername());
    }

    public void setUserFullName(String userFullName) {
	this.userFullName = userFullName;
    }

    @Override
    public String toString() {
	return "ActiveUser [userFullName=" + userFullName + ", getUsername()="
		+ getUsername() + "]";
    }
}
