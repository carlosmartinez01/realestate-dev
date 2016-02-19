/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.maverik.realestate.domain.entity.PropertyMeeting;
import com.maverik.realestate.view.bean.PropertyMeetingBean;

/**
 * @author jorge
 *
 */
@Mapper(componentModel = "spring", uses = { PermittingMeetingsMapper.class,
	PropertySpecialMapper.class })
public interface MeetingsMapper {

    PropertyMeeting propertyMeetingBeanToPropertyMeeting(
	    PropertyMeetingBean propertyMeetingBean);

    PropertyMeetingBean propertyMeetingToPropertyMeetingBean(
	    PropertyMeeting propertyMeeting);

    List<PropertyMeeting> listOfPropertyMeetingBeansToListOfPropertiesMeeting(
	    List<PropertyMeetingBean> propertyMeetingBeans);

    List<PropertyMeetingBean> listOfPropertiesMeetingToListOfPropertyMeetingBeans(
	    List<PropertyMeeting> propertiesMeetings);

}
