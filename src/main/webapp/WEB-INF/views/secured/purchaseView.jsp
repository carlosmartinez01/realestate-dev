<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="tblColContract">
	<div class="contractBox">
		<div class="tblRowContract">
			<div class="tblColContract">Purchase</div>
		</div>
		<div class="tblRowContract">
			<div class="tblColContract">Accepted</div>
			<div class="tblColContract">Date</div>
		</div>
		<div class="tblRowContract">
		<c:forEach var="acceptedPurchaseLst" items="${requestScope.purchaseAcceptedLst }">
			<div class="tblColContract"><c:out value="${acceptedPurchaseLst.purchase.fileId.name}" /></div>
			<div class="tblColContract"><c:out value="${acceptedPurchaseLst.purchase.creationTime}" /></div>
			<div class="tblColContract">
				<button id="${acceptedPurchaseLst.purchase.id}-${acceptedPurchaseLst.purchase.propertyId}-purchaseButton" class="clssPurchase" value="">View</button>
				<input type="hidden" value="${acceptedPurchaseLst.purchase.effectiveDate}" id="${acceptedPurchaseLst.purchase.id}-effectiveDate">
				<input type="hidden" value="${acceptedPurchaseLst.purchase.price}" id="${acceptedPurchaseLst.purchase.id}-pprice">
				<input type="hidden" value="${acceptedPurchaseLst.purchase.dueDiligenceTerm}" id="${acceptedPurchaseLst.purchase.id}-pddterm">
				<c:forEach var="acceptedPurchaseExtLst" items="${acceptedPurchaseLst.extensions }">
				<input type="hidden" value="${acceptedPurchaseExtLst.extensionDays}" class="${acceptedPurchaseLst.purchase.id}-pextDaysVal" id="${acceptedPurchaseExtLst.id}-pextDays">
				</c:forEach>
				<input type="hidden" value="${acceptedPurchaseLst.purchase.deadLineDate}" id="${acceptedPurchaseLst.purchase.id}-pddeadline">
				<input type="hidden" value="${acceptedPurchaseLst.purchase.closingTerm}" id="${acceptedPurchaseLst.purchase.id}-closingTerm">
				<input type="hidden" value="${acceptedPurchaseLst.purchase.closingDate}" id="${acceptedPurchaseLst.purchase.id}-closingDate">		
			</div>
		</c:forEach>
		</div>
		<div class="tblRowContract">
			<div class="tblColContract">Non-Accepted</div>
		</div>
		<c:forEach var="nonAcceptedPurchaseLst" items="${requestScope.purchaseNonAcceptedLst }">
		<div class="tblRowContract">
			<div class="tblColContract"><c:out value="${nonAcceptedPurchaseLst.purchase.fileId.name}" /></div>
			<div class="tblColContract"><c:out value="${nonAcceptedPurchaseLst.purchase.creationTime}" /></div>
			<div class="tblColContract">
				<button id="${nonAcceptedPurchaseLst.purchase.id}-${nonAcceptedPurchaseLst.purchase.propertyId}-purchaseButton" class="clssPurchase" value="">Accept</button>
				<input type="hidden" value="${nonAcceptedPurchaseLst.purchase.effectiveDate}" id="${nonAcceptedPurchaseLst.purchase.id}-effectiveDate">
				<input type="hidden" value="${nonAcceptedPurchaseLst.purchase.price}" id="${nonAcceptedPurchaseLst.purchase.id}-pprice">
				<input type="hidden" value="${nonAcceptedPurchaseLst.purchase.dueDiligenceTerm}" id="${nonAcceptedPurchaseLst.purchase.id}-pddterm">
				<c:forEach var="nonAcceptedPurchaseExtLst" items="${nonAcceptedPurchaseLst.extensions }">
				<input type="hidden" value="${nonAcceptedPurchaseExtLst.extensionDays}" class="${nonAcceptedPurchaseLst.purchase.id}-pextDaysVal" id="${nonAcceptedPurchaseExtLst.id}-pextDays">
				</c:forEach>
				<input type="hidden" value="${nonAcceptedPurchaseLst.purchase.deadLineDate}" id="${nonAcceptedPurchaseLst.purchase.id}-pddeadline">
				<input type="hidden" value="${nonAcceptedPurchaseLst.purchase.closingTerm}" id="${nonAcceptedPurchaseLst.purchase.id}-closingTerm">
				<input type="hidden" value="${nonAcceptedPurchaseLst.purchase.closingDate}" id="${nonAcceptedPurchaseLst.purchase.id}-closingDate">
			</div>
		</div>
		</c:forEach>
		<br>
		<div class="tblRowContract">
			<input type="file" id="purchaseFile" value="Choose File" />
			<button id="purchaseUploadFile">Upload</button>
		</div>
	</div>
</div>