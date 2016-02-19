/**
 * 
 */
package com.maverik.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maverik.realestate.domain.entity.Page;

/**
 * @author jorge
 *
 */
public interface PageRepository extends JpaRepository<Page, Long> {

    Page findByPageName(String name);
}
