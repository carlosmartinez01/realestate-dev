/**
 * 
 */
package com.maverik.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maverik.realestate.domain.entity.Filename;

/**
 * @author jorge
 *
 */
public interface FilenameRepository extends JpaRepository<Filename, Long> {

}
