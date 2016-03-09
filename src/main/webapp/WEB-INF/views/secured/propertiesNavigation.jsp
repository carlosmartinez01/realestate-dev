<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title></title>
<script
	src="<%=request.getContextPath()%>/resources/jquery/external/jquery/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/jquery/jquery-ui.js"></script>

<style>
.accordion-expand-holder {
	margin: 10px 0;
}

.accordion-expand-holder .open, .accordion-expand-holder .close {
	margin: 0 10px 0 0;
}

.sidebar-at-left #sidebar {
	margin-right: 1%;
}

.sidebar-at-right #sidebar {
	margin-left: 1%;
}

.sidebar-at-left #boxContent, .use-sidebar.sidebar-at-right #sidebar,
	.sidebar-at-right #separator {
	float: right;
}

.sidebar-at-right #boxContent, .use-sidebar.sidebar-at-left #sidebar,
	.sidebar-at-left #separator {
	float: left;
}

.use-sidebar #boxContent {
	width: 72%;
}

.use-sidebar #sidebar {
	display: block;
	width: 23%;
}

#separator {
	background-color: #EEE;
	border: 1px solid #CCC;
	display: block;
	outline: none;
	width: 1%;
}

.use-sidebar #separator {
	border-color: #FFF;
}

</style>

<script>
	$(function() {
		// run the currently selected effect
		function runEffect(x) {
			// get effect type from
			var selectedEffect = $("#" + x).val();
			if (selectedEffect === "hide") {
				hideBar();
			} else if (selectedEffect === "show") {
				showBar();
			}
		}
		;

		function hideBar() {
			// run the effect        
			$("#effect").hide('slide', 1000);
			setTimeout(function() {
				removeDiv();
			}, 1001);
			function removeDiv() {
				// Variables
				var objMain = $('#main');
				objMain.removeClass('use-sidebar');
			}
		}
		;

		function showBar() {
			// run the effect
			// Variables
			var objMain = $('#main');
			objMain.addClass('use-sidebar');
			setTimeout(function() {
				addDiv();
			}, 1);
			function addDiv() {
				$("#effect").show('slide', 500);
			}

		}
		;

		// set effect from select menu value
		$('.buttonList').on('click', '.ui-state-default', function() {
			runEffect($(this).attr("id"));
		});
	});
</script>

<script>
	//Accordion - Expand All #01
	$(function() {
		$("#accordion").accordion({
			collapsible : true,
			active : false
		});
		var icons = $("#accordion").accordion("option", "icons");
		$('.open')
				.click(
						function() {
							$('.ui-accordion-header')
									.removeClass('ui-corner-all')
									.addClass(
											'ui-accordion-header-active ui-state-active ui-corner-top')
									.attr({
										'aria-selected' : 'true',
										'tabindex' : '0'
									});
							$('.ui-accordion-header-icon').removeClass(
									icons.header)
									.addClass(icons.headerSelected);
							$('.ui-accordion-content').addClass(
									'ui-accordion-content-active').attr({
								'aria-expanded' : 'true',
								'aria-hidden' : 'false'
							}).show();
							$(this).attr("disabled", "disabled");
							$('.close').removeAttr("disabled");
						});
		$('.close')
				.click(
						function() {
							$('.ui-accordion-header')
									.removeClass(
											'ui-accordion-header-active ui-state-active ui-corner-top')
									.addClass('ui-corner-all').attr({
										'aria-selected' : 'false',
										'tabindex' : '-1'
									});
							$('.ui-accordion-header-icon').removeClass(
									icons.headerSelected)
									.addClass(icons.header);
							$('.ui-accordion-content').removeClass(
									'ui-accordion-content-active').attr({
								'aria-expanded' : 'false',
								'aria-hidden' : 'true'
							}).hide();
							$(this).attr("disabled", "disabled");
							$('.open').removeAttr("disabled");
						});
		$('.ui-accordion-header').click(function() {
			$('.open').removeAttr("disabled");
			$('.close').removeAttr("disabled");

		});
	});
</script>
</head>
<body>
	<div class="buttonList">
		<button id="buttonHide" value="hide" type="button" title="HIDE MENU"
			class="ui-state-default ui-corner-all">&lt;&lt;</button>
		<button id="buttonShow" value="show" type="button" title="SHOW MENU"
			class="ui-state-default ui-corner-all">&gt;&gt;</button>
	</div>
	<div style="clear: both;">&nbsp;</div>

	<div class="use-sidebar sidebar-at-left" id="main">

		<div id="boxContent">
			<div id="page">
			<div id="content">
				<div class="post">
					<div class="bg1">
						<div class="bg2">
							<div class="bg3">
								<div class="entry">
									&lt;&lt; Hide the left menu <br>
									&gt;&gt; Show the left menu
									<p>${projectMsg}.</p>
									<br>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
		</div>

		<div id="sidebar">
			<jsp:include page="${request.contextPath}/projects/menuNavigation"></jsp:include>
		</div>

	</div>

	<div style="clear: both;">&nbsp;</div>
</body>
</html>