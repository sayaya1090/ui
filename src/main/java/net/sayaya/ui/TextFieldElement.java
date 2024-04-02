package net.sayaya.ui;

import com.google.gwt.i18n.client.DateTimeFormat;
import elemental2.core.JsDate;
import elemental2.dom.*;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;
import net.sayaya.ui.event.HasClickHandlers;
import net.sayaya.ui.event.HasValueChangeHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.Elements;
import org.jboss.elemento.HTMLContainerBuilder;
import org.jboss.elemento.InputElementBuilder;
import org.jboss.elemento.InputType;

import java.util.Date;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.Elements.span;

public abstract class TextFieldElement<V, B extends TextFieldElement<V, B>> extends HTMLElementBuilder<HTMLLabelElement, B> implements HasValueChangeHandlers<V>, HasClickHandlers {
	@Setter
	@Accessors(fluent = true)
	public final static class TextFieldBuilder<V> {
		private InputElementBuilder<HTMLInputElement> input;
		@Setter(AccessLevel.PRIVATE)
		private Supplier<V> getter;
		@Setter(AccessLevel.PRIVATE)
		private Function<V, String> setter;

		public TextFieldFilled<V> filled() {
			return new TextFieldFilled<>(Elements.label(), input, getter, setter).css("mdc-text-field--filled");
		}

