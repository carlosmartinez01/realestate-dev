<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title></title>
</head>
<body>
	<div id="page">
		<div id="content">
			<div class="post">
				<div class="bg1">
					<div class="bg2">
						<div class="bg3">
							<c:if test="${not empty messageForm}">
								<div class="msg">${messageForm}</div>
							</c:if>
							<br>
							<div class="entry">
								<div class="newPropertyDiv">									
									<a href="<%=request.getContextPath()%>/properties/add" id="create-new-contact"><i class="fa fa-plus fa-lg"></i>
									New Property</a>
								</div>
								<div style="clear: both;">&nbsp;</div>
								<div class="projectNewPhase">New / Research Phase</div>
								<div class="projectNewPhaseContainer">
									<div class="projectList">
										<div class="projectTblRow">
											<div class="projectTblColHdr">Property Name</div>
										</div>
										<c:forEach var="property" items="${requestScope.newProperties }">
											<div class="projectTblRow">
												<div class="projectTblCol">
													<a class="projectNameLink" href="<%=request.getContextPath()%>/properties/${property.id}/summary">
														<c:out value="${property.name}" />
													</a>
												</div>												
											</div>
										</c:forEach>
									</div>
								</div>
								<div style="clear: both;">&nbsp;</div>
								<div class="projectContractPhase">Contract Phase</div>
								<div class="projectNewPhaseContainer">
									<div class="projectList">
										<div class="projectTblRow">
											<div class="projectTblColHdr">Property Name</div>
										</div>
										<c:forEach var="property" items="${requestScope.contractProperties }">
											<div class="projectTblRow">
												<div class="projectTblCol">
													<a class="projectNameLink" href="<%=request.getContextPath()%>/properties/${property.id}/summary">
														<c:out value="${property.name}" />
													</a>
												</div>												
											</div>
										</c:forEach>
									</div>
								</div>
								<div style="clear: both;">&nbsp;</div>
								<div class="projectPermittingPhase">Permitting Land Use Phase</div>
								<div class="projectNewPhaseContainer">
									<div class="projectList">
										<div class="projectTblRow">
											<div class="projectTblColHdr">Property Name</div>
										</div>
										<c:forEach var="property" items="${requestScope.permittingProperties }">
											<div class="projectTblRow">
												<div class="projectTblCol">
													<a class="projectNameLink" href="<%=request.getContextPath()%>/properties/${property.id}/summary">
														<c:out value="${property.name}" />
													</a>
												</div>												
											</div>
										</c:forEach>
									</div>
								</div>
								<div style="clear: both;">&nbsp;</div>
								<div class="projectPermittingPhase">Permitting Tasks Phase</div>
								<div class="projectNewPhaseContainer">
									<div class="projectList">
										<div class="projectTblRow">
											<div class="projectTblColHdr">Property Name</div>
										</div>
										<c:forEach var="property" items="${requestScope.permittingTasksProperties }">
											<div class="projectTblRow">
												<div class="projectTblCol">
													<a class="projectNameLink" href="<%=request.getContextPath()%>/properties/${property.id}/summary">
														<c:out value="${property.name}" />
													</a>
												</div>												
											</div>
										</c:forEach>
									</div>
								</div>
								<div style="clear: both;">&nbsp;</div>
								<div class="projectPreConsPhase">Pre-Construction Phase</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div style="clear: both;">&nbsp;</div>
</body>
</html>


