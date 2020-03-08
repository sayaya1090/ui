package net.sayaya.ui.table;

import elemental2.dom.HTMLTableCellElement;

@FunctionalInterface
public interface CellRenderer<T> {
	void render(HTMLTableCellElement elem, int dataIdx, int col, T value);
}
