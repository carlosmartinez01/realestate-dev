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

import com.maverik.realestate.domain.entity.ArchitectDrawingDetails;
import com.maverik.realestate.domain.entity.ProjectPreConstruction;
import com.maverik.realestate.view.bean.ArchitectDrawingDetailsBean;

/**
 * @author jorge
 *
 */
@Component
public class ArchitectDrawingSpecialMapper {

    public Long asLong(ProjectPreConstruction entity) {
	if (entity == null) {
	    return null;
	}

	return entity.getId();
    }

    public List<ArchitectDrawingDetailsBean> asListOfBeans(
	    List<ArchitectDrawingDetails> entities) {
	if (entities == null) {
	    return null;
	}

	List<ArchitectDrawingDetailsBean> beans = new ArrayList<ArchitectDrawingDetailsBean>();
	for (ArchitectDrawingDetails entity : entities) {
	    beans.add(asArchitectDrawingDetailsBean(entity));
	}

	return beans;
    }

    public ArchitectDrawingDetailsBean asArchitectDrawingDetailsBean(
	    ArchitectDrawingDetails entity) {
	if (entity == null) {
	    return null;
	}

	ArchitectDrawingDetailsBean bean = new ArchitectDrawingDetailsBean();
	bean.setId(entity.getId());
	bean.setArchitectDrawingId(entity.getArchitectDrawing().getId());
	DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");
	DateTime time = new DateTime(entity.getDrawingDate());
	bean.setDrawingDate(dtf.print(time));

	return bean;
    }

    public List<ArchitectDrawingDetails> asListOfEntities(
	    List<ArchitectDrawingDetailsBean> beans) {
	if (beans == null) {
	    return null;
	}

	List<ArchitectDrawingDetails> entities = new ArrayList<ArchitectDrawingDetails>();
	for (ArchitectDrawingDetailsBean bean : beans) {
	    entities.add(asArchitectDrawingDetails(bean));
	}

	return entities;
    }

    public ArchitectDrawingDetails asArchitectDrawingDetails(
	    ArchitectDrawingDetailsBean bean) {
	if (bean == null) {
	    return null;
	}

	ArchitectDrawingDetails entity = new ArchitectDrawingDetails();
	entity.setId(bean.getId());
	DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");
	DateTime time = dtf.parseDateTime(bean.getDrawingDate());
	entity.setDrawingDate(time.toDate());

	return entity;
    }
}
