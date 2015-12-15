<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<div class="alert alert-default">
	<strong class="lead">Taglibs used: </strong>

	<span class="badge badge-primary">liferay-ui:panel</span>

	<span class="badge badge-primary">liferay-ui:panel-container</span>
</div>

<liferay-ui:panel title="Default Panel">
	Lungo mug, to go qui viennese, mazagran, spoon that affogato espresso robusta at affogato, chicory bar acerbic instant turkish. Instant so frappuccino, extra cafe au lait turkish brewed, mountain a caffeine milk filter turkish so aroma est froth crema americano.
</liferay-ui:panel>

<liferay-ui:panel title="Uncollapsible Panel" collapsible="<%= false %>">
	Lungo mug, to go qui viennese, mazagran, spoon that affogato espresso robusta at affogato, chicory bar acerbic instant turkish. Instant so frappuccino, extra cafe au lait turkish brewed, mountain a caffeine milk filter turkish so aroma est froth crema americano.
</liferay-ui:panel>

<br />

<h3>Panel States</h3>

<liferay-ui:panel-container>
	<liferay-ui:panel title="panel">
		Lungo mug, to go qui viennese, mazagran, spoon that affogato espresso robusta at affogato, chicory bar acerbic instant turkish. Instant so frappuccino, extra cafe au lait turkish brewed, mountain a caffeine milk filter turkish so aroma est froth crema americano.
	</liferay-ui:panel>

	<liferay-ui:panel cssClass="panel-blank" title="panel-blank">
		Lungo mug, to go qui viennese, mazagran, spoon that affogato espresso robusta at affogato, chicory bar acerbic instant turkish. Instant so frappuccino, extra cafe au lait turkish brewed, mountain a caffeine milk filter turkish so aroma est froth crema americano.
	</liferay-ui:panel>

	<liferay-ui:panel cssClass="panel-default" title="panel-default">
		Lungo mug, to go qui viennese, mazagran, spoon that affogato espresso robusta at affogato, chicory bar acerbic instant turkish. Instant so frappuccino, extra cafe au lait turkish brewed, mountain a caffeine milk filter turkish so aroma est froth crema americano.
	</liferay-ui:panel>

	<liferay-ui:panel cssClass="panel-primary" title="panel-primary">
		Lungo mug, to go qui viennese, mazagran, spoon that affogato espresso robusta at affogato, chicory bar acerbic instant turkish. Instant so frappuccino, extra cafe au lait turkish brewed, mountain a caffeine milk filter turkish so aroma est froth crema americano.
	</liferay-ui:panel>

	<liferay-ui:panel cssClass="panel-success" title="panel-success">
		Lungo mug, to go qui viennese, mazagran, spoon that affogato espresso robusta at affogato, chicory bar acerbic instant turkish. Instant so frappuccino, extra cafe au lait turkish brewed, mountain a caffeine milk filter turkish so aroma est froth crema americano.
	</liferay-ui:panel>

	<liferay-ui:panel cssClass="panel-warning" title="panel-warning">
		Lungo mug, to go qui viennese, mazagran, spoon that affogato espresso robusta at affogato, chicory bar acerbic instant turkish. Instant so frappuccino, extra cafe au lait turkish brewed, mountain a caffeine milk filter turkish so aroma est froth crema americano.
	</liferay-ui:panel>

	<liferay-ui:panel cssClass="panel-danger" title="panel-danger">
		Lungo mug, to go qui viennese, mazagran, spoon that affogato espresso robusta at affogato, chicory bar acerbic instant turkish. Instant so frappuccino, extra cafe au lait turkish brewed, mountain a caffeine milk filter turkish so aroma est froth crema americano.
	</liferay-ui:panel>
</liferay-ui:panel-container>

<br />

<h3>Accordion Panel Group</h3>

