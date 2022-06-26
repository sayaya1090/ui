package net.sayaya.ui;

import elemental2.dom.Element;
import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import net.sayaya.ui.event.HasValueChangeHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.HtmlContentBuilder;

import java.util.HashSet;
import java.util.Set;

import static org.jboss.elemento.Elements.div;

public class ChipSetElement extends HTMLElementBuilder<HTMLDivElement, ChipSetElement> implements HasValueChangeHandlers<ChipElement[]> {
	public static ChipSetElement chips() {
		ChipSetElement elem =  new ChipSetElement(div());
		return elem;
	}
	public static ChipSetElement choices() {
		ChipSetElement elem =  new ChipSetElement(div().css("mdc-chip-set--choice"));
		return elem;
	}
	public static ChipSetElement filters(ChipElementCheckable... chips) {
		ChipSetElement elem =  new ChipSetElement(div().css("mdc-chip-set--filter"));
		for(ChipElementCheckable c: chips) elem.add(c);
		return elem;
	}
	private final Set<ValueChangeEventListener<ChipElement[]>> listeners = new HashSet<>();
	private final Set<ChipElement> chips = new HashSet<>();
	private final HtmlContentBuilder<HTMLDivElement> _this;
	private final MDCChipSet _mdc;
	private ChipSetElement(HtmlContentBuilder<HTMLDivElement> element) {
		super(element);
		_this = element.css("mdc-chip-set").attr("role", "grid");
		_mdc = new MDCChipSet(element());
	}
	public final ChipSetElement add(final ChipElement chip) {
		if(_mdc!=null) _mdc.addChip(chip.element());
		_this.add(chip.element());
		chips.add(chip);
		try {chip.onDetach(evt -> {
			chips.remove(chip);
			fire(ValueChangeEvent.event(evt, value()));
		});} catch(Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	public final ChipSetElement add(final ChipElementCheckable chip) {
		if(_mdc!=null) _mdc.addChip(chip.element());
		_this.add(chip.element());
		return this;
	}

	public final ChipSetElement fire(ValueChangeEvent<ChipElement[]> evt) {
		ChipElement[] value = value();
		evt.value(value);
		for(ValueChangeEventListener<ChipElement[]> listener: listeners) listener.handle(evt);
		return that();
	}

	@Override
	public ChipElement[] value() {
		return chips.toArray(new ChipElement[0]);
	}

	@Override
	public HandlerRegistration onValueChange(ValueChangeEventListener<ChipElement[]> listener) {
		listeners.add(listener);
		return ()->listeners.remove(listener);
	}

	@Override
	public ChipSetElement that() {
		return this;
	}

	@JsType(isNative = true, namespace= "mdc.chips")
	private static final class MDCChipSet {
		@JsProperty private ChipElement.MDCChip[] chips;
		public MDCChipSet(Element elem){}
		public native void addChip(Element elem);
	}
}
