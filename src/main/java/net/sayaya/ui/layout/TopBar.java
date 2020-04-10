package net.sayaya.ui.layout;

import elemental2.dom.*;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.sayaya.ui.Focusable;
import net.sayaya.ui.IsHTMLElement;
import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.event.HasClickHandlers;

import static org.jboss.gwt.elemento.core.Elements.*;

public class TopBar implements IsHTMLElement<HTMLElement, TopBar> {
	private final HTMLElement title = span().css("mdc-top-app-bar__title").style("cursor: default;").element();
	private final HTMLElement left = section().css("mdc-top-app-bar__section", "mdc-top-app-bar__section--align-start")
												 .add(title)
												 .element();
	private final HTMLElement right = section().css("mdc-top-app-bar__section", "mdc-top-app-bar__section--align-end").element();
	private final HTMLDivElement row = div().css("mdc-top-app-bar__row")
											.add(left)
											.add(right)
											.element();
	private final HTMLElement _this = header().css("mdc-top-app-bar")
											  .add(row)
											  .element();
	public static TopBarBuilder topbar() {
		return new TopBarBuilder();
	}
	private TopBar() {}
	native static void inject(Element elem, Element contents) /*-{
        var bar = $wnd.mdc.topAppBar.MDCTopAppBar.attachTo(elem);
        bar.setScrollTarget(contents);
    }-*/;
	final void inject(Element elem) {
		inject(_this, elem);
	}
	public static void setContent(Element elem) {
		elem.classList.add("mdc-top-app-bar--short-fixed-adjust");
	}
	public TopBarButton menu() {
		TopBarButton menu = new TopBarButton("mdc-top-app-bar__navigation-icon--unbounded", "menu");
		left.insertBefore(menu.element(), title);
		return menu;
	}
	public TopBarButton button(String icon) {
		TopBarButton btn = new TopBarButton("mdc-top-app-bar__action-item--unbounded", icon);
		right.appendChild(btn.element());
		return btn;
	}
	public TopBar title(String title) {
		this.title.innerHTML = title;
		return this;
	}
	public String title() {
		return title.innerHTML;
	}
	@Override
	public HTMLElement element() {
		return _this;
	}

	public static class TopBarButton implements IsHTMLElement<HTMLButtonElement, TopBarButton>, Focusable<TopBarButton>, HasClickHandlers {
		private final HTMLButtonElement _this = org.jboss.gwt.elemento.core.Elements.button().css("material-icons", "mdc-icon-button").element();
		TopBarButton(String cls, String icon) {
			_this.classList.add(cls);
			_this.textContent = icon;
		}
		@Override
		public final HandlerRegistration addClickHandler(EventListener listener) {
			return addClickHandler(_this, listener);
		}
		public final TopBarButton enabled(boolean enabled) {
			if(enabled) _this.removeAttribute("disabled");
			else _this.setAttribute("disabled", true);
			return self();
		}
		@Override
		public TopBarButton accessKey(char key) {
			_this.setAttribute("accessKey", String.valueOf(key));
			return self();
		}
		@Override
		public TopBarButton focus() {
			_this.focus();
			return self();
		}
		@Override
		public final HTMLButtonElement element() {
			return _this;
		}
	}

	@Setter
	@Accessors(fluent=true)
	public static class TopBarBuilder {
		private String title;
		private TopBarType type;
		public TopBar element(HTMLElement contents) {
			TopBar topbar = new TopBar();
			if(title!=null) topbar.title(title);
			if(type == TopBarType.FIXED) topbar.element().classList.add("mdc-top-app-bar--fixed");
			else if(type == TopBarType.SHORT) topbar.element().classList.add("mdc-top-app-bar--short");
			else if(type == TopBarType.DENSE) topbar.element().classList.add("mdc-top-app-bar--dense");
			topbar.inject(contents);
			return topbar;
		}
	}
	public enum TopBarType {
		STANDARD, FIXED, DENSE, PROMINENT, SHORT
	}
}
