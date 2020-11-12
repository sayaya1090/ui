package net.sayaya.ui;

import elemental2.dom.Element;
import elemental2.dom.HTMLDivElement;
import net.sayaya.ui.event.HasValueChangeHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.HtmlContentBuilder;

import java.util.HashSet;
import java.util.Set;

import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.EventType.bind;

public class ChipSet extends HTMLElementBuilder<HTMLDivElement, ChipSet> implements HasValueChangeHandlers<Chip[]> {
	public static ChipSet chips() {
		ChipSet elem =  new ChipSet(div().css("mdc-chip-set").attr("role", "grid"));
		bind(elem, "DOMNodeInserted", evt->inject(elem.element()));
		return elem;
	}
	public static ChipSet choices() {
		ChipSet elem =  new ChipSet(div().css("mdc-chip-set", "mdc-chip-set--choice").attr("role", "grid"));
		bind(elem, "DOMNodeInserted", evt->inject(elem.element()));
		return elem;
	}
	public static ChipSet filters(Chip... chips) {
		ChipSet elem =  new ChipSet(div().css("mdc-chip-set", "mdc-chip-set--filter").attr("role", "grid"));
		for(Chip c: chips) elem.add(c);
		bind(elem, "DOMNodeInserted", evt->inject(elem.element()));
		return elem;
	}
	native static void inject(Element elem) /*-{
        $wnd.mdc.chips.MDCChipSet.attachTo(elem);
    }-*/;
	private final Set<ValueChangeEventListener<Chip[]>> listeners = new HashSet<>();
	private final Set<Chip> chips = new HashSet<>();
	private final HtmlContentBuilder<HTMLDivElement> _this;
	private ChipSet(HtmlContentBuilder<HTMLDivElement> element) {
		super(element);
		_this = element;

	}
	public final ChipSet add(final Chip chip) {
		_this.add(chip);
		chips.add(chip);
		/*chip.onDetach(evt->{
			chips.remove(chip);
			fire(ValueChangeEvent.event(evt, value()));
		});*/
		return this;
	}

	public final ChipSet fire(ValueChangeEvent<Chip[]> evt) {
		Chip[] value = value();
		evt.value(value);
		for(ValueChangeEventListener<Chip[]> listener: listeners) listener.handle(evt);
		return that();
	}

	@Override
	public Chip[] value() {
		return chips.toArray(new Chip[0]);
	}

	@Override
	public HandlerRegistration onValueChange(ValueChangeEventListener<Chip[]> listener) {
		listeners.add(listener);
		return ()->listeners.remove(listener);
	}

	@Override
	public ChipSet that() {
		return this;
	}
}
