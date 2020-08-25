package net.sayaya.ui;

import elemental2.dom.*;
import elemental2.svg.SVGElement;
import elemental2.svg.SVGPolygonElement;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import net.sayaya.ui.event.HasValueChangeHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.HtmlContentBuilder;

import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.Elements.span;
import static org.jboss.elemento.EventType.bind;

public class DropDown extends HTMLElementBuilder<HTMLDivElement, DropDown> implements HasValueChangeHandlers<String> {
	public static DropDown dropdown(List list) {
		DropDown elem = new DropDown(div(), list);
		elem._mdc = inject(elem.element());
		return elem;
	}
	private static native MCDDropdown inject(Element elem) /*-{
        return $wnd.mdc.select.MDCSelect.attachTo(elem);
    }-*/;
	private final static String SVG_NAMESPACE = "http://www.w3.org/2000/svg";
	private final SVGPolygonElement inactive = (SVGPolygonElement) DomGlobal.document.createElementNS(SVG_NAMESPACE, "polygon");
	private final SVGPolygonElement active = (SVGPolygonElement) DomGlobal.document.createElementNS(SVG_NAMESPACE, "polygon");
	private final SVGElement svg = (SVGElement) DomGlobal.document.createElementNS(SVG_NAMESPACE, "svg");
	private final HtmlContentBuilder<HTMLElement> ripple = span().css("mdc-select__ripple");
	private final HtmlContentBuilder<HTMLElement> value = span().css("mdc-select__selected-text");
	private final HtmlContentBuilder<HTMLElement> arrow = span().css("mdc-select__dropdown-icon").add(svg);
	private final HtmlContentBuilder<HTMLElement> label = span().css("mdc-floating-label");
	private final HtmlContentBuilder<HTMLElement> ripple2 = span().css("mdc-line-ripple");
	private final HtmlContentBuilder<HTMLDivElement> anchor = div().css("mdc-select__anchor")
																   .add(ripple)
																   .add(value)
																   .add(arrow)
																   .add(label)
																   .add(ripple2);
	private final HtmlContentBuilder<HTMLDivElement> menu = div().css("mdc-select__menu", "mdc-menu", "mdc-menu-surface", "mdc-menu-surface--fullwidth");
	private final List list;
	private final HtmlContentBuilder<HTMLDivElement> _this;
	private MCDDropdown _mdc;
	public DropDown(HtmlContentBuilder<HTMLDivElement> e, List list) {
		super(e);
		_this = e;
		this.list = list;
		layout();
	}

	private void layout() {
		_this.css("mdc-select", "mdc-select--filled").style("width: 400px;")
			 .add(anchor)
			 .add(menu.add(list));
		svg.setAttribute( "viewBox", "7 10 10 5");
		svg.classList.add("mdc-select__dropdown-icon-graphic");
		svg.appendChild(inactive);
		svg.appendChild(active);
		inactive.setAttribute("points", "7 10 12 15 17 10");
		inactive.setAttribute("stroke", "none");
		inactive.setAttribute("fill-rule", "evenodd");
		inactive.classList.add("mdc-select__dropdown-icon-inactive");
		active.setAttribute("points", "7 15 12 10 17 15");
		active.setAttribute("stroke", "none");
		active.setAttribute("fill-rule", "evenodd");
		active.classList.add("mdc-select__dropdown-icon-active");
	}
	public DropDown value(String value) {
		_mdc.value = value;
		return that();
	}
	@Override
	public String value() {
		return value.element().textContent;
	}
	@Override
	public HandlerRegistration onValueChange(ValueChangeEventListener<String> listener) {
		EventListener wrapper = evt->listener.handle(ValueChangeEvent.event(evt, value()));
		return bind(value.element(), "DOMSubtreeModified", wrapper);
	}
	@Override
	public DropDown that() {
		return this;
	}
	@JsType(isNative = true, namespace = "mdc.select", name="MDCSelect")
	private final static class MCDDropdown {
		@JsProperty
		private String value;
		@JsProperty
		private Double selectedIndex;
		@JsProperty
		private boolean disabled;
		@JsProperty
		private boolean required;
		@JsProperty
		private boolean valid;
	}
}
