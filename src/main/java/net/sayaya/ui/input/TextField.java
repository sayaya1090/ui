package net.sayaya.ui.input;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.DOM;
import elemental2.core.JsDate;
import elemental2.dom.*;
import net.sayaya.ui.Focusable;
import net.sayaya.ui.event.HasClickHandlers;
import net.sayaya.ui.event.HasValueChangeHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.InputType;
import org.jboss.elemento.InputBuilder;

import static org.jboss.elemento.Elements.i;

public abstract class TextField<V> extends HTMLElement implements HasValueChangeHandlers<V>, HasClickHandlers {
/*	public static TextFieldBuilder<String, ?> textBox() {
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
	}*/
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
	public abstract TextField<V> value(V value);
	native static void inject(Element elem) /*-{
        $wnd.mdc.textField.MDCTextField.attachTo(elem);
    }-*/;
	final void inject() {
		inject(div);
	}
	/*public final TextField<V> enabled(boolean enabled) {
		if(!enabled) {
			div.classList.add("mdc-text-field--disabled");
			input().setAttribute("disabled", true);
		} else {
			div.classList.remove("mdc-text-field--disabled");
			input().removeAttribute("disabled");
		}
		return self();
	}
	public final TextField<V> label(String label) {
		this.label.innerHTML = label;
		return self();
	}*/
	public final HTMLLabelElement label() {
		return label;
	}
/*	public final TextField<V> iconLeading(String icon) {
		HTMLElement i = i().css("material-icons", "mdc-text-field__icon", "mdc-text-field__icon--leading").add(icon).element();
		div.classList.add("mdc-text-field--with-leading-icon");
		div.insertBefore(i, div.firstChild);
		return self();
	}
	public final TextField<V> iconTrailing(String icon) {
		HTMLElement i = i().css("material-icons", "mdc-text-field__icon", "mdc-text-field__icon--trailing").add(icon).element();
		div.classList.add("mdc-text-field--with-trailing-icon");
		div.insertBefore(i, input());
		return self();
	}
	public final TextField<V> fireEvent(Event evt) {
		input().dispatchEvent(evt);
		return self();
	}
*/
	@Override
	public V value() {
		return null;
	}

	@Override
	public final HandlerRegistration onValueChange(ValueChangeEventListener<V> listener) {
		return onValueChange(input(), listener);
	}
	@Override
	public final HandlerRegistration onClick(EventListener listener) {
		return onClick(input(), listener);
	}

//	@Override
	public final HTMLElement element() {
		return div;
	}

	public final HTMLInputElement input() {
		return builder.element();
	}
}
