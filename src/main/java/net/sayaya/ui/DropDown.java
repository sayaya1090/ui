package net.sayaya.ui;

import elemental2.dom.*;
import net.sayaya.ui.event.HasValueChangeHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.HtmlContentBuilder;

import static org.jboss.elemento.Elements.div;

public class DropDown extends HTMLElementBuilder<HTMLDivElement, DropDown> implements HasValueChangeHandlers<String> {
	public static DropDown dropdown(List list) {
		DropDown elem = new DropDown(div(), list);
	//	elem._mdc = inject(elem.element());
		return elem;
	}
	/*private static native DropDown.MCDDropDown inject(Element elem) /*-{
        var mdc = $wnd.mdc.checkbox.MDCCheckbox.attachTo(elem.firstChild);
        $wnd.mdc.formField.MDCFormField.attachTo(elem);
        return mdc;
    }-*/;
	/*private final HTMLInputElement input = Elements.input(InputType.checkbox).css("mdc-checkbox__native-control").id().element();
	private final static String SVG_NAMESPACE = "http://www.w3.org/2000/svg";
	private final SVGPathElement path = (SVGPathElement) DomGlobal.document.createElementNS(SVG_NAMESPACE, "path");
	private final SVGElement svg = (SVGElement) DomGlobal.document.createElementNS(SVG_NAMESPACE, "svg");
	private final HtmlContentBuilder<HTMLDivElement> mixedmark = div().css("mdc-checkbox__mixedmark");
	private final HtmlContentBuilder<HTMLDivElement> background = div().css("mdc-checkbox__background").add(svg).add(mixedmark);
	private final HtmlContentBuilder<HTMLDivElement> ripple = div().css("mdc-checkbox__ripple");
	private final HtmlContentBuilder<HTMLDivElement> checkbox = div().css("mdc-checkbox").add(input).add(background).add(ripple);
	private final HtmlContentBuilder<HTMLLabelElement> label = label().attr("for", input.id);*/
	private final HtmlContentBuilder<HTMLDivElement> anchor = div().css("mdc-select__anchor");
	private final HtmlContentBuilder<HTMLDivElement> menu = div().css("mdc-select__menu", "mdc-menu", "mdc-menu-surface", "mdc-menu-surface--fullwidth");
	private final List list;
	private final HtmlContentBuilder<HTMLDivElement> _this;
	public DropDown(HtmlContentBuilder<HTMLDivElement> e, List list) {
		super(e);
		_this = e;
		this.list = list;
		layout();
	}

	private void layout() {
		_this.css("mdc-select", "mdc-select--filled")
			 .add(anchor)
			 .add(menu.add(list));
	}

	@Override
	public String value() {
		return null;
	}

	@Override
	public HandlerRegistration onValueChange(ValueChangeEventListener<String> listener) {
		return null;
	}

	@Override
	public DropDown that() {
		return this;
	}
}
