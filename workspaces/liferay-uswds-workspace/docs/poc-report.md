# U.S. Web Design System (USWDS) - Liferay Workspace: POC Report

## Executive Summary

A Liferay workspace packaging the U.S. Web Design System (USWDS) (https://designsystem.digital.gov/) as a portable, drop-in bundle any DXP site can adopt. Distributed as two client extensions that together form a reusable kit.

1) liferay-uswds-theme-css (type: themeCSS)
2) liferay-uswds-site-initializer (type: siteInitializer)

### How to Deploy

1) Navigate to `workspaces/liferay-uswds-workspace/client-extensions/liferay-uswds-theme-css/` and `gradlew deploy`.

2) Navigate to `workspaces/liferay-uswds-workspace/client-extensions/liferay-uswds-site-initializer/` and `gradlew deploy`

3) In DXP navigate to Control Panel > Sites > Click Liferay USWDS Vertical Ellipsis > Go to Pages

4) From Liferay USWDS Site, Open Product Menu > Site Builder > Pages

5) Click Vertical Ellipsis in Control Menu > Click Configuration

6) Scroll down to THEME CSS CLIENT EXTENSION in Customization > Click the plus button in Theme CSS

7) Select Liferay USWDS Theme CSS > Scroll down and click Save

8) Click Home in the Product Menu to view the theme

## Scope

- **Portal:** Liferay CE 7.4.3.148 GA148
  - DXP 2024.Q2.x and newer quarterly releases (2024.Q3, Q4, 2025.Q1+) — expected to work; minor token/Style Book drift
  possible across quarters.
  - DXP 2024.Q1 — likely works but unverified; ColorPicker token editing is the risk.
  - DXP 2023.Q4 — themeCSS + siteInitializer + OAuth headless server all exist, but token editor types may not match.
- **Theme:** Classic Theme with Atlas Custom Properties imported
- **Style Book values:** 759 Tokens, will need to add more to make it truly theme agnostic
- **FET (Frontend Token Definition):** 759 Tokens, will need to add more to make it truly theme agnostic
- **Client extensions / global CSS:** theme client extension and site initializer

## Reusable Design System

Fragments and markup that ship with Liferay contain Clay utility classes (e.g., `navbar`, `container-fluid-max-xl`, `alert alert-fluid`, `btn-primary`) never raw colors or sizes. Those utilities resolve against CSS variables driven by the 759-token frontend-token-definition.json. The number of tokens aren't set in stone. It can be customized through `frontend-token-definition.json` and `_clay_variables.scss`.

Clay CSS is design-system agnostic in the sense that the anatomy is fixed but every value is externalized to variables. A design system isn't a stylesheet. It's a set of values plugged into the same anatomy. A consumer overrides the variable, never the rule. It exposes semantic tokens: primary, secondary, success, info, warning, danger. Those are universal UX concepts, not Lexicon concepts. USWDS, Material, Carbon, and Bootstrap all have the same six (sometimes under different names). Mapping any of those systems onto Clay is "which palette hue does your success mean?" — a token mapping, not a redesign.

In this workspace you can see it directly: success defaults to var(--green-cool-vivid-40) (a USWDS hue) instead of a hardcoded Lexicon green. The role didn't change — the value behind it did. Most design systems independently converge on the same anatomy: an alert has an icon slot + body + dismiss; a button has icon + label + optional spinner; a navbar has brand + items + actions. USWDS, Material, Carbon, GOV.UK all match this rough shape. So porting a design system onto Clay is mostly a token-mapping exercise rather than a markup rewrite.

### Theme CSS Client Extension

The theme css client extension is the gateway for developers to fine tune what CSS gets output by `clay.css` and `main.css`. Clay gives you three stacked override seams, with tokens being the topmost one.

There are Three Override Seams in This Workspace

```
src/
  frontend-token-definition.json    => Seam 1: tokens (Style Book exposes these)
  css/
    _clay_variables.scss            => Seam 2: SCSS overrides BEFORE Clay compiles
    _custom.scss                    => Seam 3: arbitrary CSS AFTER Clay
```

### Site Initializer Client Extension

The theme css client extension can't add markup, JavaScript, images, and page layouts. The site initializer fills in those gaps. It is the assembly and packaging layer of this workspace. The site initializer turns "fragments + layouts + nav + thumbnail" into a single deployable artifact that produces a complete, reproducible USWDS site from one admin click. It's how the workspace goes from "design system asset bundle" to "productized starter site."

### Fragments

