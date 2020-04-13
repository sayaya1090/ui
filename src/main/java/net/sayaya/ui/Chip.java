package net.sayaya.ui;

import elemental2.dom.Element;
import elemental2.dom.EventListener;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import jsinterop.base.JsPropertyMap;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.sayaya.ui.animate.Animation;

import java.util.LinkedList;

import static net.sayaya.ui.animate.Animation.animate;
import static org.jboss.gwt.elemento.core.Elements.*;
import static org.jboss.gwt.elemento.core.Elements.i;

public class Chip implements IsHTMLElement<HTMLElement, Chip> {
	private final HTMLDivElement ripple = div().css("mdc-chip__ripple").element();
	private final HTMLElement text = span().css("mdc-chip__text").element();
	private final HTMLElement btn = span().css("mdc-chip__primary-action").attr("role", "button")
										  .add(text)
										  .element();
	private final HTMLElement cell = span().attr("role", "gridcell")
										   .add(btn)
										   .element();
	private final HTMLDivElement _this = div().css("mdc-chip").attr("role", "row")
											  .add(ripple)
											  .add(cell)
											  .element();
	Chip(String text) {
		this.text.innerHTML = text;
	}
	public static ChipBuilder chip() {
		return ChipBuilder.builder();
	}
	native static void inject(Element elem) /*-{
        $wnd.mdc.chips.MDCChip.attachTo(elem);
    }-*/;
	final void inject() {
		inject(_this);
	}
	public final Chip iconLeading(String icon) {
		HTMLElement i = i().css("material-icons", "mdc-chip__icon", "mdc-chip__icon--leading").add(icon).element();
		_this.insertBefore(i, cell);
		return self();
	}
	public final Chip iconTrailing(String icon) {
		HTMLElement i = i().css("material-icons", "mdc-chip__icon", "mdc-chip__icon--trailing").add(icon).element();
		_this.insertBefore(i, cell.nextSibling);
		return self();
	}
	public String value() {
		return this.text.innerHTML;
	}
	@Override
	public HTMLElement element() {
		return _this;
	}

	@Setter
	@Accessors(fluent=true)
	public final static class ChipBuilder {
		private String text;
		private final LinkedList<EventListener> removeListeners = new LinkedList<>();
		private String iconLeading;
		private ChipBuilder(){}
		static ChipBuilder builder() {
			return new ChipBuilder();
		}
		public ChipBuilder text(String text) {
			this.text = text;
			return this;
		}
		public ChipBuilder removable() {
			return removable(evt->{});
		}
		public ChipBuilder removable(EventListener listener) {
			removeListeners.add(listener);
			return this;
		}
		public Chip build() {
			Chip elem = new Chip(text);
			if(!removeListeners.isEmpty()) {
				elem.iconLeading("close");
				elem.element()
						.querySelector(".mdc-chip__icon--leading")
						.addEventListener("click", evt->{
							Animation.AnimationImpl fade = animate(elem.element(), 150, JsPropertyMap.of("opacity", "1"), JsPropertyMap.of("opacity", "0"));
							fade.onfinish = ()->{
								elem.element().remove();
								for(EventListener listener: removeListeners) listener.handleEvent(evt);
							};
						});
			} else if(iconLeading!=null) elem.iconLeading(iconLeading);
			elem.inject();
			return elem;
		}
	}
}
