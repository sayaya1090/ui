package net.sayaya.ui;

import elemental2.dom.*;
import elemental2.svg.SVGElement;
import elemental2.svg.SVGPathElement;
import jsinterop.base.JsPropertyMap;
import net.sayaya.ui.event.HasAttachHandlers;
import net.sayaya.ui.event.HasDetachHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.EventType;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;

import static net.sayaya.ui.Animation.animate;
import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.Elements.span;

public final class ChipCheckable extends HTMLElementBuilder<HTMLDivElement, ChipCheckable> implements HasAttachHandlers, HasDetachHandlers {
	private final static String SVG_NAMESPACE = "http://www.w3.org/2000/svg";
	private static native Chip.MdcChip inject(Element elem) /*-{
        return $wnd.mdc.chips.MDCChip.attachTo(elem);
    }-*/;
	private final HtmlContentBuilder<HTMLDivElement> ripple = div().css("mdc-chip__ripple");
	private IsElement<?> iconBefore;
	private final IsElement<?> check = span().css("mdc_chip__checkmark").add(checkmark());
	private final HtmlContentBuilder<HTMLElement> label = span().css("mdc-chip__text");
	private final HtmlContentBuilder<HTMLElement> btn = span().css("mdc-chip__primary-action").attr("role", "check").attr("tabindex", "0")
															  .add(label);
	private final HtmlContentBuilder<HTMLElement> cell = span().attr("role", "gridcell")
															   .add(btn);
	private IsElement<?> iconTrailing;
	private final HtmlContentBuilder<HTMLDivElement> _this;
	Chip.MdcChip _mdc;
	boolean value;
	ChipCheckable(HtmlContentBuilder<HTMLDivElement> e) {
		super(e);
		_this = e;
		layout();
	}
	protected void layout() {
		clear();
		_this.add(ripple);
		if(iconBefore!=null) _this.add(iconBefore);
		_this.add(check);
		_this.add(cell);
		if(iconTrailing!=null) _this.add(iconTrailing);
	}
	public ChipCheckable text(String text) {
		label.textContent(text);
		return that();
	}
	public String text() {
		return label.element().innerHTML;
	}
	public ChipCheckable before(Icon icon) {
		if(icon!=null) icon.css("mdc-chip__icon", "mdc-chip__icon--leading");
		this.iconBefore = icon;
		layout();
		return that();
	}
	public ChipCheckable trailing(Icon icon) {
		if(icon!=null) icon.css("mdc-chip__icon", "mdc-chip__icon--trailing");
		this.iconTrailing = icon;
		layout();
		return that();
	}
	public ChipCheckable removable() {
		Icon remove = Icon.icon("close");
		remove.on(EventType.click, evt->{
			Animation.AnimationImpl fade = animate(element(), 150, JsPropertyMap.of("opacity", "1"), JsPropertyMap.of("opacity", "0"));
			if(fade!=null) fade.onfinish = () -> element().remove();
			else element().parentElement.removeChild(element());
		});
		trailing(remove);
		return this;
	}
	public ChipCheckable value(boolean value) {
		this.value = value;
		if(_mdc!=null) _mdc.selected = value;
		return this;
	}
	public boolean value() {
		if(_mdc!=null) return _mdc.selected;
		else return value;
	}
	private static SVGElement checkmark() {
		SVGPathElement path = (SVGPathElement) DomGlobal.document.createElementNS(SVG_NAMESPACE, "path");
		path.classList.add("mdc-chip__checkmark-path");
		path.setAttribute("d", "M1.73,12.91 8.1,19.28 22.79,4.59");
		path.setAttribute("fill", "none");
		path.setAttribute("stroke", "black");

		SVGElement svg = (SVGElement) DomGlobal.document.createElementNS(SVG_NAMESPACE, "svg");
		svg.classList.add("mdc-chip__checkmark-svg");
		svg.setAttribute( "viewBox", "-2 -3 30 30");
		svg.appendChild(path);
		return svg;
	}

	@Override
	public HandlerRegistration onAttach(EventListener listener) {
		return onAttach(element(), listener);
	}
	@Override
	public HandlerRegistration onDetach(EventListener listener) {
		return onDetach(element(), listener);
	}
	@Override
	public ChipCheckable that() {
		return this;
	}
}