Fragments located in `/liferay-uswds-workspace/client-extensions/liferay-uswds-site-initializer/site-initializer/fragments/group/uswds/fragments`, are a small, self-contained unit of HTML, CSS, JS that authors drag onto pages in Liferay's page editor, similar to a digital LEGO brick. The developers ship the bricks; content authors compose the pages and no new code is needed to publish a new page.

A fragment is a directory with four files, declared in fragment.json:

```
fragment-name/
  fragment.json       => declares name, type, file paths, icon
  index.html          => the markup
  index.css           => scoped styling (often empty if tokens carry the look)
  index.js            => optional behavior
  configuration.json  => declares what's editable + field types
```

The link, https://learn.liferay.com/w/dxp/sites/creating-pages/page-fragments-and-widgets/using-fragments, goes into detail about how to create and configure fragments.

A well designed fragment can scale well because one fragment instance can be used on many pages across several sites. Content teams armed with fragments can piece together new pages and update content without escalating to developers.

Traditionally, minor copy tweaks, image swaps, or layout adjustments required a developer to dive into code or push a deployment. Developers can mark editable sections in fragments using [data-lfr-editable-x](https://learn.liferay.com/w/dxp/development/developing-page-fragments/reference/fragment-specific-tags-and-attributes-reference) attributes.

These data attributes will make link, text, and images editable in the Page Editor user interface. No coding knowledge is needed, content teams can double-click text, maps, buttons, or images directly on the page to change them using a visual, what you see is what you get editor.

### Layout Fragments

Layout fragments are based on a responsive grid system (built on Bootstrap). There are two types of layout fragments, container and grid, that ship out of the box. The container element adds white space and frames the content to a specific viewport. The grid allows a user to split the page into multple columns depending on the settings used (e.g., 50/50, 33/33/33, or custom widths like 25/75). There is a drop zone in the container and each column where the composer can drag and drop fragments like button, hero banner, or Liferay Widgets.

Content creators can group multiple fragments together and save them as a single page template. This can then be reused across different pages or inherited by other sites in the Liferay instance. Page templates are useful to quickly replicate common layout patterns on the site such as a 1/2/1 layout (e.g., header, footer, sidebar, and content).

Page templates can be created through the UI by going to Product Menu > Design > Page Templates > New. In a site initializer, we can add `master-page.json`, `page-definition.json`, and a thumbnail in /client-extensions/liferay-uswds-site-initializer/site-initializer/layout-page-templates/master-pages/(page-name). The file `page-definition.json` describes the page structure. It should look like:

```
{
    "pageElement": {
        "pageElements": [
            {
                "definition": {
                    "layout": {
                        "widthType": "Fluid"
                    }
                },
                "pageElements": [
                    {
                        "definition": {
                            "layout": {
                                "widthType": "Fluid"
                            }
                        },
                        "pageElements": [
                            {
                                "definition": {
                                    "fragment": {
                                        "key": "uswds-header"
                                    }
                                },
                                "type": "Fragment"
                            }
                        ],
                        "type": "Section"
                    }
                ],
                "type": "Section"
            },
            {
                "definition": {},
                "type": "DropZone"
            },
            {
                "definition": {
                    "layout": {
                        "widthType": "Fluid"
                    }
                },
                "pageElements": [
                    {
                        "definition": {
                            "layout": {
                                "widthType": "Fluid"
                            }
                        },
                        "pageElements": [
                            {
                                "definition": {
                                    "fragment": {
                                        "key": "uswds-footer-medium"
                                    }
                                },
                                "type": "Fragment"
                            }
                        ],
                        "type": "Section"
                    }
                ],
                "type": "Section"
            }
        ],
        "type": "Root"
    }
}
```

### Navigation Fragments

Liferay navigation fragments are built-in fragments that render a site's navigation menu inside a page. Rather than hard-coding links, they bind to a Liferay navigation menu (managed under Site Administration > Site Builder > Navigation Menus) and render its items dynamically.

Liferay's out of the box header and footer fragments have rigid markup structure. It's likely that you will create a custom header, footer, or navigation fragment. You can leverage `<lfr-widget-nav></lfr-widget-nav>` in your fragment to render the site's pages. The tag is a placeholder element that Liferay's fragment renderer recognizes and replaces, at render time, with the site's navigation widget. The markup it produces is as follows:

```
<div class="portlet">
  ...
  <ul class="navbar-blank navbar-nav navbar-site">
    <li class="lfr-nav-item nav-item">
      <a class="nav-link text-truncate" href="">
        Page Name
      </a>
    </li>
  </ul>
</div>
```

In a similar way, Liferay's search bar can be rendered with `<lfr-widget-search-bar></lfr-widget-search-bar>`.

## Best Practices

### Token-first, always

Override variables, never rules. The whole point of the three seams, `frontend-token-definition.json` > `_clay_variables.scss` > `_custom.scss`, is that you can ship a USWDS look without forking Clay's source. If you find yourself writing a selector to override a compiled Clay rule, you almost certainly want to be overriding a token instead. Fall through to `_custom.scss` only when the token API doesn't expose what you need.

Use semantic tokens, not hue tokens. Map USWDS hues onto --primary, --success, --info, etc., and let fragments reference those. A fragment that says btn-primary keeps working when the theme is rebranded; one that says bg-green-cool-vivid-40 breaks the moment someone retunes the palette.

Never hardcode hex/rgb in fragment CSS or HTML. The 759-token surface is the contract — fragments should consume it, not bypass it.

## Example Implementation Walkthrough

To make the pieces concrete, this section follows a single feature end-to-end: a USWDS-styled site alert that an author drops onto a page and edits in place. The fragment already ships in this workspace at `client-extensions/liferay-uswds-site-initializer/site-initializer/fragments/group/uswds/fragments/uswds-alert/`.

### Step 1: The Token Mapping

USWDS ships a "site alert" pattern in a hue called `blue-vivid-60`. We don't want fragments referencing that hue directly since that token may not exist in other themes. It would couple every alert to USWDS forever. Instead, we map the hue onto Clay's semantic `primary` token in `frontend-token-definition.json`:

```
{
    "defaultValue": "var(--blue-vivid-60)",
    "label": "primary",
    "name": "primaryColor",
    "mappings": [
        { "type": "cssVariable", "value": "primary" }
    ]
}
```

The token surface (Seam 1) now says "primary is a USWDS blue." Style Book exposes it as a ColorPicker, so a site admin can retune it without touching code.

### Step 2: The Fragment Markup

The fragment's `index.html` consumes the token through Clay utility classes — it never names a color:

```
<div class="alert alert-fluid alert-primary" role="alert">
    <div class="container-fluid container-fluid-max-xl py-0">
        <span class="alert-indicator align-middle mr-1 text-3">
            [@clay["icon"] symbol="${configuration.iconSymbol}" .../]
        </span>
        <lfr-editable class="d-inline text-1" id="alert-text" type="text">
            Final enum native int extends import short while for
        </lfr-editable>
    </div>
</div>
```

Two things are worth pointing at:

- `alert-primary` resolves against `--primary`, which (by Step 1) resolves to a USWDS hue. The same markup would render Material blue or Carbon blue if the token were remapped.
- `<lfr-editable id="alert-text" type="text">` is the contract with content authors — that node becomes a double-click-to-edit target in the page editor.

### Step 3: The Fragment Contract

`configuration.json` declares what a page author can configure per instance, separate from the editable text:

```
{
    "fieldSets": [{
        "fields": [{
            "dataType": "string",
            "defaultValue": "en-us",
            "label": "Icon Symbol",
            "name": "iconSymbol",
            "type": "text"
        }]
    }]
}
```

The `${configuration.iconSymbol}` reference in the markup is what binds the two together. `fragment.json` ties the directory into a named, draggable unit ("USWDS Alert", type `component`).

### Step 4: Composing the Page

A site initializer page definition references the fragment by its key, not its markup. For an alert above the drop zone in a master page, `page-definition.json` looks like:

```
{
    "definition": { "fragment": { "key": "uswds-alert" } },
    "type": "Fragment"
}
```

When the site initializer runs, Liferay materializes the page, instantiates the fragment, and renders the markup with the current token values applied. A content author then opens the page in the editor, double-clicks the alert text, and rewrites it — no developer round-trip.

### Step 5: Rebranding Without Rewriting

The payoff is what happens when the design system shifts. Suppose the site moves from USWDS blue to a brand purple:

- **Token-first path:** open Style Book, change `primary` to the new hue, save. Every fragment using `alert-primary`, `btn-primary`, `bg-primary`, etc., updates at once. Zero fragment edits.
- **What the bad version would have looked like:** had the fragment said `style="background: #005ea2"` or `class="bg-blue-vivid-60"`, the rebrand becomes a code change across every fragment that hardcoded the hue.

This is the "token mapping, not redesign" thesis in one diff: the markup is stable, the values move.

## Current Limitations

- We need to deploy two modules separately. It would be nice if we could deploy once for everything in the workspace.

- We need to manually apply Theme CSS Client extension via Product Menu > Site Builder > Pages > Configuration > Theme CSS Client Extension

- There is a bug where the site isn't populated in Control Menu > Sites until you create a new unrelated site.

- lfr-widget-nav has old markup. The active class is on li instead of on nav-link.

- Too easy to create too many fragments, it can cause clutter and confusion.
