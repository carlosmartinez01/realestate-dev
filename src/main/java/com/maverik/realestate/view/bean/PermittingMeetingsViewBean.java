/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.util.List;

import javax.validation.Valid;

/**
 * @author jorge
 *
 */
public class PermittingMeetingsViewBean {

    private PropertyPermittingBean permitting;

    @Valid
    private List<PropertyMeetingBean> meetings;

    public PropertyPermittingBean getPermitting() {
	return permitting;
    }

    public void setPermitting(PropertyPermittingBean permitting) {
	this.permitting = permitting;
    }

    public List<PropertyMeetingBean> getMeetings() {
	return meetings;
    }

    public void setMeetings(List<PropertyMeetingBean> meetings) {
	this.meetings = meetings;
    }
}
