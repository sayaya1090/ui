package net.sayaya.ui.input;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.DOM;
import elemental2.core.JsDate;
import elemental2.dom.*;
import net.sayaya.ui.Focusable;
import net.sayaya.ui.IsHTMLElement;
import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.event.HasClickHandlers;
import net.sayaya.ui.event.HasValueChangeHandlers;
import org.jboss.elemento.InputType;
import org.jboss.elemento.InputBuilder;

import static org.jboss.elemento.Elements.i;

public abstract class TextField<V, W extends TextField<V, W>> implements IsHTMLElement<HTMLElement, W>, Focusable<W>, HasValueChangeHandlers<V>, HasClickHandlers {
	public static TextFieldBuilder<String, ?> textBox() {
		InputBuilder<HTMLInputElement> builder = org.jboss.elemento.Elements.input(InputType.text);
		return new TextFieldBuilder<>(new TextFieldBuilder.TextFieldSetting<String>().builder(builder).getter(()->builder.element().value).settter(v->builder.element().value = v));
	}
	public static TextFieldBuilder<Double, ?> numberBox() {
		InputBuilder<HTMLInputElement> builder = org.jboss.elemento.Elements.input(InputType.number);
		return new TextFieldBuilder<>(new TextFieldBuilder.TextFieldSetting<Double>().builder(builder).getter(()->builder.element().valueAsNumber).settter(v->builder.element().value=v!=null?String.valueOf(v):null));
	}
	public static TextFieldBuilder<String, ?> emailBox() {
		InputBuilder<HTMLInputElement> builder = org.jboss.elemento.Elements.input(InputType.email);
		return new TextFieldBuilder<>(new TextFieldBuilder.TextFieldSetting<String>().builder(builder).getter(()->builder.element().value).settter(v->builder.element().value = v));
	}
	public static TextFieldBuilder<JsDate, ?> dateBox() {
		InputBuilder<HTMLInputElement> builder = org.jboss.elemento.Elements.input(InputType.date);
		return new TextFieldBuilder<>(new TextFieldBuilder.TextFieldSetting<JsDate>().builder(builder).getter(()->builder.element().valueAsDate).settter(v->builder.element().valueAsDate = v));
	}
	public static TextFieldBuilder<String, ?> fileBox() {
		InputBuilder<HTMLInputElement> builder = org.jboss.elemento.Elements.input(InputType.file).style("position: relative; top: calc(50% - 15px);");
		return new TextFieldBuilder<>(new TextFieldBuilder.TextFieldSetting<String>().builder(builder).getter(()->builder.element().value).settter(v->{DomGlobal.console.log("Unimplemented method: InputType.file -> setValue");}));
	}
	public static TextFieldBuilder<String, ?> password() {
		InputBuilder<HTMLInputElement> builder = org.jboss.elemento.Elements.input(InputType.password);
		return new TextFieldBuilder<>(new TextFieldBuilder.TextFieldSetting<String>().builder(builder).getter(()->builder.element().value).settter(v->builder.element().value = v));
	}
	private final HTMLLabelElement div;
	private final HTMLLabelElement label = org.jboss.elemento.Elements.label().css("mdc-floating-label").element();
	private final InputBuilder<HTMLInputElement> builder;
	protected TextField(InputBuilder<HTMLInputElement> builder) {
		// this.input = ;
		this.builder = builder;
		// input.classList.add("mdc-text-field__input");
		String id = DOM.createUniqueId();
		builder.css("mdc-text-field__input").id(id);
		label.setAttribute("for", id);
		div = initialize();
	}
	protected abstract HTMLLabelElement initialize();
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
			input().setAttribute("disabled", true);
		} else {
			div.classList.remove("mdc-text-field--disabled");
			input().removeAttribute("disabled");
		}
		return self();
	}
	public final W label(String label) {
		this.label.innerHTML = label;
		return self();
	}
	public final HTMLLabelElement label() {
		return label;
	}
	public final W iconLeading(String icon) {
		HTMLElement i = i().css("material-icons", "mdc-text-field__icon", "mdc-text-field__icon--leading").add(icon).element();
		div.classList.add("mdc-text-field--with-leading-icon");
		div.insertBefore(i, div.firstChild);
		return self();
	}
	public final W iconTrailing(String icon) {
		HTMLElement i = i().css("material-icons", "mdc-text-field__icon", "mdc-text-field__icon--trailing").add(icon).element();
		div.classList.add("mdc-text-field--with-trailing-icon");
		div.insertBefore(i, input());
		return self();
	}
	public final W fireEvent(Event evt) {
		input().dispatchEvent(evt);
		return self();
	}
	@Override
	public final W accessKey(char key) {
		input().setAttribute("accessKey", String.valueOf(key));
		return self();
	}

	@Override
	public final W focus() {
		input().focus();
		return self();
	}

	@Override
	public final HandlerRegistration addValueChangeHandler(ValueChangeEventListener<V> listener) {
		return addValueChangeHandler(input(), listener);
	}
	@Override
	public final HandlerRegistration addClickHandler(EventListener listener) {
		return addClickHandler(input(), listener);
	}

	@Override
	public final HTMLElement element() {
		return div;
	}

	public final HTMLInputElement input() {
		return builder.element();
	}
}