<liferay-ui:panel-container accordion="<%= true %>">
	<liferay-ui:panel state="open" title="Panel #1">
		Lungo mug, to go qui viennese, mazagran, spoon that affogato espresso robusta at affogato, chicory bar acerbic instant turkish. Instant so frappuccino, extra cafe au lait turkish brewed, mountain a caffeine milk filter turkish so aroma est froth crema americano.
	</liferay-ui:panel>

	<liferay-ui:panel state="close" title="Panel #2">
		Lungo mug, to go qui viennese, mazagran, spoon that affogato espresso robusta at affogato, chicory bar acerbic instant turkish. Instant so frappuccino, extra cafe au lait turkish brewed, mountain a caffeine milk filter turkish so aroma est froth crema americano.
	</liferay-ui:panel>

	<liferay-ui:panel state="close" title="Panel #3">
		Lungo mug, to go qui viennese, mazagran, spoon that affogato espresso robusta at affogato, chicory bar acerbic instant turkish. Instant so frappuccino, extra cafe au lait turkish brewed, mountain a caffeine milk filter turkish so aroma est froth crema americano.
	</liferay-ui:panel>
</liferay-ui:panel-container>

<br />

<h3>Lexicon View</h3>

<liferay-ui:panel markupView="lexicon" title="Default Panel">
	Lungo mug, to go qui viennese, mazagran, spoon that affogato espresso robusta at affogato, chicory bar acerbic instant turkish. Instant so frappuccino, extra cafe au lait turkish brewed, mountain a caffeine milk filter turkish so aroma est froth crema americano.
</liferay-ui:panel>

<liferay-ui:panel markupView="lexicon" title="Uncollapsible Panel" collapsible="<%= false %>">
	Lungo mug, to go qui viennese, mazagran, spoon that affogato espresso robusta at affogato, chicory bar acerbic instant turkish. Instant so frappuccino, extra cafe au lait turkish brewed, mountain a caffeine milk filter turkish so aroma est froth crema americano.
</liferay-ui:panel>

<liferay-ui:panel-container markupView="lexicon">
	<liferay-ui:panel markupView="lexicon" title="panel">
		Lungo mug, to go qui viennese, mazagran, spoon that affogato espresso robusta at affogato, chicory bar acerbic instant turkish. Instant so frappuccino, extra cafe au lait turkish brewed, mountain a caffeine milk filter turkish so aroma est froth crema americano.
	</liferay-ui:panel>

	<liferay-ui:panel cssClass="panel-primary" markupView="lexicon" title="panel-primary">
		Lungo mug, to go qui viennese, mazagran, spoon that affogato espresso robusta at affogato, chicory bar acerbic instant turkish. Instant so frappuccino, extra cafe au lait turkish brewed, mountain a caffeine milk filter turkish so aroma est froth crema americano.
	</liferay-ui:panel>
</liferay-ui:panel-container>

<liferay-ui:panel-container accordion="<%= true %>" markupView="lexicon">
	<liferay-ui:panel markupView="lexicon" state="open" title="Panel #1">
		Lungo mug, to go qui viennese, mazagran, spoon that affogato espresso robusta at affogato, chicory bar acerbic instant turkish. Instant so frappuccino, extra cafe au lait turkish brewed, mountain a caffeine milk filter turkish so aroma est froth crema americano.
	</liferay-ui:panel>

	<liferay-ui:panel markupView="lexicon" state="close" title="Panel #2">
		Lungo mug, to go qui viennese, mazagran, spoon that affogato espresso robusta at affogato, chicory bar acerbic instant turkish. Instant so frappuccino, extra cafe au lait turkish brewed, mountain a caffeine milk filter turkish so aroma est froth crema americano.
	</liferay-ui:panel>

	<liferay-ui:panel markupView="lexicon" state="close" title="Panel #3">
		Lungo mug, to go qui viennese, mazagran, spoon that affogato espresso robusta at affogato, chicory bar acerbic instant turkish. Instant so frappuccino, extra cafe au lait turkish brewed, mountain a caffeine milk filter turkish so aroma est froth crema americano.
	</liferay-ui:panel>
</liferay-ui:panel-container>
