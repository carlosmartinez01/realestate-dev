/**
 * 
 */
package com.maverik.realestate.mapper;

import org.springframework.stereotype.Component;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.domain.entity.Filename;
import com.maverik.realestate.domain.entity.ProjectPreConstruction;
import com.maverik.realestate.view.bean.FileBean;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
@Component
public class PreConstructionDetailsSpecialMapper {

    public Long asLong(ProjectPreConstruction entity) {

	if (entity == null) {
	    return null;
	}

	return entity.getId();
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
