/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.maverik.realestate.domain.entity.DailyReportDetails;
import com.maverik.realestate.view.bean.DailyReportDetailsBean;

/**
 * @author jorge
 *
 */
@Mapper(componentModel = "spring", uses = { CommonMapper.class })
public interface DailyReportDetailsMapper {

    @Mapping(source = "dailyReport", target = "dailyReport", ignore = true)
    DailyReportDetails beanToEntity(DailyReportDetailsBean bean);

    DailyReportDetailsBean entityToBean(DailyReportDetails entity);

    List<DailyReportDetails> beansToEntities(List<DailyReportDetailsBean> beans);

    List<DailyReportDetailsBean> entitiesToBeans(
	    List<DailyReportDetails> entities);

}
