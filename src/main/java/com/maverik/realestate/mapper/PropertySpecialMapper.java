/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.domain.entity.Filename;
import com.maverik.realestate.domain.entity.Property;
import com.maverik.realestate.view.bean.FileBean;

/**
 * @author jorge
 *
 */
@Component
@SonarClassExclusion
public class PropertySpecialMapper {

    public Property asProperty(Long id) {

	if (id == null) {
	    return null;
	}

	Property property = new Property();
	property.setId(id);

	return property;
    }

    public Long asLong(Property property) {

	if (property == null) {
	    return null;
	}

	return property.getId();
    }

    public String asString(Date date) {

	if (date == null) {
	    return null;
	}

	DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");
	DateTime time = new DateTime(date);

	return dtf.print(time);
    }

    public Date asDate(String date) {

	if (date == null || date.isEmpty()) {
	    return null;
	}

	DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");
	DateTime time = dtf.parseDateTime(date);

	return time.toDate();
    }

    public Filename asFile(FileBean bean) {

	if (bean == null) {
	    return null;
	}

	Filename file = new Filename();
	file.setId(bean.getId());
	file.setName(bean.getName());
	file.setAbsolutePath(bean.getAbsolutePath());

	return file;
    }

    public FileBean asFileBean(Filename file) {

	if (file == null) {
	    return null;
	}

	FileBean bean = new FileBean();
	bean.setId(file.getId());
	bean.setName(file.getName());
	bean.setAbsolutePath(file.getAbsolutePath());

	return bean;
    }
}
