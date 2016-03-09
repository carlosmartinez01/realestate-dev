<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
<head>
<title></title>
<script>
function companyMain() {
window.location="<%=request.getContextPath()%>/company";
	}
</script>
<style>
label, input {
	display: block;
}

input.text {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 25px;
}

h1 {
	font-size: 1.2em;
	margin: .6em 0;
}

div#users-contain {
	width: 350px;
	margin: 20px 0;
}

div#users-contain table {
	margin: 1em 0;
	border-collapse: collapse;
	width: 100%;
}

div#users-contain table td, div#users-contain table th {
	border: 1px solid #eee;
	padding: .6em 10px;
	text-align: left;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
}
</style>
<script>
	$(function() {
		var dialog, form, v_password = $("#f_password"), confirmPassword = $("#confirmPassword"), allFields = $(
				[]).add(v_password).add(confirmPassword), tips = $(".validateTips");

		function updateTips(t) {
			tips.text(t).addClass("ui-state-highlight");
			setTimeout(function() {
				tips.removeClass("ui-state-highlight", 1500);
			}, 500);
		}

		function checkLength(o, n, min, max) {
			if (o.val().length > max || o.val().length < min) {
				o.addClass("ui-state-error");
				updateTips("Length of " + n + " must be between " + min
						+ " and " + max + ".");
				return false;
			} else {
				return true;
			}
		}

		function checkRegexp(o, regexp, n) {
			if (!(regexp.test(o.val()))) {
				o.addClass("ui-state-error");
				updateTips(n);
				return false;
			} else {
				return true;
			}
		}

		function checkMatch(p, c, n) {
			if (p.val() === c.val()) {
				return true;
			} else {
				p.addClass("ui-state-error");
				updateTips(n);
				return false;
			}
		}

		function addUser() {
			var valid = true;
			allFields.removeClass("ui-state-error");
			valid = valid && checkLength(v_password, "password", 6, 80);

			valid = valid
					&& checkRegexp(v_password, /^([0-9a-zA-Z])+$/,
							"Password field only allow : a-z 0-9");

			valid = valid
					&& checkMatch(v_password, confirmPassword,
							"Password doesn't match");

			if (valid) {
				submitForm();
			}
			return valid;
		}

		function submitForm() {
			var parameters = {
				r_password : v_password.val()
			};
			$
					.ajax({
						url : '/realestate/profile/${userProfileView.id }/changePassword',
						data : parameters,
						type : "POST",
						statusCode : {
							200 : function(data) {
								if (data.toUpperCase().indexOf("FAIL") > -1) {
									$('#displayMsg').html(data).attr('class',
											'error');
								} else {
									$('#displayMsg').html(data).attr('class',
											'msg');
								}
							}
						}
					});
		}

		dialog = $("#dialog-form").dialog({
			autoOpen : false,
			height : "auto",
			width : "auto",
			modal : true,
			buttons : {
				"Save" : addUser,
				Cancel : function() {
					dialog.dialog("close");
				}
			},
			close : function() {
				form[0].reset();
				allFields.removeClass("ui-state-error");
				$('#displayMsg').removeClass('msg');
				$('#displayMsg').removeClass('error');
				$('#displayMsg').html('');
			}
		});

		form = dialog.find("form").on("submit", function(event) {
			event.preventDefault();
			addUser();
		});

		$("#create-user").button().on("click", function() {
			dialog.dialog("open");
			return false;
		});
	});
