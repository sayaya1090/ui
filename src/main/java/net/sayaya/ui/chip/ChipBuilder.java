package net.sayaya.ui.chip;

import elemental2.dom.EventListener;
import elemental2.dom.HTMLElement;
import jsinterop.base.JsPropertyMap;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.sayaya.ui.animate.Animation;

import java.util.LinkedList;

import static net.sayaya.ui.animate.Animation.animate;

@Setter
@Accessors(fluent=true)
public class ChipBuilder {
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
	public Chip element() {
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
