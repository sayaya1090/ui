package net.sayaya.ui.layout;

import elemental2.dom.Element;
import elemental2.dom.HTMLElement;
import net.sayaya.ui.IsHTMLElement;

import static org.jboss.gwt.elemento.core.Elements.div;

public class TabBar implements IsHTMLElement<HTMLElement, TabBar> {
	private final HTMLElement _this = div().css("mdc-tab-bar").attr("role", "tablist").element();
	public TabBar() {
		_this.innerHTML = "  <div class=\"mdc-tab-scroller\">\n" +
								"    <div class=\"mdc-tab-scroller__scroll-area\">\n" +
								"      <div class=\"mdc-tab-scroller__scroll-content\">\n" +
								"        <button class=\"mdc-tab mdc-tab--active\" role=\"tab\" aria-selected=\"true\" tabindex=\"0\">\n" +
								"          <span class=\"mdc-tab__content\">\n" +
								"            <span class=\"mdc-tab__icon material-icons\" aria-hidden=\"true\">favorite</span>\n" +
								"            <span class=\"mdc-tab__text-label\">Favorites</span>\n" +
								"          </span>\n" +
								"          <span class=\"mdc-tab-indicator mdc-tab-indicator--active\">\n" +
								"            <span class=\"mdc-tab-indicator__content mdc-tab-indicator__content--underline\"></span>\n" +
								"          </span>\n" +
								"          <span class=\"mdc-tab__ripple\"></span>\n" +
								"        </button>\n" +
								"      </div>\n" +
								"    </div>\n" +
								"  </div>";
	}
	native static void inject(Element elem) /*-{
        $wnd.mdc.tabBar.MDCTabBar.attachTo(elem);
    }-*/;
	final void inject() {
		inject(_this);
	}
	@Override
	public HTMLElement element() {
		inject();
		return _this;
	}
}
