package net.sayaya.ui.sheet.column;

import elemental2.dom.HTMLTableCellElement;

@FunctionalInterface
public interface ColumnStyleFn<T> {
	T apply(HTMLTableCellElement td, int row, String prop, String value);
}
