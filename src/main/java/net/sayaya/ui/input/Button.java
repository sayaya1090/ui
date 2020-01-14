package net.sayaya.ui.input;

<<<<<<< HEAD
import org.jboss.gwt.elemento.core.InputType;

import static org.jboss.gwt.elemento.core.Elements.input;

public abstract class Button<T extends Button<T>> extends InputBase<T> {
	protected Button() {
		super(input(InputType.button).element());
=======
import elemental2.dom.EventListener;
import elemental2.dom.HTMLElement;
import net.sayaya.ui.event.HasClickHandlers;
import org.jboss.gwt.elemento.core.InputType;
import org.jboss.gwt.elemento.core.IsElement;

import static org.jboss.gwt.elemento.core.Elements.input;

public abstract class Button<T extends Button<T>> implements IsElement<HTMLElement>, HasClickHandlers {
	private final HTMLElement _this;

	protected Button() {
		this._this = input(InputType.button).element();
	}
	protected final T self() {
		return (T) this;
	}
	@Override
	public final HTMLElement element() {
		return _this;
	}
	@Override
	public void addClickHandler(EventListener listener) {
		addClickHandler(_this, listener);
	}

	@Override
	public void removeClickHandler(EventListener listener) {
		removeClickHandler(_this, listener);
	}
	public final T setEnabled(boolean enabled) {
		if(enabled) _this.removeAttribute("disabled");
		else _this.setAttribute("disabled", true);
		return self();
>>>>>>> origin/master
	}
}
