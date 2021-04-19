package net.sayaya.ui;

import com.google.gwt.user.client.DOM;
import elemental2.dom.*;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;
import net.sayaya.ui.event.HasClickHandlers;
import net.sayaya.ui.event.HasValueChangeHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.*;

import java.util.function.Function;
import java.util.function.Supplier;

import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.Elements.span;
import static org.jboss.elemento.EventType.bind;

public abstract class TextArea<V> extends HTMLElementBuilder<HTMLLabelElement, TextArea<V>> implements HasValueChangeHandlers<V>, HasClickHandlers {
	@Setter
	@Accessors(fluent = true)
	public final static class TextFieldBuilder<V> {
		private TextContentBuilder<HTMLTextAreaElement> input;
		@Setter(AccessLevel.PRIVATE)
		private Supplier<V> getter;
		@Setter(AccessLevel.PRIVATE)
		private Function<V, String> setter;

		public TextArea<V> filled() {
			TextFieldFilled<V> elem = new TextFieldFilled<>(Elements.label(), input, getter, setter);
			elem.css("mdc-text-field", "mdc-text-field--textarea", "mdc-text-field--filled");
			bind(elem, "DOMNodeInserted", evt->elem.initialize());
			return elem;
		}

		public TextArea<V> outlined() {
			new TextFieldBuilder<String>();
			TextFieldOutlined<V> elem = new TextFieldOutlined<>(Elements.label(), input, getter, setter);
			elem.css("mdc-text-field", "mdc-text-field--textarea", "mdc-text-field--outlined");
			bind(elem, "DOMNodeInserted", evt->elem.initialize());
			return elem;
		}
	}
	public static TextFieldBuilder<String> textBox() {
		TextContentBuilder<HTMLTextAreaElement> input = Elements.textarea().css("mdc-text-field__input");
		return new TextFieldBuilder<String>().input(input)
											 .setter(v->v!=null?v:"")
											 .getter(()->input.element().value);
	}
	private static native MDCTextField inject(Element elem) /*-{
    	return new $wnd.mdc.textField.MDCTextField(elem);
    }-*/;
	private static native MDCTextFieldFoundation foundation(MDCTextField mdc) /*-{
        return mdc.foundation.adapter;
    }-*/;
	private static native MDCTextFieldHelperText inject2(Element elem) /*-{
        return new $wnd.mdc.textField.MDCTextFieldHelperText(elem);
    }-*/;
	private static native MDCTextFieldHelperTextFoundation foundation(MDCTextFieldHelperText mdc) /*-{
        return mdc.foundation.adapter;
    }-*/;
	private final HtmlContentBuilder<HTMLLabelElement> _this;
	protected IsElement<?> iconBefore;
	protected IsElement<?> iconTrailing;
	private final Supplier<V> getter;
	private final Function<V, String> setter;
	@Delegate
	protected MDCTextField _mdc;
	protected MDCTextFieldFoundation _foundation;
	public TextArea(HtmlContentBuilder<HTMLLabelElement> e, Supplier<V> getter, Function<V, String> setter) {
		super(e);
		_this = e;
		this.getter = getter;
		this.setter = setter;
	}
	public TextArea<V> initialize() {
		_mdc = inject(_this.element());
		_foundation = foundation(_mdc);
		return that();
	}
	protected abstract void layout();
	public abstract TextContentBuilder<HTMLTextAreaElement> input();
	@Override
	public final HandlerRegistration onClick(EventListener listener) {
		return onClick(input().element(), listener);
	}
	@Override
	public final V value() {
		return getter.get();
	}
	public final TextArea<V> value(V value) {
		this._mdc.value=setter.apply(value);
		return that();
	}
	public abstract TextArea<V> text(String label);
	public String text() {
		return this.element().innerHTML;
	}
	public TextArea<V> before(Icon icon) {
		if(icon!=null) icon.attr("role", "button").css("mdc-text-field__icon", "mdc-text-field__icon--leading");
		this.iconBefore = icon;
		layout();
		return that();
	}
	public TextArea<V> trailing(Icon icon) {
		if(icon!=null) icon.attr("role", "button").css("mdc-text-field__icon", "mdc-text-field__icon--trailing");
		this.iconTrailing = icon;
		layout();
		return that();
	}
	public final TextArea<V> enabled(boolean enabled) {
		if(!enabled) {
			css("mdc-text-field--disabled");
			input().attr("disabled", "true");
		} else {
			ncss("mdc-text-field--disabled");
			input().element().removeAttribute("disabled");
		}
		return that();
	}
	public final TextFieldHelper helper(String msg) {
		String id =  DOM.createUniqueId();
		input().aria("labelledby", id).aria("controls", id).aria("describedby", id);
		return new TextFieldHelper(id, div()).text(msg);
	}
	public final TextArea<V> fire(Event evt) {
		input().element().dispatchEvent(evt);
		return that();
	}
	public final TextArea<V> required(boolean required) {
		input().element().setAttribute("required", required);
		return that();
	}
	public final TextArea<V> autocomplete(String autocomplete) {
		input().element().setAttribute("autocomplete", autocomplete);
		return that();
	}
	public final TextArea<V> autofocus(boolean autofocus) {
		input().element().autofocus = autofocus;
		return that();
	}
	public final TextArea<V> readOnly(boolean readOnly) {
		input().element().readOnly = readOnly;
		return that();
	}
	public TextArea<V> floatLabel(boolean shouldFloat) {
		_foundation.floatLabel(shouldFloat);
		return that();
	}
	public final boolean checkValidity() {
		return input().element().checkValidity();
	}
	public final boolean reportValidity() {
		return input().element().reportValidity();
	}
	public final void blur() {
		input().element().blur();
	}
	public final void focus() {
		input().element().focus();
	}
	public final void select() {
		input().element().select();
	}
	@Override
	public final HandlerRegistration onValueChange(ValueChangeEventListener<V> listener) {
		return onValueChange(input().element(), listener);
	}

