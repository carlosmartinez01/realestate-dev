/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.maverik.realestate.domain.entity.ArchitectDrawing;
import com.maverik.realestate.view.bean.ArchitectDrawingBean;

/**
 * @author jorge
 *
 */
@Mapper(componentModel = "spring", uses = { ArchitectDrawingSpecialMapper.class })
public interface ArchitectDrawingMapper {

    ArchitectDrawing beanToEntity(ArchitectDrawingBean bean);

    ArchitectDrawingBean entityToBean(ArchitectDrawing entity);

    List<ArchitectDrawing> beansToEntities(List<ArchitectDrawingBean> beans);

    List<ArchitectDrawingBean> entitiesToBeans(List<ArchitectDrawing> entities);
}
