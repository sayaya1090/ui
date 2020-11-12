package net.sayaya.ui;

import com.google.gwt.core.client.Scheduler;
import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import lombok.Setter;
import lombok.experimental.Accessors;
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
		bind(elem, "DOMNodeInserted", evt->{
			elem._mdc = inject(elem.element());
		});
		return elem;
	}
	public static ChipSet choices() {
		ChipSet elem =  new ChipSet(div().css("mdc-chip-set", "mdc-chip-set--choice").attr("role", "grid"));
		bind(elem, "DOMNodeInserted", evt->{
			elem._mdc = inject(elem.element());
		});
		return elem;
	}
	public static ChipSet filters(ChipCheckable... chips) {
		ChipSet elem =  new ChipSet(div().css("mdc-chip-set", "mdc-chip-set--filter").attr("role", "grid"));
		for(ChipCheckable c: chips) elem.add(c);
		elem._mdc = inject(elem.element());
		for(int i = 0; i < chips.length; ++i) {
			chips[i]._mdc = elem._mdc.chips[i];
			if(chips[i].value) chips[i].value(true);
		}
		return elem;
	}
	native static MdcChipSet inject(Element elem) /*-{
        return $wnd.mdc.chips.MDCChipSet.attachTo(elem);
    }-*/;
	private final Set<ValueChangeEventListener<Chip[]>> listeners = new HashSet<>();
	private final Set<Chip> chips = new HashSet<>();
	private final HtmlContentBuilder<HTMLDivElement> _this;
	private MdcChipSet _mdc;
	private ChipSet(HtmlContentBuilder<HTMLDivElement> element) {
		super(element);
		_this = element;
	}
	public final ChipSet add(final Chip chip) {
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
	public final ChipSet add(final ChipCheckable chip) {
		if(_mdc!=null) _mdc.addChip(chip.element());
		_this.add(chip.element());
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

	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	@Setter(onMethod_ = {@JsOverlay})
	@Accessors(fluent=true)
	private static final class MdcChipSet {
		private Chip.MdcChip[] chips;
		public native void addChip(Element elem);
	}
}
