<#if has_navigation && is_setup_complete>
	<button aria-controls="navigationCollapse" aria-expanded="false" aria-label="Toggle navigation" class="navbar-toggler navbar-toggler-right" data-target="#navigationCollapse" data-toggle="liferay-collapse" type="button">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div aria-expanded="false" class="collapse navbar-collapse" id="navigationCollapse">
		<#assign preferences = freeMarkerPortletPreferences.getPreferences({
			"portletSetupPortletDecoratorId": "barebone",
			"displayStyle": "ddmTemplate_NAVBAR_CLASSIC-FTL"
		}) />

		<@liferay.navigation_menu default_preferences="${preferences}" />
	</div>
</#if>