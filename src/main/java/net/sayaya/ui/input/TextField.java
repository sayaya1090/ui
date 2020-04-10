package net.sayaya.ui.input;

import com.google.gwt.user.client.DOM;
import elemental2.core.JsDate;
import elemental2.dom.*;
import net.sayaya.ui.Focusable;
import net.sayaya.ui.IsHTMLElement;
import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.event.HasClickHandlers;
import net.sayaya.ui.event.HasValueChangeHandlers;
import org.jboss.gwt.elemento.core.InputType;

import static org.jboss.gwt.elemento.core.Elements.i;

public abstract class TextField<V, W extends TextField<V, W>> implements IsHTMLElement<HTMLElement, W>, Focusable<W>, HasValueChangeHandlers<V>, HasClickHandlers {
	public static TextFieldBuilder<String, ?> textBox() {
		HTMLInputElement input = org.jboss.gwt.elemento.core.Elements.input(InputType.text).element();
		return new TextFieldBuilder<>(new TextFieldBuilder.TextFieldSetting<String>().input(input).getter(()->input.value).settter(v->input.value = v));
	}
	public static TextFieldBuilder<Double, ?> numberBox() {
		HTMLInputElement input = org.jboss.gwt.elemento.core.Elements.input(InputType.number).element();
		return new TextFieldBuilder<>(new TextFieldBuilder.TextFieldSetting<Double>().input(input).getter(()->input.valueAsNumber).settter(v->input.value=v!=null?String.valueOf(v):null));
	}
	public static TextFieldBuilder<String, ?> emailBox() {
		HTMLInputElement input = org.jboss.gwt.elemento.core.Elements.input(InputType.email).element();
		return new TextFieldBuilder<>(new TextFieldBuilder.TextFieldSetting<String>().input(input).getter(()->input.value).settter(v->input.value = v));
	}
	public static TextFieldBuilder<JsDate, ?> dateBox() {
		HTMLInputElement input = org.jboss.gwt.elemento.core.Elements.input(InputType.date).element();
		return new TextFieldBuilder<>(new TextFieldBuilder.TextFieldSetting<JsDate>().input(input).getter(()->input.valueAsDate).settter(v->input.valueAsDate = v));
	}
	public static TextFieldBuilder<String, ?> fileBox() {
		HTMLInputElement input = org.jboss.gwt.elemento.core.Elements.input(InputType.file).element();
		return new TextFieldBuilder<>(new TextFieldBuilder.TextFieldSetting<String>().input(input).getter(()->input.value).settter(v->{DomGlobal.console.log("Unimplemented method: InputType.file -> setValue");}));
	}

	private final HTMLDivElement div;
	protected final HTMLLabelElement label = org.jboss.gwt.elemento.core.Elements.label().css("mdc-floating-label").element();
	protected final HTMLInputElement input;
	protected TextField(HTMLInputElement input) {
		this.input = input;
		input.classList.add("mdc-text-field__input");
		String id = DOM.createUniqueId();
		input.setAttribute("id", id);
		label.setAttribute("for", id);
		div = initialize();
	}
	protected abstract HTMLDivElement initialize();
	public abstract W value(V value);
	native static void inject(Element elem) /*-{
        $wnd.mdc.textField.MDCTextField.attachTo(elem);
    }-*/;
    final void inject() {
    	inject(div);
	}
	public final W enabled(boolean enabled) {
		if(!enabled) {
			div.classList.add("mdc-text-field--disabled");
			input.setAttribute("disabled", true);
		} else {
			div.classList.remove("mdc-text-field--disabled");
			input.removeAttribute("disabled");
		}
		return self();
	}
	public final W label(String label) {
		this.label.innerHTML = label;
		return self();
	}
	public final W iconLeading(String icon) {
		HTMLElement i = i().css("material-icons", "mdc-text-field__icon").style("position: absolute; top: 50%; left: 16px; right: auto; color: rgba(0, 0, 0, 0.54); cursor: default; pointer-events: none;").add(icon).element();
		div.classList.add("mdc-text-field--with-leading-icon");
		div.insertBefore(i, div.firstChild);
		return self();
	}
	public final W iconTrailing(String icon) {
		HTMLElement i = i().css("material-icons", "mdc-text-field__icon").style("position: absolute; top: 50%; left: auto; right: 12px; color: rgba(0, 0, 0, 0.54); cursor: default; pointer-events: none;").add(icon).element();
		div.classList.add("mdc-text-field--with-trailing-icon");
		div.insertBefore(i, input);
		return self();
	}
	public final W fireEvent(Event evt) {
		input.dispatchEvent(evt);
		return self();
	}
	@Override
	public final W accessKey(char key) {
		input.setAttribute("accessKey", String.valueOf(key));
		return self();
	}

	@Override
	public final W focus() {
		input.focus();
		return self();
	}

	@Override
	public final HandlerRegistration addValueChangeHandler(ValueChangeEventListener<V> listener) {
		return addValueChangeHandler(input, listener);
	}
	@Override
	public final HandlerRegistration addClickHandler(EventListener listener) {
		return addClickHandler(input, listener);
	}

	@Override
	public final HTMLElement element() {
		return div;
	}

	public final HTMLInputElement input() {
		return input;
	}
}
