AUI.add(
	'liferay-dockbar-my-sites-search',
	function(A) {
		var NAME = 'mysitessearch';

		var DockbarMySitesSearch = A.Component.create(
			{
				EXTENDS: A.Plugin.Base,
				NAME: NAME,
				NS: NAME,
				prototype: {
					initializer: function() {
						var instance = this;

						var mySitesMenuDropdown = A.one('.my-sites.dropdown');

						if (mySitesMenuDropdown) {
							var mySitesMenu = mySitesMenuDropdown.one('.my-sites-menu');
							var mySitesMenuTrigger = mySitesMenuDropdown.one('.dropdown-toggle');

							var mySitesCount = A.Lang.toInt(mySitesMenu.getData('sitescount'), 10);
							var mySitesMax = A.Lang.toInt(mySitesMenu.getData('sitesmax'), 10);

							if (mySitesCount > mySitesMax) {
								mySitesMenuTrigger.once(
									'menuOpen',
									function(event) {
										instance._initSearch();

										instance._bindScroll();
									}
								);

								mySitesMenuTrigger.on(
									'menuOpen',
									function(event) {
										mySitesMenuTrigger.one('.search-query').focus();
									}
								);

								instance.mySitesMenu = mySitesMenu;
								instance.mySitesMenuTrigger = mySitesMenuTrigger;
								instance.mySitesMenuDropdown = mySitesMenuDropdown;
								instance.mySitesCount = mySitesCount;
								instance.mySitesMax = mySitesMax;
							}
						}
					},

					_initSearch: function() {
						var instance = this;

						var mySitesMenu = instance.mySitesMenu;

						var mySitesMenuTrigger = instance.mySitesMenuTrigger;

						var userSites = mySitesMenu.all('.user-site');

						var mySitesMax = instance.mySitesMax;

						var searchMySites = A.Node.create('<input class="search-query" placeholder="Search" type="text" autocomplete="false" />');

						var moreResults = A.Node.create('<li class="more-results">' +
								'<a class="hide" href="javascript:;">Loading More Results...</a>' +
							'</li>'
						);

						instance.mySitesMenuDropdown.addClass('site-search');

						mySitesMenu.append(moreResults);

						mySitesMenuTrigger.append(searchMySites);

						searchMySites.on(
							'gesturemovestart',
							function(event) {
								event.stopPropagation();
							}
						);

						var userSiteHeight = userSites.item(0).outerHeight();

						var mySitesMenuHeight = (userSiteHeight * mySitesMax);

						mySitesMenu.setStyle('height', mySitesMenuHeight + 'px');
						mySitesMenu.setStyle('width', mySitesMenu.width());

						var SiteFilter = A.Base.create(
							'siteFilter',
							A.Base,
							[A.AutoCompleteBase],
							{
								initializer: function () {
									this._bindUIACBase();
									this._syncUIACBase();
								}
							}
						);

						var filter = new SiteFilter(
							{
								inputNode: searchMySites,
								minQueryLength: 0,
								source: function (query, callback) {
									Liferay.Service(
										'$userGroups = /group/search',
										{
											companyId: themeDisplay.getCompanyId(),
											description: '',
											end: mySitesMax,
											name: query,
											params: null,
											start: 0,
											'$publicLayouts = /layout/get-layouts': {
												'@groupId': '$userGroups.groupId',
												privateLayout: false
											},
											'$privateLayouts = /layout/get-layouts': {
												'@groupId': '$userGroups.groupId',
												privateLayout: true
											}
										},
										callback
									);
								},
								on: {
									results: function (event) {
										var results = event.results;

										if (results) {
											mySitesMenu.all('.user-site').remove();

											var queryResults = instance._getQueryResultsHTML(event.query, results);

											mySitesMenu.insertBefore(queryResults, moreResults);

											moreResults.toggle(results.length >= (mySitesMax - 1));
										}

										instance.index = 0;
									}
								},
								resultFilters: 'phraseMatch',
								resultTextLocator: 'name',
								queryDelay: 0
							}
						);

						instance._RESULTS = {
							'': userSites.outerHTML().join('')
						};

						instance.moreResults = moreResults;
						instance.searchMySites = searchMySites;
					},

					_bindScroll: function() {
						var instance = this;

						var lastScrollTop = 0;
						var loadingLock = false;

						var moreResults = instance.moreResults;
						var mySitesMenu = instance.mySitesMenu;
						var mySitesMax = instance.mySitesMax;
						var searchMySites = instance.searchMySites;

						instance.index = 0;

						mySitesMenu.on(
							'scroll',
							function(event) {
								var moreResultsToShow = !moreResults.hasClass('hide');

								if (moreResultsToShow) {
									var scrollTop = mySitesMenu.attr('scrollTop');

									var scrolledDown = (scrollTop > lastScrollTop);

									if (scrolledDown) {
										var scrollHeight = mySitesMenu.attr('scrollHeight');

										var scrolledToBottom = ((scrollHeight - scrollTop - moreResults.outerHeight()) <= mySitesMenu.outerHeight());

										if (scrolledToBottom && !loadingLock) {
											loadingLock = true;

											instance.index += mySitesMax;

											var start = instance.index;
											var end = start + mySitesMax;

											Liferay.Service(
												'$userGroups = /group/search',
												{
													companyId: themeDisplay.getCompanyId(),
													description: '',
													end: end,
													name: searchMySites.val(),
													params: null,
													start: start,
													'$publicLayouts = /layout/get-layouts': {
													'@groupId': '$userGroups.groupId',
														privateLayout: false
													},
													'$privateLayouts = /layout/get-layouts': {
														'@groupId': '$userGroups.groupId',
														privateLayout: true
													}
												},
												function(results) {
													var siteListTemplate = instance._getSiteListTemplate();

													var compiledTemplate = siteListTemplate(
														{
															items: A.Array.map(results, instance._getTemplateLayoutResultConfig)
														}
													);

													var insertNode = A.Node.create(compiledTemplate);

													mySitesMenu.insertBefore(insertNode, moreResults);

													moreResults.toggle(results.length >= (mySitesMax - 1));

													loadingLock = false;
												}
											);
										}
									}

									lastScrollTop = scrollTop;
								}
							}
						);
					},

					_getSiteListTemplate: function() {
						var instance = this;

						var siteListTemplate = instance._siteListTemplate;

						if (!siteListTemplate) {
							var siteListTemplateHTML = instance.mySitesMenuDropdown.one('[type="text/x-handlebars-template"]').getHTML();

							var siteListTemplate = A.Handlebars.compile(siteListTemplateHTML);

							instance._siteListTemplate = siteListTemplate;
						}

						return siteListTemplate;
					},

					_getTemplateLayoutResultConfig: function(result) {
						var instance = this;

						if (result.raw) {
							result = result.raw;
						}

						var parentGroupId = A.Lang.toInt(themeDisplay.getParentGroupId(), 10);

						var privateLayouts = result.privateLayouts.length;
						var publicLayouts = result.publicLayouts.length;

						return {
							selectedSite: (parentGroupId === result.groupId),
							showLayoutIcons: (privateLayouts && publicLayouts),
							name: result.descriptiveName,
							privateLayouts: privateLayouts,
							publicLayouts: publicLayouts,
							url: result.friendlyURL
						};
					},

					_getQueryResultsHTML: function(query, results) {
						var instance = this;

						var queryResults = instance._RESULTS[query];

						if (!queryResults) {
							var siteListTemplate = instance._getSiteListTemplate();

							var queryResults = siteListTemplate(
								{
									items: A.Array.map(results, instance._getTemplateLayoutResultConfig)
								}
							);

							instance._RESULTS[query] = queryResults;
						}

						return queryResults;
					}
				}
			}
		);

		new DockbarMySitesSearch();

		Liferay.DockbarMySitesSearch = DockbarMySitesSearch;
	},
	'',
	{
		requires: ['autocomplete-filters', 'autocomplete-plugin', 'handlebars']
	}
);