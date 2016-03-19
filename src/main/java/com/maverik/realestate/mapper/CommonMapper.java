/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maverik.realestate.domain.entity.DailyReport;
import com.maverik.realestate.domain.entity.Filename;
import com.maverik.realestate.domain.entity.Project;
import com.maverik.realestate.domain.entity.User;
import com.maverik.realestate.view.bean.FileBean;
import com.maverik.realestate.view.bean.UserBean;

/**
 * @author jorge
 *
 */
@Component
public class CommonMapper {

    @Autowired
    private UserActiveMapper userActiveMapper;

    public Long asLong(Project entity) {
	if (entity == null) {
	    return null;
	}

	return entity.getId();
    }

    public Long asLong(DailyReport entity) {
	if (entity == null) {
	    return null;
	}

	return entity.getId();
    }

    public Project asProject(Long id) {
	if (id == null) {
	    return null;
	}

	Project project = new Project();
	project.setId(id);

	return project;
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

    public UserBean asUserBean(User user) {
	if (user == null) {
	    return null;
	}

	UserBean userBean = new UserBean();
	userBean.setRoles(userActiveMapper.asSetString(user.getUserRoles()));
	userBean.setUsername(user.getUsername());
	userBean.setPassword(user.getPassword());
	userBean.setFirstName(user.getFirstName());
	userBean.setLastName(user.getLastName());
	userBean.setEmail(user.getEmail());
	userBean.setActive(userActiveMapper.asBoolean(user.getActive()));
	userBean.setId(user.getId());
	userBean.setCellPhone(user.getCellPhone());
	userBean.setOfficePhone(user.getOfficePhone());
	userBean.setCompany(userActiveMapper.asCompanyBean(user.getCompany()));

	return userBean;
    }
}
