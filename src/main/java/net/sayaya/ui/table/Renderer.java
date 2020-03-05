package net.sayaya.ui.table;

import elemental2.dom.HTMLElement;

@FunctionalInterface
public interface Renderer<T> {
	HTMLElement render(T value);
}
