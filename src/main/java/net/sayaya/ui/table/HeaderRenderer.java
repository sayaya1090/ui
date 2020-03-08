package net.sayaya.ui.table;

import elemental2.dom.HTMLElement;

@FunctionalInterface
public interface HeaderRenderer {
	void render(HTMLElement elem, int row, int col);
}
