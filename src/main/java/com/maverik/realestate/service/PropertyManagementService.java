package com.maverik.realestate.service;

import java.math.BigDecimal;
import java.util.List;

import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.view.bean.FileBean;
import com.maverik.realestate.view.bean.LeaseAndExtensionBean;
import com.maverik.realestate.view.bean.LeaseExtensionBean;
import com.maverik.realestate.view.bean.PermittingAssignmentTaskBean;
import com.maverik.realestate.view.bean.PermittingContactBean;
import com.maverik.realestate.view.bean.PermittingMeetingsViewBean;
import com.maverik.realestate.view.bean.ProjectBean;
import com.maverik.realestate.view.bean.PropertyBean;
import com.maverik.realestate.view.bean.PropertyContractViewBean;
import com.maverik.realestate.view.bean.PropertyLOIBean;
import com.maverik.realestate.view.bean.PropertyLeaseBean;
import com.maverik.realestate.view.bean.PropertyPermittingBean;
import com.maverik.realestate.view.bean.PropertyPurchaseBean;
import com.maverik.realestate.view.bean.PurchaseAndExtensionWrapBean;
import com.maverik.realestate.view.bean.PurchaseExtensionBean;
import com.maverik.realestate.view.bean.TaskStatusBean;

public interface PropertyManagementService {

    public PropertyBean insertProperty(PropertyBean property)
	    throws GenericException;

    public PropertyBean updateProperty(PropertyBean property)
	    throws GenericException;

    public PropertyBean findByProperty(Long id) throws GenericException;

    public void deleteProperty(PropertyBean property) throws GenericException;

    public List<PropertyBean> findAllProperties() throws GenericException;

    public List<PropertyBean> findPropertiesByResearchPhase()
	    throws GenericException;

    public PropertyBean findByPropertyName(String name) throws GenericException;

    public PropertyBean insertPropertyWithProjects(PropertyBean property,
	    List<ProjectBean> projects) throws GenericException;

    public List<PropertyBean> findPropertiesByContractPhase()
	    throws GenericException;

    public List<PropertyBean> findPropertiesByPermittingPhase()
	    throws GenericException;

    public List<PropertyLOIBean> findLOIByProperty(Long propertyId)
	    throws GenericException;

    public List<PropertyLOIBean> findLOIByAcceptance(Long propertyId,
	    Byte accepted) throws GenericException;

    public Integer saveLOIPrice(Long loiID, BigDecimal price)
	    throws GenericException;

    public Integer acceptLOIPrice(Long loiID, BigDecimal price, Long propertyId)
	    throws GenericException;

    public List<PropertyLeaseBean> findLeaseByAcceptance(Long propertyId,
	    Byte accepted) throws GenericException;

    public List<LeaseExtensionBean> findLeaseExtensionsByLease(Long leaseId)
	    throws GenericException;

    public PropertyLeaseBean saveLease(PropertyLeaseBean lease,
	    List<LeaseExtensionBean> extensions) throws GenericException;

    public PropertyLeaseBean acceptLease(PropertyLeaseBean lease,
	    List<LeaseExtensionBean> extensions) throws GenericException;

    public List<LeaseAndExtensionBean> findAcceptedLease(Long propertyId,
	    Long leaseId) throws GenericException;

    public List<LeaseAndExtensionBean> findNonAcceptedLease(Long propertyId,
	    Long leaseId) throws GenericException;

    public PropertyPurchaseBean savePurchase(PropertyPurchaseBean purchase,
	    List<PurchaseExtensionBean> extensions) throws GenericException;

    public PropertyPurchaseBean acceptPurchase(PropertyPurchaseBean purchase,
	    List<PurchaseExtensionBean> extensions) throws GenericException;

    public List<PurchaseAndExtensionWrapBean> findAcceptedPurchase(
	    Long propertyId, Long purchaseId) throws GenericException;

    public List<PurchaseAndExtensionWrapBean> findNonAcceptedPurchase(
	    Long propertyId, Long purchaseId) throws GenericException;

    public PropertyContractViewBean findPropertyContract(Long id)
	    throws GenericException;

    public PropertyContractViewBean savePropertyContract(
	    PropertyContractViewBean propertyContract) throws GenericException;

    public FileBean uploadPropertyPicture(FileBean fileBean, Long propertyId)
	    throws GenericException;

    public FileBean createLOIAndAddFile(FileBean fileBean, Long propertyId)
	    throws GenericException;

    public PropertyBean createContractAndUpdateProperty(PropertyBean property)
	    throws GenericException;

    public FileBean addRECSOFile(FileBean fileBean, Long contractId)
	    throws GenericException;

    public FileBean addTitleCommitmentFile(FileBean fileBean, Long contractId)
	    throws GenericException;

    public FileBean addTitlePolicyFile(FileBean fileBean, Long contractId)
	    throws GenericException;

    public FileBean addSettlementFile(FileBean fileBean, Long contractId)
	    throws GenericException;

    public FileBean createLeaseAndAddFile(FileBean fileBean, Long propertyId)
	    throws GenericException;

    public FileBean createPurchaseAndAddFile(FileBean fileBean, Long propertyId)
	    throws GenericException;

    public PropertyBean createLandPermittingAndUpdateProperty(
	    PropertyContractViewBean propertyContract) throws GenericException;

    public PermittingMeetingsViewBean findLandPermittingByProperty(Long id)
	    throws GenericException;

    public PermittingMeetingsViewBean savePropertyPermitting(
	    PermittingMeetingsViewBean permitting) throws GenericException;

    public PropertyBean updatePropertyToPermittingTaskPhase(
	    PropertyPermittingBean permitting) throws GenericException;

    public FileBean addPermittingCommitmentFile(FileBean fileBean,
	    Long permittingId) throws GenericException;

    public FileBean addPermittingExceptionFile(FileBean fileBean,
	    Long permittingId) throws GenericException;

    public FileBean addPermittingSurveryFile(FileBean fileBean,
	    Long permittingId) throws GenericException;

    public FileBean addPermittingSitePlanFile(FileBean fileBean,
	    Long permittingId) throws GenericException;

    public FileBean addPermittingGeotechnicalFile(FileBean fileBean,
	    Long permittingId) throws GenericException;

    public FileBean addPermittingTrafficFile(FileBean fileBean,
	    Long permittingId) throws GenericException;

    public List<PermittingAssignmentTaskBean> getAllTaskByProperty(
	    Long propertyId) throws GenericException;

    public PermittingAssignmentTaskBean saveAssignmentTask(
	    PermittingAssignmentTaskBean task) throws GenericException;

    public void markAssignmentTask(TaskStatusBean tasks)
	    throws GenericException;

    public List<String> getTaskAvailableUsersByProperty(Long propertyId)
	    throws GenericException;

    public PermittingContactBean saveContact(PermittingContactBean contact)
	    throws GenericException;

    public List<PermittingContactBean> getAllContactsByProperty(Long propertyId)
	    throws GenericException;

    public List<PropertyBean> findPropertiesByPermittingTasksPhase()
	    throws GenericException;
}
