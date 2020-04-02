package net.sayaya.ui.button;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import elemental2.dom.EventListener;
import elemental2.dom.HTMLButtonElement;
import net.sayaya.ui.Focusable;
import net.sayaya.ui.IsHTMLElement;
import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.event.HasClickHandlers;
import net.sayaya.ui.input.CheckBox;

import static org.jboss.gwt.elemento.core.Elements.button;

public abstract class Button<W extends Button<W>> implements IsHTMLElement<HTMLButtonElement, W>, Focusable<W>, HasClickHandlers {
	public interface Resource extends ClientBundle {
		@Source("Button.gss")
		Resource.Style style();

		interface Style extends CssResource {
			String button();
			String flat();
			String outline();
			String contain();
			String toggle();
		}
	}
	private static final Resource RESOURCE =  GWT.create(Resource.class);
	public static final Resource.Style GSS = RESOURCE.style();
	static {
		RESOURCE.style().ensureInjected();
	}
	private final HTMLButtonElement _this;
	protected Button(String[] classes) {
		_this = button().css(classes).element();
	}
	@Override
	public final HTMLButtonElement element() {
		return _this;
	}
	@Override
	public final HandlerRegistration addClickHandler(EventListener listener) {
		return addClickHandler(_this, listener);
	}
	public final W enabled(boolean enabled) {
		if(enabled) _this.removeAttribute("disabled");
		else _this.setAttribute("disabled", true);
		return self();
	}
	@Override
	public W accessKey(char key) {
		_this.setAttribute("accessKey", String.valueOf(key));
		return self();
	}
	@Override
	public W focus() {
		_this.focus();
		return self();
	}
}
