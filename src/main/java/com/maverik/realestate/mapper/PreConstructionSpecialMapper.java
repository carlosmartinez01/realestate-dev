/**
 * 
 */
package com.maverik.realestate.mapper;

import org.springframework.stereotype.Component;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.domain.entity.Project;
import com.maverik.realestate.domain.entity.ProjectPreConstruction;

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

    public Long asLong(ProjectPreConstruction entity) {

	if (entity == null) {
	    return null;
	}

	return entity.getId();
    }
}
