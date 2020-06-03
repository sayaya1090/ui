package net.sayaya.ui;

import elemental2.dom.*;
import jsinterop.annotations.JsType;
import net.sayaya.ui.event.HasClickHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.Elements;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;

import static org.jboss.elemento.Elements.*;
import static org.jboss.elemento.EventType.bind;

public class TopBar extends HTMLElementBuilder<HTMLElement, TopBar> {
	public static TopBar topBar() {
		TopBar elem = new TopBar(header());
		elem.css("mdc-top-app-bar");
		bind(elem, "DOMNodeInserted", evt->elem._mdc=inject(elem.element()));
		return elem;
	}
	public static TopBar topBarFixed() {
		TopBar elem = new TopBar(header());
		elem.css("mdc-top-app-bar", "mdc-top-app-bar--fixed");
		bind(elem, "DOMNodeInserted", evt->elem._mdc=inject(elem.element()));
		return elem;
	}
	public static TopBar topBarShort() {
		TopBar elem = new TopBar(header());
		elem.css("mdc-top-app-bar", "mdc-top-app-bar--short");
		bind(elem, "DOMNodeInserted", evt->elem._mdc=inject(elem.element()));
		return elem;
	}
	public static TopBar topBarDense() {
		TopBar elem = new TopBar(header());
		elem.css("mdc-top-app-bar", "mdc-top-app-bar--dense");
		bind(elem, "DOMNodeInserted", evt->elem._mdc=inject(elem.element()));
		return elem;
	}
	public static TopBarSection section() {
		TopBarSection elem = new TopBarSection(Elements.section()).left();
		elem.css("mdc-top-app-bar__section");
		return elem;
	}
	public static TopBarButton buttonNavigation(String icon) {
		TopBarButton elem = new TopBarButton(Elements.button()).value(icon);
		elem.css("material-icons", "mdc-icon-button", "mdc-top-app-bar__navigation-icon--unbounded");
		return elem;
	}
	public static TopBarButton buttonAction(String icon) {
		TopBarButton elem = new TopBarButton(Elements.button()).value(icon);
		elem.css("material-icons", "mdc-icon-button", "mdc-top-app-bar__action-item--unbounded");
		return elem;
	}
	private native static MDCTopAppBar inject(Element elem) /*-{
        return $wnd.mdc.topAppBar.MDCTopAppBar.attachTo(elem);
    }-*/;
	private final HtmlContentBuilder<HTMLDivElement> row = div().css("mdc-top-app-bar__row");
	private final HtmlContentBuilder<HTMLElement> _this;
	private MDCTopAppBar _mdc;
	private TopBar(HtmlContentBuilder<HTMLElement> e) {
		super(e);
		_this = e;
		layout();
	}
	private void layout() {
		_this.add(row);
	}
	public TopBar add(TopBarSection section) {
		row.add(section);
		return this;
	}
	public TopBar target(HtmlContentBuilder<?> elem) {
		elem.css("mdc-top-app-bar--fixed-adjust");
		_mdc.setScrollTarget(elem.element());
		return this;
	}
	@Override
	public TopBar that() {
		return this;
	}
	@JsType(isNative = true, namespace = "mdc.topAppBar", name="MDCTopAppBar")
	private final static class MDCTopAppBar {
		public native void setScrollTarget(Element target);
	}
	public static class TopBarSection extends HTMLElementBuilder<HTMLElement, TopBarSection> {
		private final HtmlContentBuilder<HTMLElement> _this;
		private TopBarSection(HtmlContentBuilder<HTMLElement> e) {
			super(e);
			_this = e;
			layout();
		}
		private void layout() {

		}
		public TopBarSection left() {
			ncss("mdc-top-app-bar__section--align-end");
			css("mdc-top-app-bar__section--align-start");
			return this;
		}
		public TopBarSection right() {
			css("mdc-top-app-bar__section--align-end");
			ncss("mdc-top-app-bar__section--align-start");
			return this;
		}
		public TopBarSection add(IsElement<?> elem) {
			_this.add(elem);
			return this;
		}
		@Override
		public TopBarSection that() {
			return this;
		}
	}
	public static class TopBarButton extends HTMLElementBuilder<HTMLButtonElement, TopBarButton> implements HasClickHandlers {
		private final HtmlContentBuilder<HTMLButtonElement> _this;
		private TopBarButton(HtmlContentBuilder<HTMLButtonElement> e) {
			super(e);
			_this = e;
		}
		public TopBarButton value(String icon) {
			element().textContent = icon;
			return this;
		}
		@Override
		public HandlerRegistration onClick(EventListener listener) {
			return onClick(_this.element(), listener);
		}
		@Override
		public TopBarButton that() {
			return this;
		}
	}
}
