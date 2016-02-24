/**
 * 
 */
package com.maverik.realestate.mapper;

import org.springframework.stereotype.Component;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.domain.entity.ProjectPreConstruction;

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

}