</script>
</head>
<body>
	<div id="page">
		<div id="content">
			<div class="post">
				<div class="bg1">
					<div class="bg2">
						<div class="bg3">
							<h2 class="title">Account Information</h2>
							<c:if test="${not empty updateMessage}">
								<div class="msg">${updateMessage}</div>
							</c:if>
							<br>
							<div class="entry">

								<div id="dialog-form" title="Setup Password">
									<p class="validateTips">Type your new password.</p>


									<div id="displayMsg">${messageForm}</div>


									<form>
										<fieldset>
											<label for="password">Password</label> <input type="password"
												name="password" id="f_password"
												class="text ui-widget-content ui-corner-all"> <label
												for="confirmPassword">Confirm Password</label> <input
												type="password" name="confirmPassword" id="confirmPassword"
												class="text ui-widget-content ui-corner-all">

											<!-- Allow form submission with keyboard without duplicating the dialog button -->
											<input type="submit" tabindex="-1"
												style="position: absolute; top: -1000px">
										</fieldset>
									</form>
								</div>

								<c:choose>
									<c:when test="${action=='Add'}">
										<spring:url value="/profile/addUserToCompany" var="profileUrl" />
									</c:when>
									<c:otherwise>
										<spring:url value="/profile/updateProfile" var="profileUrl" />
									</c:otherwise>
								</c:choose>

								<form:form method="POST" action="${profileUrl }"
									commandName="userProfileView">
									<div class="objListDetails">
										<div class="tblRowDetails">
											<div class="tblColDetails">UserName</div>
											<%-- <td align="left"><b><c:out value="${UserProfile.username}"></c:out></b></td> --%>
											<c:choose>
												<c:when test="${action=='Add'}">
													<div class="tblColBoxDetails">
														<form:input path="username" type="text"
															cssClass="genericBox" />
													</div>
													<div class="tblColErrorDetails">
														<form:errors path="username" cssClass="errorValMsg"
															element="div" />
													</div>
												</c:when>
												<c:otherwise>
													<div class="tblColBoxDetails">
														<form:input path="username" type="text" readonly="true"
															cssClass="genericBox" />
													</div>
												</c:otherwise>
											</c:choose>
											<%-- <td class="formTables"><form:errors path="username" cssClass="errorValMsg" element="div" /></td> --%>
										</div>
										<div class="tblRowDetails">
											<div class="tblColDetails">Password</div>
											<c:choose>
												<c:when test="${action=='Add'}">
													<div class="tblColBoxDetails">
														<form:password path="password"
															value="${userProfileView.password}" id="passHtml"
															cssClass="genericBox" />
													</div>
													<div class="tblColErrorDetails">
														<form:errors path="password" cssClass="errorValMsg"
															element="div" />
													</div>
												</c:when>
												<c:otherwise>
													<div class="tblColBoxDetails">
														<button id="create-user">Edit</button>
														<form:input path="password" type="hidden" readonly="true"
															cssClass="genericBox" />
													</div>
												</c:otherwise>
											</c:choose>
										</div>
										<div class="tblRowDetails">
											<div class="tblColDetails">Email</div>
											<div class="tblColBoxDetails">
												<form:input path="email" type="text" cssClass="genericBox" />
											</div>
											<div class="tblColErrorDetails">
												<form:errors path="email" cssClass="errorValMsg"
													element="div" />
											</div>
										</div>
										<div class="tblRowDetails">
											<div class="tblColDetails">First Name</div>
											<div class="tblColBoxDetails">
												<form:input path="firstName" type="text"
													cssClass="genericBox" />
											</div>
											<div class="tblColErrorDetails">
												<form:errors path="firstName" cssClass="errorValMsg"
													element="div" />
											</div>
										</div>
										<div class="tblRowDetails">
											<div class="tblColDetails">Last Name</div>
											<div class="tblColBoxDetails">
												<form:input path="lastName" type="text"
													cssClass="genericBox" />
											</div>
											<div class="tblColErrorDetails">
												<form:errors path="lastName" cssClass="errorValMsg"
													element="div" />
											</div>
										</div>
										<div class="tblRowDetails">
											<div class="tblColDetails">Cell Phone</div>
											<div class="tblColBoxDetails">
												<form:input path="cellPhone" type="text"
													cssClass="genericBox" />
											</div>
											<div class="tblColErrorDetails">
												<form:errors path="cellPhone" cssClass="errorValMsg"
													element="div" />
											</div>
										</div>
										<div class="tblRowDetails">
											<div class="tblColDetails">Office Phone</div>
											<div class="tblColBoxDetails">
												<form:input path="officePhone" type="text"
													cssClass="genericBox" />
											</div>
											<div class="tblColErrorDetails">
												<form:errors path="officePhone" cssClass="errorValMsg"
													element="div" />
											</div>
										</div>
										<c:choose>
											<c:when test="${action=='Add'}">
												<div class="tblRowDetails">
													<div class="tblColDetails">Role</div>
													<div class="tblColDetails">
														<select name="role" style="width: 180px">
															<option value="Default">--- Select ---</option>
															<c:forEach items="${requestScope.lstRoles }" var="role">
																<option value="${role.roleName }">${role.roleName }</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="tblRowDetails">
													<div class="tblColDetails">Company Name</div>
													<div class="tblColBoxDetails">
														<form:input path="company.companyName" type="text"
															readonly="true" cssClass="genericBox" />
													</div>
													<div class="tblColErrorDetails">
														<form:errors path="company.companyName"
															cssClass="errorValMsg" element="div" />
													</div>
												</div>
												<div class="tblRowDetails">
													<div class="tblColDetails">Address</div>
													<div class="tblColBoxDetails">
														<form:input path="company.address" readonly="true"
															type="text" cssClass="genericBox" />
													</div>
													<div class="tblColErrorDetails">
														<form:errors path="company.address" cssClass="errorValMsg"
															element="div" />
													</div>
												</div>
												<div class="tblRowDetails">
													<div class="tblColDetails">State</div>
													<div class="tblColBoxDetails">
														<form:input path="company.state" readonly="true"
															type="text" cssClass="genericBox" />
													</div>
													<div class="tblColErrorDetails">
														<form:errors path="company.state" cssClass="errorValMsg"
															element="div" />
													</div>
												</div>
												<div class="tblRowDetails">
													<div class="tblColDetails">Zip Code</div>
													<div class="tblColBoxDetails">
														<form:input path="company.zipCode" readonly="true"
															type="text" cssClass="genericBox" />
													</div>
													<div class="tblColErrorDetails">
														<form:errors path="company.zipCode" cssClass="errorValMsg"
															element="div" />
													</div>
												</div>
											</c:when>
										</c:choose>

									</div>
									<br>
									<c:choose>
										<c:when test="${pageAction=='Add'}">
											<input class="bttnAction" type="button" value="&lt;&#45;Back"
												onclick="javascript:companyMain()" />
											<input type="hidden" name="companyId"
												value="${userProfileView.company.id }" />
											<input class="bttnAction" type="submit" value="Add User" />
										</c:when>
										<c:otherwise>
											<input type="hidden" name="userId"
												value="${userProfileView.id }" />
											<input class="bttnAction" type="submit"
												value="Update Information" />
										</c:otherwise>
									</c:choose>
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>