		public TextFieldOutlined<V> outlined() {
			return new TextFieldOutlined<>(Elements.label(), input, getter, setter).css("mdc-text-field--outlined");
		}
	}
	public static TextFieldBuilder<String> textBox() {
		InputElementBuilder<HTMLInputElement> input = Elements.input(InputType.text).css("mdc-text-field__input");
		return new TextFieldBuilder<String>().input(input)
											 .setter(v->v!=null?v:"")
											 .getter(()->input.element().value);
	}
	public static TextFieldBuilder<Double> numberBox() {
		InputElementBuilder<HTMLInputElement> input = Elements.input(InputType.number).css("mdc-text-field__input").style("text-align: right; padding-right: 0px;");
		return new TextFieldBuilder<Double>().input(input)
											 .setter(v->v!=null?String.valueOf(v):"")
											 .getter(()->input.element().valueAsNumber);
	}
	public static TextFieldBuilder<String> emailBox() {
		InputElementBuilder<HTMLInputElement> input = Elements.input(InputType.email).css("mdc-text-field__input");
		return new TextFieldBuilder<String>().input(input)
											 .setter(v->v!=null?v:"")
											 .getter(()->input.element().value);
	}
	private final static DateTimeFormat DTF = DateTimeFormat.getFormat("yyyy-MM-dd");
	public static TextFieldBuilder<JsDate> dateBox() {
		InputElementBuilder<HTMLInputElement> input = Elements.input(InputType.date).css("mdc-text-field__input");
		return new TextFieldBuilder<JsDate>().input(input)
											 .setter(v->{
											 	if(v==null) return "";
											 	return DTF.format(new Date((long) v.getTime()));
											 }).getter(()->input.element().valueAsDate);
	}
	private final static DateTimeFormat DTF2 = DateTimeFormat.getFormat("yyyy-MM-dd'T'HH:mm");
	public static TextFieldBuilder<JsDate> datetimeBox() {
		InputElementBuilder<HTMLInputElement> input = Elements.input(InputType.datetime).css("mdc-text-field__input").attr("type", "datetime-local");
		return new TextFieldBuilder<JsDate>().input(input)
				.setter(v->{
					if(v==null) return "";
					return DTF2.format(new Date((long) v.getTime()));
				}).getter(()->{try {
					return new JsDate((double)(DTF2.parse(input.element().value).getTime()));
				} catch(Exception e) {return null;}});
	}
	public static TextFieldBuilder<String> fileBox() {
		InputElementBuilder<HTMLInputElement> input = Elements.input(InputType.file).css("mdc-text-field__input").style("position: relative; top: calc(50% - 15px);");
		return new TextFieldBuilder<String>().input(input)
											 .setter(v->v!=null?v:"")
											 .getter(()->input.element().value);
	}
	public static TextFieldBuilder<String> password() {
		InputElementBuilder<HTMLInputElement> input = Elements.input(InputType.password).css("mdc-text-field__input");
		return new TextFieldBuilder<String>().input(input)
											 .setter(v->v!=null?v:"")
											 .getter(()->input.element().value);
	}
	protected final HTMLContainerBuilder<HTMLLabelElement> _this;
	protected final InputElementBuilder<HTMLInputElement> input;
	protected final HTMLContainerBuilder<HTMLElement> label;
	private final Supplier<V> getter;
	private final Function<V, String> setter;
	@Delegate protected MDCTextField _mdc;
	public TextFieldElement(HTMLContainerBuilder<HTMLLabelElement> e, InputElementBuilder<HTMLInputElement> input, Supplier<V> getter, Function<V, String> setter) {
		super(e);
		this._this = e.css("mdc-text-field");
		this.input = input;
		label = span();
		input.aria("labelledby", label.id().element().id);
		this.getter = getter;
		this.setter = setter;
		layout();
		_mdc = new MDCTextField(element());
	}
	protected abstract void layout();
	public final InputElementBuilder<HTMLInputElement> input() {
		return input;
	}
	@Override
	public final HandlerRegistration onClick(EventListener listener) {
		return onClick(input.element(), listener);
	}
	@Override
	public final V value() {
		return getter.get();
	}
	public final B value(V value) {
		this._mdc.value=setter.apply(value);
		return that();
	}
	public final B text(String label) {
		this.label.textContent(label);
		return that();
	}
	public String text() {
		return this.element().innerHTML;
	}
	public B before(IconElement iconElement) {
		ncss("mdc-text-field--with-leading-icon");
		var icons = element().getElementsByClassName("mdc-text-field__icon--leading");
		if(icons!=null) for(var icon: icons.asList()) icon.remove();
		if(iconElement !=null) {
			css("mdc-text-field--with-leading-icon").element().prepend(iconElement.attr("role", "button").css("mdc-text-field__icon", "mdc-text-field__icon--leading").element());
		}
		return that();
	}
	public B trailing(IconElement iconElement) {
		ncss("mdc-text-field--with-trailing-icon");
		var icons = element().getElementsByClassName("mdc-text-field__icon--trailing");
		if(icons!=null) for(var icon: icons.asList()) icon.remove();
		if(iconElement !=null) {
			css("mdc-text-field--with-trailing-icon");
			input.element().insertAdjacentElement("afterend", iconElement.attr("role", "button").css("mdc-text-field__icon", "mdc-text-field__icon--trailing").element());
		}
		return that();
	}
	public final B enabled(boolean enabled) {
		if(!enabled) {
			css("mdc-text-field--disabled");
			input.attr("disabled", "true");
		} else {
			ncss("mdc-text-field--disabled");
			input.element().removeAttribute("disabled");
		}
		return that();
	}
	public final TextFieldHelper helper(String msg) {
		var helper = new TextFieldHelper(div()).text(msg);
		String id = helper.element().id;
		input.aria("labelledby", id).aria("controls", id).aria("describedby", id);
		return helper;
	}
	public final B fire(Event evt) {
		input.element().dispatchEvent(evt);
		return that();
	}
	public final B required(boolean required) {
		input.required(required);
		return that();
	}
	public final B autocomplete(String autocomplete) {
		input.autocomplete(autocomplete);
		return that();
	}
	public final B autofocus(boolean autofocus) {
		input.autofocus(autofocus);
		return that();
	}
	public final B readOnly(boolean readOnly) {
		input.readOnly(readOnly);
		return that();
	}
	public B floatLabel(boolean shouldFloat) {
		_mdc.foundation.adapter.floatLabel(shouldFloat);
		return that();
	}
	public final boolean checkValidity() {
		return input.element().checkValidity();
	}
	public final boolean reportValidity() {
		return input.element().reportValidity();
	}
	public final void blur() {
		input.element().blur();
	}
	public final void click() {
		input.element().click();
	}
	public final void focus() {
		input.element().focus();
	}
	public final void select() {
		input.element().select();
	}
	@Override
	public final HandlerRegistration onValueChange(ValueChangeEventListener<V> listener) {
		return onValueChange(input.element(), listener);
	}

