/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.util.List;

/**
 * @author jorge
 *
 */
public class LeaseAndExtensionBean {

    private PropertyLeaseBean lease;

    private List<LeaseExtensionBean> extension;

    public PropertyLeaseBean getLease() {
	return lease;
    }

    public void setLease(PropertyLeaseBean lease) {
	this.lease = lease;
    }

    public List<LeaseExtensionBean> getExtension() {
	return extension;
    }

    public void setExtension(List<LeaseExtensionBean> extension) {
	this.extension = extension;
    }

}
