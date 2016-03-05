/**
 * 
 */
package com.maverik.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maverik.realestate.domain.entity.Page;

/**
 * @author jorge
 *
 */
@Repository
public interface PageRepository extends JpaRepository<Page, Long> {

    Page findByPageName(String name);
}