	public static class TextFieldHelper extends HTMLElementBuilder<HTMLDivElement, TextFieldHelper> {
		private HTMLContainerBuilder<HTMLDivElement> text = div().css("mdc-text-field-helper-text").aria("hidden", "true");
		private TextFieldHelper(HTMLContainerBuilder<HTMLDivElement> elem) {
			super(elem);
			elem.css("mdc-text-field-helper-line").add(text.id());
			new MDCTextFieldHelperText(text.element());
		}
		public TextFieldHelper persistent() {
			text.css("mdc-text-field-helper-text--persistent");
			return that();
		}
		public TextFieldHelper validation() {
			text.css("mdc-text-field-helper-text--validation-msg");
			return that();
		}
		public TextFieldHelper text(String text) {
			this.text.textContent(text);
			return this;
		}
		@Override
		public TextFieldHelper that() {
			return this;
		}
	}
	public static final class TextFieldFilled<V> extends TextFieldElement<V, TextFieldFilled<V>> {
		public TextFieldFilled(HTMLContainerBuilder<HTMLLabelElement> e, InputElementBuilder<HTMLInputElement> i, Supplier<V> getter, Function<V, String> setter) {
			super(e, i, getter, setter);
		}
		@Override
		protected void layout() {
			_this.add(span().css("mdc-text-field__ripple"))
					.add(input)
					.add(label.css("mdc-floating-label"))
					.add(span().css("mdc-line-ripple"));
		}
		@Override
		public TextFieldFilled<V> that() {
			return this;
		}
	}
	public static final class TextFieldOutlined<V> extends TextFieldElement<V, TextFieldOutlined<V>> {
		private HTMLContainerBuilder<HTMLElement> outline;
		public TextFieldOutlined(HTMLContainerBuilder<HTMLLabelElement> e, InputElementBuilder<HTMLInputElement> i, Supplier<V> getter, Function<V, String> setter) {
			super(e, i, getter, setter);
		}
		@Override
		protected void layout() {
			clear();
			outline = span();
			_this.add(input)
					.add(outline.css("mdc-notched-outline")
							.add(span().css("mdc-notched-outline__leading"))
							.add(span().css("mdc-notched-outline__notch")
									.add(label.css("mdc-floating-label")))
							.add(span().css("mdc-notched-outline__trailing")));
		}
		public TextFieldOutlined<V> floatLabel(boolean shouldFloat) {
			super.floatLabel(shouldFloat);
			if(shouldFloat) outline.css("mdc-notched-outline--notched");
			return that();
		}
		@Override
		public TextFieldOutlined<V> that() {
			return this;
		}
	}
	@JsType(isNative = true, namespace="mdc.textField")
	private final static class MDCTextField {
		@JsProperty private String value;
		@JsProperty private boolean disabled;
		@JsProperty private boolean valid;
		@JsProperty private MDCTextFieldFoundation foundation;
		public MDCTextField(Element element){}
	}
	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	private final static class MDCTextFieldFoundation {
		@JsProperty private MDCTextFieldFoundationAdapter adapter;
	}
	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	private final static class MDCTextFieldFoundationAdapter {
		public native void activateLineRipple();
		public native void deactivateLineRipple();
		public native void floatLabel(boolean shouldFloat);
		public native void shakeLabel(boolean shouldShake);
	}
	@JsType(isNative = true, namespace="mdc.textField")
	private final static class MDCTextFieldHelperText {
		public MDCTextFieldHelperText(Element element){}
	}
}
