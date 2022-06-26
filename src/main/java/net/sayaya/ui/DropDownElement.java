package net.sayaya.ui;

import com.google.gwt.core.client.Scheduler;
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

public abstract class DropDownElement extends HTMLElementBuilder<HTMLDivElement, DropDownElement> implements HasValueChangeHandlers<String>, HasSelectionChangeHandlers<Integer> {
	public static DropDownElement filled(ListElement listElement) {
		return new DropDownFilled(div(), listElement);
	}
	public static DropDownElement outlined(ListElement listElement) {
		return new DropDownOutlined(div(), listElement);
	}
	private final static String SVG_NAMESPACE = "http://www.w3.org/2000/svg";
	protected MDCDropdown _mdc;
	protected MCDDropdownFoundation _foundation;
	protected final HtmlContentBuilder<HTMLDivElement> anchor = div().css("mdc-select__anchor");
	protected final HtmlContentBuilder<HTMLElement> label = span().css("mdc-floating-label");
	protected final HtmlContentBuilder<HTMLElement> value = span().css("mdc-select__selected-text");
	private final SVGElement svg = (SVGElement) DomGlobal.document.createElementNS(SVG_NAMESPACE, "svg");
	protected final HtmlContentBuilder<HTMLElement> arrow = span().css("mdc-select__dropdown-icon").add(svg);
	private final SVGPolygonElement inactive = (SVGPolygonElement) DomGlobal.document.createElementNS(SVG_NAMESPACE, "polygon");
	private final SVGPolygonElement active = (SVGPolygonElement) DomGlobal.document.createElementNS(SVG_NAMESPACE, "polygon");
	protected final ListElement<?> listElement;
	protected DropDownElement(HtmlContentBuilder<HTMLDivElement> e, ListElement<?> listElement) {
		super(e);
		this.listElement = listElement;
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
	@Override
	public HandlerRegistration onSelectionChange(SelectionChangeEventListener<Integer> listener) {
		EventListener wrapper = evt->Scheduler.get().scheduleDeferred(()->listener.handle(SelectionChangeEvent.event(evt, selection())));
		return bind(value.element(), "DOMSubtreeModified", wrapper);
	}

	@Override
	public String value() {
		return _mdc.value;
	}
	@Override
	public HandlerRegistration onValueChange(ValueChangeEventListener<String> listener) {
		EventListener wrapper = evt->Scheduler.get().scheduleDeferred(()->listener.handle(ValueChangeEvent.event(evt, value())));
		return bind(value.element(), "DOMSubtreeModified", wrapper);
	}

	private final static class DropDownFilled extends DropDownElement {
		private final HtmlContentBuilder<HTMLElement> ripple = span();
		private final HtmlContentBuilder<HTMLElement> ripple2 = span();
		private final HtmlContentBuilder<HTMLDivElement> menu = div();
		private final HtmlContentBuilder<HTMLDivElement> _this;
		public DropDownFilled(HtmlContentBuilder<HTMLDivElement> e, ListElement<?> listElement) {
			super(e, listElement);
			_this = e;
			layout();
			_mdc = new MDCDropdown(element());
			_foundation = _mdc.foundation;
		}

		private void layout() {
			_this.css("mdc-select", "mdc-select--filled")
					.add(anchor.add(ripple.css("mdc-select__ripple"))
							.add(value)
							.add(arrow)
							.add(label)
							.add(ripple2.css("mdc-line-ripple")))
					.add(menu.css("mdc-select__menu", "mdc-menu", "mdc-menu-surface", "mdc-menu-surface--fixed", "mdc-menu-surface--fullwidth")
							.add(listElement));
		}

		@Override
		public DropDownFilled that() {
			return this;
		}
	}
	private final static class DropDownOutlined extends DropDownElement {
		private final HtmlContentBuilder<HTMLElement> outline = span();
		private final HtmlContentBuilder<HTMLElement> outlineLeading = span();
		private final HtmlContentBuilder<HTMLElement> outlineNotch = span();
		private final HtmlContentBuilder<HTMLElement> outlineTrailing = span();
		private final HtmlContentBuilder<HTMLDivElement> menu = div();
		private final HtmlContentBuilder<HTMLDivElement> _this;
		public DropDownOutlined(HtmlContentBuilder<HTMLDivElement> e, ListElement<?> listElement) {
			super(e, listElement);
			_this = e;
			layout();
			_mdc = new MDCDropdown(element());
			_foundation = _mdc.foundation;
		}
		private void layout() {
			_this.css("mdc-select", "mdc-select--outlined")
					.add(anchor.add(outline.css("mdc-notched-outline")
									.add(outlineLeading.css("mdc-notched-outline__leading"))
									.add(outlineNotch.css("mdc-notched-outline__notch")
											.add(label))
									.add(outlineTrailing.css("mdc-notched-outline__trailing")))
							   .add(value)
							   .add(arrow))
					.add(menu.css("mdc-select__menu", "mdc-menu", "mdc-menu-surface", "mdc-menu-surface--fixed", "mdc-menu-surface--fullwidth")
							.add(listElement));
		}

		@Override
		public DropDownOutlined that() {
			return this;
		}
	}
	@JsType(isNative = true, namespace = "mdc.select", name="MDCSelect")
	private final static class MDCDropdown {
		@JsProperty public String value;
		@JsProperty public boolean disabled;
		@JsProperty public boolean required;
		@JsProperty public boolean valid;
		@JsProperty public MCDDropdownFoundation foundation;
		@JsProperty private Double selectedIndex;
		public MDCDropdown(Element elem){}
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
