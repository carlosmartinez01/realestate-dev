/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.maverik.realestate.domain.entity.DailyReport;
import com.maverik.realestate.view.bean.DailyReportBean;

/**
 * @author jorge
 *
 */
@Mapper(componentModel = "spring", uses = { CommonMapper.class })
public interface DailyReportMapper {

    @Mapping(source = "project", target = "project", ignore = true)
    @Mapping(source = "reportedBy", target = "reportedBy", ignore = true)
    DailyReport beanToEntity(DailyReportBean bean);

    DailyReportBean entityToBean(DailyReport entity);

    List<DailyReport> beansToEntities(List<DailyReportBean> beans);

    List<DailyReportBean> entitiesToBeans(List<DailyReport> entities);

}
