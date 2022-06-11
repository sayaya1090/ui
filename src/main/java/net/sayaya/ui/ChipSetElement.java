package net.sayaya.ui;

import elemental2.dom.Element;
import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.sayaya.ui.event.HasValueChangeHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;
import org.jboss.elemento.HtmlContentBuilder;

import java.util.HashSet;
import java.util.Set;

import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.EventType.bind;

public class ChipSetElement extends HTMLElementBuilder<HTMLDivElement, ChipSetElement> implements HasValueChangeHandlers<ChipElement[]> {
	public static ChipSetElement chips() {
		ChipSetElement elem =  new ChipSetElement(div().css("mdc-chip-set").attr("role", "grid"));
		bind(elem, "DOMNodeInserted", evt->{
			elem._mdc = inject(elem.element());
		});
		return elem;
	}
	public static ChipSetElement choices() {
		ChipSetElement elem =  new ChipSetElement(div().css("mdc-chip-set", "mdc-chip-set--choice").attr("role", "grid"));
		bind(elem, "DOMNodeInserted", evt->{
			elem._mdc = inject(elem.element());
		});
		return elem;
	}
	public static ChipSetElement filters(ChipElementCheckable... chips) {
		ChipSetElement elem =  new ChipSetElement(div().css("mdc-chip-set", "mdc-chip-set--filter").attr("role", "grid"));
		for(ChipElementCheckable c: chips) elem.add(c);
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
	private final Set<ValueChangeEventListener<ChipElement[]>> listeners = new HashSet<>();
	private final Set<ChipElement> chips = new HashSet<>();
	private final HtmlContentBuilder<HTMLDivElement> _this;
	private MdcChipSet _mdc;
	private ChipSetElement(HtmlContentBuilder<HTMLDivElement> element) {
		super(element);
		_this = element;
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

	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	@Setter(onMethod_ = {@JsOverlay})
	@Accessors(fluent=true)
	private static final class MdcChipSet {
		private ChipElement.MdcChip[] chips;
		public native void addChip(Element elem);
	}
}
