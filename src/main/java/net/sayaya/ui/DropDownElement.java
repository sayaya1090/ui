package net.sayaya.ui;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.DOM;
import elemental2.dom.Element;
import elemental2.dom.EventListener;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import jsinterop.annotations.*;
import net.sayaya.ui.event.HasSelectionChangeHandlers;
import net.sayaya.ui.event.HasValueChangeHandlers;
import net.sayaya.ui.svg.SvgBuilder;
import net.sayaya.ui.svg.SvgPolygonBuilder;
import net.sayaya.ui.util.OnAttachEvent;
import net.sayaya.ui.util.OnChangeEvent;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.HTMLContainerBuilder;

import static net.sayaya.ui.svg.SvgBuilder.svg;
import static net.sayaya.ui.svg.SvgPolygonBuilder.polygon;
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
	protected MDCDropdown _mdc;
	protected MDCDropdownFoundation _foundation;
	protected final HTMLContainerBuilder<HTMLDivElement> anchor = div().css("mdc-select__anchor");
	protected final HTMLContainerBuilder<HTMLElement> label = span().css("mdc-floating-label");
	protected final HTMLContainerBuilder<HTMLElement> value = span().css("mdc-select__selected-text");
	private final SvgPolygonBuilder inactive = polygon().css("mdc-select__dropdown-icon-inactive").points("7 10 12 15 17 10").stroke("none").fillRule("evenodd");
	private final SvgPolygonBuilder active = polygon().css("mdc-select__dropdown-icon-active").points("7 15 12 10 17 15").stroke("none").fillRule("evenodd");
	private final SvgBuilder svg = svg().viewBox(7,10,10,5).css("mdc-select__dropdown-icon-graphic").add(inactive).add(active);
	protected final HTMLContainerBuilder<HTMLElement> arrow = span().css("mdc-select__dropdown-icon").add(svg.element());
	protected final ListElement<?> listElement;
	protected DropDownElement(HTMLContainerBuilder<HTMLDivElement> e, ListElement<?> listElement) {
		super(e);
		this.listElement = listElement;
		String id = DOM.createUniqueId();
		label.id(id);
		anchor.aria("labelledby", id);
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
			anchor.aria("disabled", "true");
			_mdc.disabled = true;
		} else {
			ncss("mdc-select--disable");
			anchor.aria("disabled", null);
			_mdc.disabled = false;
		}
		return that();
	}
	@Override
	public HandlerRegistration onSelectionChange(SelectionChangeEventListener<Integer> listener) {
		EventListener wrapper = evt->Scheduler.get().scheduleDeferred(()->listener.handle(SelectionChangeEvent.event(evt, selection())));
		var obs = OnChangeEvent.observe(value.element(), wrapper);
		return obs::disconnect;
	}

	@Override
	public String value() {
		return _mdc.value;
	}
	@Override
	public HandlerRegistration onValueChange(ValueChangeEventListener<String> listener) {
		EventListener wrapper = evt->Scheduler.get().scheduleDeferred(()->listener.handle(ValueChangeEvent.event(evt, value())));
		var obs = OnChangeEvent.observe(value.element(), wrapper);
		return obs::disconnect;
	}

	private final static class DropDownFilled extends DropDownElement {
		private final HTMLContainerBuilder<HTMLElement> ripple = span();
		private final HTMLContainerBuilder<HTMLElement> ripple2 = span();
		private final HTMLContainerBuilder<HTMLDivElement> menu = div();
		private final HTMLContainerBuilder<HTMLDivElement> _this;
		public DropDownFilled(HTMLContainerBuilder<HTMLDivElement> e, ListElement<?> listElement) {
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
		private final HTMLContainerBuilder<HTMLElement> outline = span();
		private final HTMLContainerBuilder<HTMLElement> outlineLeading = span();
		private final HTMLContainerBuilder<HTMLElement> outlineNotch = span();
		private final HTMLContainerBuilder<HTMLElement> outlineTrailing = span();
		private final HTMLContainerBuilder<HTMLDivElement> menu = div();
		private final HTMLContainerBuilder<HTMLDivElement> _this;
		public DropDownOutlined(HTMLContainerBuilder<HTMLDivElement> e, ListElement<?> listElement) {
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
		@JsProperty public MDCDropdownFoundation foundation;
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
	private final static class MDCDropdownFoundation {
		public native void setSelectedIndex(int idx);
	}
}
