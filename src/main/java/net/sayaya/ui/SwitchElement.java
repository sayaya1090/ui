package net.sayaya.ui;

import com.google.gwt.core.client.Scheduler;
import elemental2.dom.Element;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLInputElement;
import elemental2.dom.HTMLLabelElement;
import net.sayaya.ui.event.HasValueChangeHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.InputBuilder;
import org.jboss.elemento.InputType;

import java.util.concurrent.ScheduledExecutorService;

import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.Elements.input;

public class SwitchElement extends HTMLElementBuilder<HTMLDivElement, SwitchElement> implements HasValueChangeHandlers<Boolean> {
	public static SwitchElement sw() {
		return sw(false);
	}
	public static SwitchElement sw(boolean init) {
		SwitchElement elem = new SwitchElement(div());
		elem.checkbox.checked(init);
		inject(elem.element());
	//	elem.foundation = foundation(elem._mdc);
		return elem;
	}
	private native static void inject(Element elem) /*-{
       $wnd.mdc.switchControl.MDCSwitch.attachTo(elem);
    }-*/;
	private final InputBuilder<HTMLInputElement> checkbox = input(InputType.checkbox).css("mdc-switch__native-control").id("basic-switch").attr("role", "switch");
	private final HTMLLabelElement label = org.jboss.elemento.Elements.label().element();
	private final HtmlContentBuilder<HTMLDivElement> track = div().css("mdc-switch__track").add(label);
	private final HtmlContentBuilder<HTMLDivElement> underlay = div().css("mdc-switch__thumb-underlay")
																	 .add(div().css("mdc-switch__thumb"))
																	 .add(checkbox);
	private final HtmlContentBuilder<HTMLDivElement> _this;
	public SwitchElement(HtmlContentBuilder<HTMLDivElement> e) {
		super(e.css("mdc-switch"));
		_this = e;
		_this.add(track).add(underlay);
	}
	public SwitchElement text(String text) {
		label.innerHTML = text;
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
}
