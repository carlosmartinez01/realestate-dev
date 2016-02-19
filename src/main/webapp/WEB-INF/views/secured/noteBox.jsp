<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>

<script type="text/javascript">
$(document).ready(function(){
	$("#noteBox").hide();
    $("#toggleNote").click(function(){
        $("#noteBox").toggle();
    });
});
</script>
<c:if test="${not empty noteMessageAdded}">
	<div class="msg">${noteMessageAdded}</div>
</c:if>
<br>
<div class="entry">
	<input type="hidden" id="addNoteUrl"
		value="<%=request.getContextPath()%>/note/${requestScope.oType}/${requestScope.oID}/${requestScope.pageId}/${requestScope.username}/addNote">
	<button class="bttnAction" id="toggleNote">Note</button>
	<div style="clear: both;">&nbsp;</div>
	<div id="noteBox">
		<div class="objListDetails">
			<div class="tblRowDetails">
				<div class="tblColDetails">
					<textarea id="notesText" rows="10" cols="50"></textarea>
				</div>
			</div>
		</div>
		<br>
		<button class="bttnAction" id="saveNote">Save Note</button>
	</div>
</div>