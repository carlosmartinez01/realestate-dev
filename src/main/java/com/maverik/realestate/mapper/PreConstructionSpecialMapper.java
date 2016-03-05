/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.domain.entity.PreConstructionType;
import com.maverik.realestate.domain.entity.PreConstructionTypeDetails;
import com.maverik.realestate.domain.entity.Project;
import com.maverik.realestate.view.bean.PreConstructionTypeBean;
import com.maverik.realestate.view.bean.PreConstructionTypeDetailsBean;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
@Component
public class PreConstructionSpecialMapper {

    @Autowired
    private PreConstructionDetailsSpecialMapper fileMapper;

    public Long asLong(Project entity) {
	if (entity == null) {
	    return null;
	}

	return entity.getId();
    }

    public PreConstructionType asPreConstructionType(
	    PreConstructionTypeBean bean) {
	if (bean == null) {
	    return null;
	}
	PreConstructionType entity = new PreConstructionType();
	entity.setChecked(bean.getChecked());
	entity.setConstructionDocumentType(bean.getConstructionDocumentType());
	entity.setContactName(bean.getContactName());
	entity.setTypeDetails(asEntityList(bean.getTypeDetails()));
	entity.setId(bean.getId());

	return entity;
    }

    public PreConstructionTypeBean asPreConstructionTypeBean(
	    PreConstructionType entity) {
	if (entity == null) {
	    return null;
	}
	PreConstructionTypeBean bean = new PreConstructionTypeBean();
	bean.setChecked(entity.getChecked());
	bean.setConstructionDocumentType(entity.getConstructionDocumentType());
	bean.setContactName(entity.getContactName());
	bean.setTypeDetails(asBeanList(entity.getTypeDetails()));
	bean.setId(entity.getId());

	return bean;
    }

    public List<PreConstructionTypeDetails> asEntityList(
	    List<PreConstructionTypeDetailsBean> beans) {
	if (beans == null) {
	    return null;
	}
	List<PreConstructionTypeDetails> temp = new ArrayList<PreConstructionTypeDetails>();
	for (PreConstructionTypeDetailsBean bean : beans) {
	    temp.add(asPreConstructionTypeDetails(bean));
	}

	return temp;
    }

    public PreConstructionTypeDetailsBean asPreConstructionTypeDetailsBean(
	    PreConstructionTypeDetails entity) {
	if (entity == null) {
	    return null;
	}
	PreConstructionTypeDetailsBean bean = new PreConstructionTypeDetailsBean();
	DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");
	DateTime time = new DateTime(entity.getDateReceived());
	bean.setDateReceived(dtf.print(time));
	bean.setFilename(fileMapper.asFileBean(entity.getFilename()));
	bean.setId(entity.getId());

	return bean;
    }

    public List<PreConstructionTypeDetailsBean> asBeanList(
	    List<PreConstructionTypeDetails> entities) {
	if (entities == null) {
	    return null;
	}
	List<PreConstructionTypeDetailsBean> temp = new ArrayList<PreConstructionTypeDetailsBean>();
	for (PreConstructionTypeDetails entity : entities) {
	    temp.add(asPreConstructionTypeDetailsBean(entity));
	}

	return temp;
    }

    public PreConstructionTypeDetails asPreConstructionTypeDetails(
	    PreConstructionTypeDetailsBean bean) {
	if (bean == null) {
	    return null;
	}
	PreConstructionTypeDetails entity = new PreConstructionTypeDetails();
	DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");
	DateTime time = dtf.parseDateTime(bean.getDateReceived());
	entity.setDateReceived(time.toDate());
	entity.setFilename(fileMapper.asFile(bean.getFilename()));
	entity.setId(bean.getId());

	return entity;
    }
}
