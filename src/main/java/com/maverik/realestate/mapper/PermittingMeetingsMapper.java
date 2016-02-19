/**
 * 
 */
package com.maverik.realestate.mapper;

import org.springframework.stereotype.Component;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.domain.entity.PropertyPermitting;

/**
 * @author jorge
 *
 */
@Component
@SonarClassExclusion
public class PermittingMeetingsMapper {

    public PropertyPermitting asEntity(Long id) {

	if (id == null) {
	    return null;
	}

	PropertyPermitting p = new PropertyPermitting();
	p.setId(id);

	return p;
    }

    public Long asLong(PropertyPermitting entity) {

	if (entity == null) {
	    return null;
	}

	return entity.getId();
    }
}
