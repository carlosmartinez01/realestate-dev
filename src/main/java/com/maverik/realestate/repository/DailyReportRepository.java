/**
 * 
 */
package com.maverik.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maverik.realestate.domain.entity.DailyReport;
import com.maverik.realestate.domain.entity.Project;

/**
 * @author jorge
 *
 */
@Repository(value = "dailyReportRepository")
public interface DailyReportRepository extends JpaRepository<DailyReport, Long> {

    List<DailyReport> findDailyReportsByProject(Project project);

}
