package net.sayaya.ui;

import com.google.gwt.core.client.Scheduler;
import elemental2.dom.*;
import elemental2.svg.SVGElement;
import elemental2.svg.SVGPolygonElement;
import jsinterop.annotations.*;
import net.sayaya.ui.event.HasSelectionChangeHandlers;
import net.sayaya.ui.event.HasValueChangeHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;
import org.jboss.elemento.HtmlContentBuilder;

import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.Elements.span;
import static org.jboss.elemento.EventType.bind;

public abstract class DropDownElement extends HTMLElementBuilder<HTMLDivElement, DropDownElement> implements HasValueChangeHandlers<String>, HasSelectionChangeHandlers<Integer> {
	public static DropDownElement filled(ListElement<ListElement.SingleLineItem> listElement) {
		DropDownElement elem = new DropDownFilled(div(), listElement);
		bind(elem.element,"DOMNodeInserted", evt->elem._mdc = inject(elem.element()));
		return elem;
	}
	public static DropDownElement outlined(ListElement<ListElement.SingleLineItem> listElement) {
		DropDownElement elem = new DropDownOutlined(div(), listElement);
		bind(elem.element,"DOMNodeInserted", evt->elem._mdc = inject(elem.element()));
		return elem;
	}
	private final static String SVG_NAMESPACE = "http://www.w3.org/2000/svg";

	protected final ListElement<?> listElement;
	protected MCDDropdown _mdc;
	protected MCDDropdownFoundation _foundation;
	protected final HtmlContentBuilder<HTMLDivElement> _this;
	protected final HtmlContentBuilder<HTMLDivElement> anchor = div().css("mdc-select__anchor");
	protected final HtmlContentBuilder<HTMLElement> value = span().css("mdc-select__selected-text");
	protected final HtmlContentBuilder<HTMLElement> label = span().css("mdc-floating-label");
	protected final HtmlContentBuilder<HTMLElement> container = span().css("mdc-select__selected-text-container").add(value);
	private final SVGElement svg = (SVGElement) DomGlobal.document.createElementNS(SVG_NAMESPACE, "svg");
	protected final HtmlContentBuilder<HTMLElement> arrow = span().css("mdc-select__dropdown-icon").add(svg);
	private final SVGPolygonElement inactive = (SVGPolygonElement) DomGlobal.document.createElementNS(SVG_NAMESPACE, "polygon");
	private final SVGPolygonElement active = (SVGPolygonElement) DomGlobal.document.createElementNS(SVG_NAMESPACE, "polygon");
	protected final HtmlContentBuilder<HTMLDivElement> menu = div().css("mdc-select__menu", "mdc-menu", "mdc-menu-surface", "mdc-menu-surface--fullwidth");
	protected DropDownElement(HtmlContentBuilder<HTMLDivElement> e, ListElement<?> listElement) {
		super(e);
		_this = e;
		this.listElement = listElement;
		menu.add(listElement);
		initDropdownIcon();
	}
	private void initDropdownIcon() {
		svg.classList.add("mdc-select__dropdown-icon-graphic");
		svg.setAttribute( "viewBox", "7 10 10 5");
		svg.setAttribute("focusable", "false");
		svg.appendChild(inactive);
		svg.appendChild(active);
		inactive.classList.add("mdc-select__dropdown-icon-inactive");
		inactive.setAttribute("points", "7 10 12 15 17 10");
		inactive.setAttribute("stroke", "none");
		inactive.setAttribute("fill-rule", "evenodd");
		active.classList.add("mdc-select__dropdown-icon-active");
		active.setAttribute("points", "7 15 12 10 17 15");
		active.setAttribute("stroke", "none");
		active.setAttribute("fill-rule", "evenodd");
	}
	public final DropDownElement text(String label) {
		this.label.textContent(label);
		return that();
	}
	@Override
	public Integer selection() {
		return _mdc.selectedIndex();
	}
	public DropDownElement select(int idx) {
		_foundation.setSelectedIndex(idx);
		return that();
	}
	public DropDownElement select(String value) {
		Integer n = listElement.indexOf(value);
		if(n != null) select(n);
		return that();
	}
	@Override
	public HandlerRegistration onSelectionChange(SelectionChangeEventListener<Integer> listener) {
		EventListener wrapper = evt->Scheduler.get().scheduleDeferred(()->listener.handle(SelectionChangeEvent.event(evt, selection())));
		return bind(value.element(), "change", wrapper);
	}
	@Override
	public String value() {
		return _mdc.value;
	}
	@Override
	public HandlerRegistration onValueChange(ValueChangeEventListener<String> listener) {
		EventListener wrapper = evt->Scheduler.get().scheduleDeferred(()->listener.handle(ValueChangeEvent.event(evt, value())));
		return bind(value.element(), "change", wrapper);
	}

