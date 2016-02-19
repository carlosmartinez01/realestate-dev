<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="tblColContract">
	<div class="contractBox">
		<div class="tblRowContract">
			<div class="tblColContract">LOI</div>
		</div>
		<div class="tblRowContract">
			<div class="tblColContract">Accepted</div>
			<div class="tblColContract">Date</div>
		</div>
		<div class="tblRowContract">
		<c:forEach var="acceptedLst" items="${requestScope.loiAcceptedLst }">
			<div class="tblColContract"><c:out value="${acceptedLst.fileId.name}" /></div>
			<div class="tblColContract"><c:out value="${acceptedLst.creationTime}" /></div>
			<div class="tblColContract">
				<button id="${acceptedLst.id}-${acceptedLst.propertyId}" class="clssLOI" value="${acceptedLst.price}">View</button>
			</div>
		</c:forEach>
		</div>
		<div class="tblRowContract">
			<div class="tblColContract">Non-Accepted</div>
		</div>
		<c:forEach var="nonAcceptedLst" items="${requestScope.loiNonAcceptedLst }">
		<div class="tblRowContract">
			<div class="tblColContract"><c:out value="${nonAcceptedLst.fileId.name}" /></div>
			<div class="tblColContract"><c:out value="${nonAcceptedLst.creationTime}" /></div>
			<div class="tblColContract">
				<button id="${nonAcceptedLst.id}-${nonAcceptedLst.propertyId}" class="clssLOI" value="${nonAcceptedLst.price}">Accept</button>
			</div>
		</div>
		</c:forEach>
		<br>
		<div class="tblRowContract">
			<input type="file" id="loiFile" value="Choose File" />
			<button id="loiUploadFile">Upload</button>
		</div>
	</div>
</div>