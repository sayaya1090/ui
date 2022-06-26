package net.sayaya.ui;

import elemental2.dom.Element;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLInputElement;
import jsinterop.annotations.JsType;
import net.sayaya.ui.event.HasValueChangeHandlers;
import net.sayaya.ui.svg.SvgBuilder;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.InputBuilder;
import org.jboss.elemento.InputType;

import static net.sayaya.ui.svg.SvgBuilder.svg;
import static net.sayaya.ui.svg.SvgPathBuilder.path;
import static org.jboss.elemento.Elements.*;

public class SwitchElement extends HTMLElementBuilder<HTMLButtonElement, SwitchElement> implements HasValueChangeHandlers<Boolean> {
	public static SwitchElement sw() {
		return sw(false);
	}
	public static SwitchElement sw(boolean init) {
		SwitchElement elem = new SwitchElement(button());
		elem.checkbox.checked(init);
		return elem;
	}
	private final InputBuilder<HTMLInputElement> checkbox = input(InputType.checkbox);
	private final HtmlContentBuilder<elemental2.dom.HTMLLabelElement> label = label();
	private final HtmlContentBuilder<HTMLDivElement> track = div();
	private final SvgBuilder on = svg();
	private final SvgBuilder off = svg();
	private final HtmlContentBuilder<HTMLDivElement> underlay = div();
	public SwitchElement(HtmlContentBuilder<HTMLButtonElement> e) {
		super(e);
		e.css("mdc-switch", "mdc-switch--unselected").attr("role", "switch").aria("checked", "false")
				.add(track.css("mdc-switch__track").add(label))
				.add(underlay.css("mdc-switch__handle-track")
						.add(div().css("mdc-switch__handle")
								.add(div().css("mdc-switch__shadow")
										.add(div().css("mdc-elevation-overlay")))
								.add(div().css("mdc-switch__ripple"))
								.add(div().css("mdc-switch__icons")
										.add(on.css("mdc-switch__icon", "mdc-switch__icon--on").viewBox(0, 0,24, 24)
												.add(path().d("M19.69,5.23L8.96,15.96l-4.23-4.23L2.96,13.5l6,6L21.46,7L19.69,5.23z"))
												.element())
										.add(off.css("mdc-switch__icon", "mdc-switch__icon--off").viewBox(0, 0,24, 24)
												.add(path().d("M20 13H4v-2h16v2z"))
												.element()))));
		new MDCSwitch(element());
	}
	public SwitchElement text(String text) {
		label.element().innerHTML = text;
		return that();
	}
	@Override
	public Boolean value() {
		return checkbox.element().checked;
	}

	@Override
	public HandlerRegistration onValueChange(ValueChangeEventListener<Boolean> listener) {
		return onValueChange(checkbox.element(), listener);
	}
	@Override
	public SwitchElement that() {
		return this;
	}

	@JsType(isNative = true, namespace="mdc.switchControl")
	static final class MDCSwitch {
		public MDCSwitch(Element elem){}
	}
}
