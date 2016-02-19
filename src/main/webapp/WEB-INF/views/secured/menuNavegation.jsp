<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

<div class="toggler">
  <div id="effect" class="ui-widget-content ui-corner-all">
<div class="projHeaderContainer">
<div class="projNavHeader">
Property Navigation
</div>
<div class="projNavBody">
Property Summary
</div>
</div>

<div id="buttonsAccordion" class="accordion-expand-holder">
	<button type="button" class="open">&nbsp;&nbsp;Expand all</button>
	<button type="button" class="close">Collapse all</button>
</div>

<div id="accordion">
	<h3 class="header3"><span class="accordeon-header-font">Real Estate</span></h3>
	<div>
	<p><a href="<%=request.getContextPath()%>/properties/${sessionScope.propertyOID}/research">Research</a></p>		
		<br>
		<a href="<%=request.getContextPath()%>/properties/${sessionScope.propertyOID}/contract">Contract</a>
	</div>
	<h3 class="header3"><span class="accordeon-header-font">Permitting &#38; Licensing</span></h3>
	<div>
		<p><a href="<%=request.getContextPath()%>/properties/${sessionScope.propertyOID}/permitting">Land Use Permitting</a></p>
		<br>
		<a href="<%=request.getContextPath()%>/properties/${sessionScope.propertyOID}/permitting/tasks">Permitting Tasks</a>
	</div>
	<h3 class="header3"><span class="accordeon-header-font">Construction</span></h3>
	<div>
		<a href="<%=request.getContextPath()%>/projects/${sessionScope.propertyOID}/preconstruction">Pre Construction</a>
	</div>
	<h3 class="header3"><span class="accordeon-header-font">Construction Tools</span></h3>
	<div>
		<p class="menuText">Nam enim risus, molestie et, porta ac, aliquam
			ac, risus. Quisque lobortis. Phasellus pellentesque purus in massa.
			</p>
		<ul class="menuText">
			<li>List item one</li>
			<li>List item two</li>
			<li>List item three</li>
			<li>List item two</li>
			<li>List item three</li>
			<li>List item two</li>
			<li>List item three</li>
			<li>List item two</li>
			<li>List item three</li>
		</ul>
	</div>
	<h3 class="header3"><span class="accordeon-header-font">Folders</span></h3>
	<div>
		<p class="menuText">Cras dictum. Pellentesque habitant morbi
			tristique senectus et netus et malesuada fames ac turpis egestas.
			Vestibulum ante ipsum primis in faucibus orci luctus et.</p>
	</div>
</div>


 </div>
</div>
