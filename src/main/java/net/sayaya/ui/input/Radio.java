package net.sayaya.ui.input;

import elemental2.dom.*;
import net.sayaya.ui.Focusable;
import net.sayaya.ui.Icon;
import net.sayaya.ui.IsHTMLElement;
import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.event.HasValueChangeHandlers;
import org.jboss.gwt.elemento.core.InputType;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.input;

public class Radio<T> implements IsHTMLElement<HTMLDivElement, Radio<T>>, Focusable<Radio<T>>, HasValueChangeHandlers<T> {
	private final HTMLInputElement input = input(InputType.radio).css("mdc-radio__native-control").element();
	private final HTMLDivElement _this = div().css("mdc-radio")
											  .add(input)
											  .add(div().css("mdc-radio__background")
														.add(div().css("mdc-radio__outer-circle").style("box-sizing: border-box;"))
														.add(div().css("mdc-radio__inner-circle").style("box-sizing: border-box;")))
											  .add(div().css("mdc-radio__ripple"))
											  .element();
	private T value;
	private Radio(String group) {
		input.setAttribute("name", group);
		inject();
	}
	native static void inject(Element elem) /*-{
        $wnd.mdc.radio.MDCRadio.attachTo(elem);
    }-*/;
	final void inject() {
		inject(_this);
	}
	@Override
	public Radio<T> accessKey(char key) {
		input.setAttribute("accessKey", String.valueOf(key));
		return self();
	}
	@Override
	public Radio<T> focus() {
		input.focus();
		return self();
	}
	@Override
	public HTMLDivElement element() {
		return _this;
	}
	@Override
	public T value() {
		if("on".equals(input.value)) return value;
		return null;
	}
	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeEventListener<T> listener) {
		return addValueChangeHandler(input, listener);
	}
	public static <T> RadioBuilder<T> radio(String group, T value) {
		return new RadioBuilder<>(group, value);
	}
	public final static class RadioBuilder<T> {
		private final String group;
		private final T value;
		private RadioBuilder(String group, T value){
			this.group = group;
			this.value = value;
		}
		public Radio<T> element() {
			Radio<T> elem = new Radio<>(group);
			elem.value = value;
			return elem;
		}
	}
}
