/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.domain.entity.PreConstructionDetails;
import com.maverik.realestate.domain.entity.Project;
import com.maverik.realestate.view.bean.PreConstructionDetailsBean;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
@Component
public class PreConstructionSpecialMapper {

    public Long asLong(Project entity) {
	if (entity == null) {
	    return null;
	}

	return entity.getId();
    }

    private PreConstructionDetails asPreConstructionDetails(
	    PreConstructionDetailsBean bean) {
	if (bean == null) {
	    return null;
	}
	PreConstructionDetails entity = new PreConstructionDetails();
	DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");
	DateTime time = dtf.parseDateTime(bean.getDateReceived());
	entity.setDateReceived(time.toDate());
	entity.setFilename(bean.getFilename());
	entity.setId(bean.getId());

	return entity;
    }

    private PreConstructionDetailsBean asPreConstructionDetailsBean(
	    PreConstructionDetails entity) {
	if (entity == null) {
	    return null;
	}
	PreConstructionDetailsBean bean = new PreConstructionDetailsBean();
	DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");
	DateTime time = new DateTime(entity.getDateReceived());
	bean.setDateReceived(dtf.print(time));
	bean.setFilename(entity.getFilename());
	bean.setId(entity.getId());

	return bean;
    }

    public List<PreConstructionDetails> asEntityList(
	    List<PreConstructionDetailsBean> beans) {
	if (beans == null) {
	    return null;
	}
	List<PreConstructionDetails> temp = new ArrayList<PreConstructionDetails>();
	for (PreConstructionDetailsBean bean : beans) {
	    temp.add(asPreConstructionDetails(bean));
	}

	return temp;
    }

    public List<PreConstructionDetailsBean> asBeanList(
	    List<PreConstructionDetails> entities) {
	if (entities == null) {
	    return null;
	}
	List<PreConstructionDetailsBean> temp = new ArrayList<PreConstructionDetailsBean>();
	for (PreConstructionDetails entity : entities) {
	    temp.add(asPreConstructionDetailsBean(entity));
	}

	return temp;
    }
}
