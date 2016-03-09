/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.maverik.realestate.domain.entity.PropertyLease;

/**
 * @author jorge
 *
 */
@Mapper(componentModel = "spring")
public interface LeaseToLeaseMapper {

    PropertyLease leaseToLease(PropertyLease lease);

    List<PropertyLease> listOfLeasesToLeases(List<PropertyLease> leases);

}
