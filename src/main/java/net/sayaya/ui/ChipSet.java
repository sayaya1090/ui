package net.sayaya.ui;

import elemental2.dom.Element;
import elemental2.dom.HTMLElement;
import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.event.HasValueChangeHandlers;

import java.util.HashSet;
import java.util.Set;

import static org.jboss.gwt.elemento.core.Elements.div;

public final class ChipSet implements IsHTMLElement<HTMLElement, ChipSet>, HasValueChangeHandlers<Chip[]> {
	private final HTMLElement _this = div().css("mdc-chip-set").attr("role", "grid").element();
	private final Set<ValueChangeEventListener<Chip[]>> listeners = new HashSet<>();
	private final Set<Chip> chips = new HashSet<>();
	public ChipSet() {
		inject();
	}
	native static void inject(Element elem) /*-{
        $wnd.mdc.chips.MDCChipSet.attachTo(elem);
    }-*/;
	final void inject() {
		inject(_this);
	}
	public final ChipSet add(final Chip chip) {
		_this.appendChild(chip.element());
		chips.add(chip);
		chip.addDetachHandler(evt->chips.remove(chip));
		return this;
	}
	@Override
	public HTMLElement element() {
		return _this;
	}
	@Override
	public final Chip[] value() {
		return chips.toArray(new Chip[0]);
	}
	public final ChipSet fireEvent(ValueChangeEvent<Chip[]> evt) {
		Chip[] value = value();
		evt.value(value);
		for(ValueChangeEventListener<Chip[]> listener: listeners) listener.handleEvent(evt);
		return self();
	}
	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeEventListener<Chip[]> listener) {
		listeners.add(listener);
		return ()->listeners.remove(listener);
	}
}
