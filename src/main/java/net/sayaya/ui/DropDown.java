package net.sayaya.ui;

import com.google.gwt.user.client.DOM;
import elemental2.dom.*;
import elemental2.svg.SVGElement;
import elemental2.svg.SVGPolygonElement;
import jsinterop.annotations.*;
import net.sayaya.ui.event.HasSelectionChangeHandlers;
import net.sayaya.ui.event.HasValueChangeHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.HtmlContentBuilder;

import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.Elements.span;
import static org.jboss.elemento.EventType.bind;

public abstract class DropDown extends HTMLElementBuilder<HTMLDivElement, DropDown> implements HasValueChangeHandlers<String>, HasSelectionChangeHandlers<Integer> {
	public static DropDown filled(List list) {
		DropDown elem = new DropDownFilled(div(), list);
		elem._mdc = inject(elem.element());
		elem._foundation = foundation(elem._mdc);
		return elem;
	}
	public static DropDown outlined(List list) {
		DropDown elem = new DropDownOutlined(div(), list);
		elem._mdc = inject(elem.element());
		elem._foundation = foundation(elem._mdc);
		return elem;
	}
	private static native MCDDropdown inject(Element elem) /*-{
        return $wnd.mdc.select.MDCSelect.attachTo(elem);
    }-*/;
	private static native MCDDropdownFoundation foundation(MCDDropdown mdc) /*-{
        return mdc.foundation;
    }-*/;
	private final static String SVG_NAMESPACE = "http://www.w3.org/2000/svg";
	protected MCDDropdown _mdc;
	protected MCDDropdownFoundation _foundation;
	protected final HtmlContentBuilder<HTMLDivElement> anchor = div().css("mdc-select__anchor");
	protected final HtmlContentBuilder<HTMLElement> label = span().css("mdc-floating-label");
	protected final HtmlContentBuilder<HTMLElement> value = span().css("mdc-select__selected-text");
	// protected final HtmlContentBuilder<HTMLElement> container = span().css("mdc-select__selected-text-container").add(value);
	private final SVGElement svg = (SVGElement) DomGlobal.document.createElementNS(SVG_NAMESPACE, "svg");
	protected final HtmlContentBuilder<HTMLElement> arrow = span().css("mdc-select__dropdown-icon").add(svg);
	private final SVGPolygonElement inactive = (SVGPolygonElement) DomGlobal.document.createElementNS(SVG_NAMESPACE, "polygon");
	private final SVGPolygonElement active = (SVGPolygonElement) DomGlobal.document.createElementNS(SVG_NAMESPACE, "polygon");
	protected final List<?> list;
	protected DropDown(HtmlContentBuilder<HTMLDivElement> e, List<?> list) {
		super(e);
		this.list = list;
		String id = DOM.createUniqueId();
		label.id(id);
		anchor.attr("aria-labelledby", id);
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
	public final DropDown text(String label) {
		this.label.textContent(label);
		return that();
	}
	@Override
	public Integer selection() {
		return _mdc.selectedIndex();
	}
	public DropDown select(int idx) {
		_foundation.setSelectedIndex(idx);
		return that();
	}
	@Override
	public HandlerRegistration onSelectionChange(SelectionChangeEventListener<Integer> listener) {
		EventListener wrapper = evt->listener.handle(SelectionChangeEvent.event(evt, selection()));
		return bind(value.element(), "DOMSubtreeModified", wrapper);
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

	private final static class DropDownFilled extends DropDown {
		private final HtmlContentBuilder<HTMLElement> ripple = span().css("mdc-select__ripple");
		private final HtmlContentBuilder<HTMLElement> ripple2 = span().css("mdc-line-ripple");
		private final HtmlContentBuilder<HTMLDivElement> menu = div().css("mdc-select__menu", "mdc-menu", "mdc-menu-surface", "mdc-menu-surface--fixed", "mdc-menu-surface--fullwidth");
		private final HtmlContentBuilder<HTMLDivElement> _this;
		public DropDownFilled(HtmlContentBuilder<HTMLDivElement> e, List<?> list) {
			super(e, list);
			_this = e;
			layout();
		}

		private void layout() {
			_this.css("mdc-select", "mdc-select--filled")
					.add(anchor.add(ripple)
							.add(value)
							.add(arrow)
							.add(label)
							.add(ripple2))
					.add(menu.add(list));
		}

		@Override
		public DropDownFilled that() {
			return this;
		}
	}
	private final static class DropDownOutlined extends DropDown {
		private final HtmlContentBuilder<HTMLElement> outline = span().css("mdc-notched-outline");
		private final HtmlContentBuilder<HTMLElement> outlineLeading = span().css("mdc-notched-outline__leading");
		private final HtmlContentBuilder<HTMLElement> outlineNotch = span().css("mdc-notched-outline__notch");
		private final HtmlContentBuilder<HTMLElement> outlineTrailing = span().css("mdc-notched-outline__trailing");
		private final HtmlContentBuilder<HTMLDivElement> menu = div().css("mdc-select__menu", "mdc-menu", "mdc-menu-surface", "mdc-menu-surface--fixed", "mdc-menu-surface--fullwidth");
		private final HtmlContentBuilder<HTMLDivElement> _this;
		public DropDownOutlined(HtmlContentBuilder<HTMLDivElement> e, List<?> list) {
			super(e, list);
			_this = e;
			layout();
		}
		private void layout() {
			_this.css("mdc-select", "mdc-select--outlined")
					.add(anchor.add(outline.add(outlineLeading).add(outlineNotch).add(outlineTrailing))
							.add(value).add(arrow).add(label))
					.add(menu.add(list));
		}

		@Override
		public DropDownOutlined that() {
			return this;
		}
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
		@JsOverlay
		@JsIgnore
		public int selectedIndex() {
			if(selectedIndex==null) return 0;
			else return selectedIndex.intValue();
		}
	}
	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	private final static class MCDDropdownFoundation {
		public native void setSelectedIndex(int idx);
	}
}