	private final static class DropDownFilled extends DropDownElement {
		private final HtmlContentBuilder<HTMLElement> rippleSelect = span().css("mdc-select__ripple");
		private final HtmlContentBuilder<HTMLElement> rippleLine = span().css("mdc-line-ripple");
		public DropDownFilled(HtmlContentBuilder<HTMLDivElement> e, ListElement<?> listElement) {
			super(e, listElement);
			layout();
		}
		private void layout() {
			_this.css("mdc-select mdc-select--filled")
					.add(anchor.add(rippleSelect)
							.add(label)
							.add(container)
							.add(arrow)
							.add(rippleLine))
					.add(menu);
		}

		@Override
		public DropDownFilled that() {
			return this;
		}
	}
	private final static class DropDownOutlined extends DropDownElement {
		private final HtmlContentBuilder<HTMLElement> outlineLeading = span().css("mdc-notched-outline__leading");
		private final HtmlContentBuilder<HTMLElement> outlineNotch = span().css("mdc-notched-outline__notch").add(label);
		private final HtmlContentBuilder<HTMLElement> outlineTrailing = span().css("mdc-notched-outline__trailing");
		private final HtmlContentBuilder<HTMLElement> outline = span().css("mdc-notched-outline").add(outlineLeading).add(outlineNotch).add(outlineTrailing);

		public DropDownOutlined(HtmlContentBuilder<HTMLDivElement> e, ListElement<?> listElement) {
			super(e, listElement);
			layout();
		}
		private void layout() {
			_this.css("mdc-select mdc-select--outlined")
					.add(anchor.add(outline)
							.add(container)
							.add(arrow))
					.add(menu);
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
		@JsProperty
		private MCDDropdownFoundation foundation;
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
	private static native MCDDropdown inject(Element elem) /*-{

        var t = $wnd.mdc.select.MDCSelect.attachTo(elem);
        console.log(t);
        return t;
    }-*/;
}

/*
	protected DropDownElement(HtmlContentBuilder<HTMLDivElement> e, ListElement<?> listElement) {
		super(e);
		this.listElement = listElement;
		String id = DOM.createUniqueId();
		label.id(id);
		anchor.attr("aria-labelledby", id);
	}

	public final DropDownElement enabled(boolean enabled) {
		if(!enabled) {
			css("mdc-select--disable");
			anchor.attr("aria-disabled", "true");
			_mdc.disabled = true;
		} else {
			ncss("mdc-select--disable");
			anchor.attr("aria-disabled", null);
			_mdc.disabled = false;
		}
		return that();
}

	private final static class DropDownFilled extends DropDownElement {
		private final HtmlContentBuilder<HTMLElement> ripple = span().css("mdc-select__ripple");
		private final HtmlContentBuilder<HTMLElement> ripple2 = span().css("mdc-line-ripple");
		private final HtmlContentBuilder<HTMLDivElement> menu = div().css("mdc-select__menu", "mdc-menu", "mdc-menu-surface", "mdc-menu-surface--fixed", "mdc-menu-surface--fullwidth");
		private final HtmlContentBuilder<HTMLDivElement> _this;
		public DropDownFilled(HtmlContentBuilder<HTMLDivElement> e, ListElement<?> listElement) {
			super(e, listElement);
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
					.add(menu.add(listElement));
		}

		@Override
		public DropDownFilled that() {
			return this;
		}
	}
}*/
