<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="tblColContract">
	<div class="contractBox">
		<div class="tblRowContract">
			<div class="tblColContract">Lease Offfer</div>
		</div>
		<div class="tblRowContract">
			<div class="tblColContract">Accepted</div>
			<div class="tblColContract">Date</div>
		</div>
		<div class="tblRowContract">
		<c:forEach var="acceptedLeaseLst" items="${requestScope.leaseAcceptedLst }">
			<div class="tblColContract"><c:out value="${acceptedLeaseLst.lease.fileId.name}" /></div>
			<div class="tblColContract"><c:out value="${acceptedLeaseLst.lease.creationTime}" /></div>
			<div class="tblColContract">
				<button id="${acceptedLeaseLst.lease.id}-${acceptedLeaseLst.lease.propertyId}-leaseButton" class="clssLease" value="">View</button>
				<input type="hidden" value="${acceptedLeaseLst.lease.landlord}" id="${acceptedLeaseLst.lease.id}-landlord">
				<input type="hidden" value="${acceptedLeaseLst.lease.leaseAmount}" id="${acceptedLeaseLst.lease.id}-leaseAmount">
				<input type="hidden" value="${acceptedLeaseLst.lease.rentCommencementDate}" id="${acceptedLeaseLst.lease.id}-rent">
				<input type="hidden" value="${acceptedLeaseLst.lease.deadLineDate}" id="${acceptedLeaseLst.lease.id}-ddeadline">
				<input type="hidden" value="${acceptedLeaseLst.lease.dueDiligenceTerm}" id="${acceptedLeaseLst.lease.id}-diligenceTerm">
				<input type="hidden" value="${acceptedLeaseLst.lease.initialTerm}" id="${acceptedLeaseLst.lease.id}-iniTerm">
				<input type="hidden" value="${acceptedLeaseLst.lease.numberOfExtensions}" id="${acceptedLeaseLst.lease.id}-numberOfExtensions">
				<input type="hidden" value="${acceptedLeaseLst.lease.extensionLengthOfYears}" id="${acceptedLeaseLst.lease.id}-lengthOfYearsExt">
				<input type="hidden" value="${acceptedLeaseLst.lease.rofr}" id="${acceptedLeaseLst.lease.id}-rofr">
				<input type="hidden" value="${acceptedLeaseLst.lease.rofo}" id="${acceptedLeaseLst.lease.id}-rofo">
				<input type="hidden" value="${acceptedLeaseLst.lease.purchaseOption}" id="${acceptedLeaseLst.lease.id}-purchaseOption">
				<input type="hidden" value="${acceptedLeaseLst.lease.purchaseOptionAfter}" id="${acceptedLeaseLst.lease.id}-purchaseOptionAfter">
				<c:forEach var="acceptedLeaseExtLst" items="${acceptedLeaseLst.extension }">
				<input type="hidden" value="${acceptedLeaseExtLst.extensionDays}" class="${acceptedLeaseLst.lease.id}-extDaysVal" id="${acceptedLeaseExtLst.id}-extDays">
				</c:forEach>
			</div>
		</c:forEach>
		</div>
		<div class="tblRowContract">
			<div class="tblColContract">Non-Accepted</div>
		</div>
		<c:forEach var="nonAcceptedLeaseLst" items="${requestScope.leaseNonAcceptedLst }">
		<div class="tblRowContract">
			<div class="tblColContract"><c:out value="${nonAcceptedLeaseLst.lease.fileId.name}" /></div>
			<div class="tblColContract"><c:out value="${nonAcceptedLeaseLst.lease.creationTime}" /></div>
			<div class="tblColContract">
				<button id="${nonAcceptedLeaseLst.lease.id}-${nonAcceptedLeaseLst.lease.propertyId}-leaseButton" class="clssLease" value="">Accept</button>
				<input type="hidden" value="${nonAcceptedLeaseLst.lease.landlord}" id="${nonAcceptedLeaseLst.lease.id}-landlord">
				<input type="hidden" value="${nonAcceptedLeaseLst.lease.leaseAmount}" id="${nonAcceptedLeaseLst.lease.id}-leaseAmount">
				<input type="hidden" value="${nonAcceptedLeaseLst.lease.rentCommencementDate}" id="${nonAcceptedLeaseLst.lease.id}-rent">
				<input type="hidden" value="${nonAcceptedLeaseLst.lease.deadLineDate}" id="${nonAcceptedLeaseLst.lease.id}-ddeadline">
				<input type="hidden" value="${nonAcceptedLeaseLst.lease.dueDiligenceTerm}" id="${nonAcceptedLeaseLst.lease.id}-diligenceTerm">
				<input type="hidden" value="${nonAcceptedLeaseLst.lease.initialTerm}" id="${nonAcceptedLeaseLst.lease.id}-iniTerm">
				<input type="hidden" value="${nonAcceptedLeaseLst.lease.numberOfExtensions}" id="${nonAcceptedLeaseLst.lease.id}-numberOfExtensions">
				<input type="hidden" value="${nonAcceptedLeaseLst.lease.extensionLengthOfYears}" id="${nonAcceptedLeaseLst.lease.id}-lengthOfYearsExt">
				<input type="hidden" value="${nonAcceptedLeaseLst.lease.rofr}" id="${nonAcceptedLeaseLst.lease.id}-rofr">
				<input type="hidden" value="${nonAcceptedLeaseLst.lease.rofo}" id="${nonAcceptedLeaseLst.lease.id}-rofo">
				<input type="hidden" value="${nonAcceptedLeaseLst.lease.purchaseOption}" id="${nonAcceptedLeaseLst.lease.id}-purchaseOption">
				<input type="hidden" value="${nonAcceptedLeaseLst.lease.purchaseOptionAfter}" id="${nonAcceptedLeaseLst.lease.id}-purchaseOptionAfter">
				<c:forEach var="nonAcceptedLeaseExtLst" items="${nonAcceptedLeaseLst.extension }">
				<input type="hidden" value="${nonAcceptedLeaseExtLst.extensionDays}" class="${nonAcceptedLeaseLst.lease.id}-extDaysVal" id="${nonAcceptedLeaseExtLst.id}-extDays">
				</c:forEach>
			</div>
		</div>
		</c:forEach>
		<br>
		<div class="tblRowContract">
			<input type="file" id="leaseFile" value="Choose File" />
			<button id="leaseUploadFile">Upload</button>
		</div>
	</div>
</div>