package net.sayaya.ui.input;

import elemental2.dom.HTMLInputElement;
import net.sayaya.ui.Focusable;
import net.sayaya.ui.IsHTMLElement;
import net.sayaya.ui.event.HasClickHandlers;
import net.sayaya.ui.event.HasValueChangeHandlers;

public interface Input<V, W extends Input<V, W>> extends IsHTMLElement<HTMLInputElement, W>, Focusable<W>, HasValueChangeHandlers<V>, HasClickHandlers {
	W setEnabled(boolean enabled);
}
