/**
 * 
 */
package com.maverik.realestate.test;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

/**
 * @author jorge
 *
 */
public class MockSecurityContext implements SecurityContext {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private Authentication authentication;
    
    public MockSecurityContext(Authentication authentication) {
        this.authentication = authentication;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.core.context.SecurityContext#getAuthentication()
     */
    @Override
    public Authentication getAuthentication() {
	return this.authentication;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.core.context.SecurityContext#setAuthentication(org.springframework.security.core.Authentication)
     */
    @Override
    public void setAuthentication(Authentication authentication) {
	this.authentication = authentication;
    }

}