	public static class TextFieldHelper extends HTMLElementBuilder<HTMLDivElement, TextFieldHelper> {
		private HtmlContentBuilder<HTMLDivElement> text = div().css("mdc-text-field-helper-text").aria("hidden", "true");
		private MDCTextFieldHelperText _mdc;
		private MDCTextFieldHelperTextFoundation _foundation;
		private TextFieldHelper(String id, HtmlContentBuilder<HTMLDivElement> elem) {
			super(elem);
			elem.css("mdc-text-field-helper-line").add(text.id(id));
			bind(text,"DOMNodeInserted", evt->{
				_mdc = inject2(text.element());
				_foundation = foundation(_mdc);
			});
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
	private static class TextFieldFilled<V> extends TextArea<V> {
		private final HtmlContentBuilder<HTMLLabelElement> _this;
		private final TextContentBuilder<HTMLTextAreaElement> input;
		private final HtmlContentBuilder<HTMLElement> resizer = span().css("mdc-text-field__resizer");
		private final HtmlContentBuilder<HTMLElement> rippleInput = span().css("mdc-text-field__ripple");
		private final HtmlContentBuilder<HTMLElement> label = span().css("mdc-floating-label");
		private final HtmlContentBuilder<HTMLElement> rippleLine = span().css("mdc-line-ripple");
		public TextFieldFilled(HtmlContentBuilder<HTMLLabelElement> e, TextContentBuilder<HTMLTextAreaElement> i, Supplier<V> getter, Function<V, String> setter) {
			super(e, getter, setter);
			_this = e;
			this.input = i;
			String id = DOM.createUniqueId();
			label.id(id);
			i.attr("aria-labelledby", id);
			layout();
		}
		@Override
		protected void layout() {
			clear();
			if(iconBefore!=null) {
				_this.add(iconBefore);
				css("mdc-text-field--with-leading-icon");
			} else ncss("mdc-text-field--with-leading-icon");
			_this.add(rippleInput)
				 .add(resizer.add(input));
			if(iconTrailing!=null) {
				_this.add(iconTrailing);
				css("mdc-text-field--with-trailing-icon");
			} else ncss("mdc-text-field--with-trailing-icon");
			_this.add(label)
				 .add(rippleLine);
		}
		@Override
		public final TextFieldFilled<V> text(String label) {
			this.label.textContent(label);
			return that();
		}
		@Override
		public TextContentBuilder<HTMLTextAreaElement> input() {
			return input;
		}

		@Override
		public TextFieldFilled<V> that() {
			return this;
		}
	}
	private static class TextFieldOutlined<V> extends TextArea<V> {
		private final HtmlContentBuilder<HTMLLabelElement> _this;
		private final TextContentBuilder<HTMLTextAreaElement> input;
		private final HtmlContentBuilder<HTMLElement> resizer = span().css("mdc-text-field__resizer");
		private final HtmlContentBuilder<HTMLElement> label = span().css("mdc-floating-label");
		private final HtmlContentBuilder<HTMLElement> outline = span().css("mdc-notched-outline");
		private final HtmlContentBuilder<HTMLElement> outlineLeading = span().css("mdc-notched-outline__leading");
		private final HtmlContentBuilder<HTMLElement> outlineNotch = span().css("mdc-notched-outline__notch");
		private final HtmlContentBuilder<HTMLElement> outlineTrailing = span().css("mdc-notched-outline__trailing");
		public TextFieldOutlined(HtmlContentBuilder<HTMLLabelElement> e, TextContentBuilder<HTMLTextAreaElement> i, Supplier<V> getter, Function<V, String> setter) {
			super(e, getter, setter);
			_this = e;
			this.input = i;
			String id = DOM.createUniqueId();
			label.id(id);
			i.attr("aria-labelledby", id);
			outline.add(outlineLeading).add(outlineNotch).add(outlineTrailing);
			outlineNotch.add(label);
			layout();
		}
		@Override
		protected void layout() {
			clear();
			if(iconBefore!=null) {
				_this.add(iconBefore);
				css("mdc-text-field--with-leading-icon");
			} else ncss("mdc-text-field--with-leading-icon");
			_this.add(resizer.add(input));
			if(iconTrailing!=null) {
				_this.add(iconTrailing);
				css("mdc-text-field--with-trailing-icon");
			} else ncss("mdc-text-field--with-trailing-icon");
			_this.add(outline);
		}
		@Override
		public final TextFieldOutlined<V> text(String label) {
			this.label.textContent(label);
			return that();
		}
		public final TextArea<V> floatLabel(boolean shouldFloat) {
			super.floatLabel(shouldFloat);
			if(shouldFloat) outline.css("mdc-notched-outline--notched");
			return that();
		}
		@Override
		public TextContentBuilder<HTMLTextAreaElement> input() {
			return input;
		}
		@Override
		public TextFieldOutlined<V> that() {
			return this;
		}
	}
	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	private final static class MDCTextField {
		private String value;
		private boolean disabled;
		private boolean valid;
	}
	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	private final static class MDCTextFieldFoundation {
		public native void activateLineRipple();
		public native void deactivateLineRipple();
		public native void shakeLabel(boolean shouldShake);
		public native void floatLabel(boolean shouldFloat);
	}
	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	private final static class MDCTextFieldHelperText {

	}
	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	private final static class MDCTextFieldHelperTextFoundation {

	}
}
