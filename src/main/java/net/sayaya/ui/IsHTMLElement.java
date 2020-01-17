package net.sayaya.ui;

import elemental2.dom.HTMLElement;
import net.sayaya.ui.style.Style;
import org.jboss.gwt.elemento.core.IsElement;

public interface IsHTMLElement<E extends HTMLElement, W extends FluentInterface<W>> extends IsElement<E>, FluentInterface<W> {
	default W setStyle(Style style) {
		style.apply(element().style);
		return self();
	}
}
