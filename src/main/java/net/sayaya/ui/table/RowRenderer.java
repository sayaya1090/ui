package net.sayaya.ui.table;

import elemental2.dom.HTMLTableRowElement;

@FunctionalInterface
public interface RowRenderer {
	SiblingRowRenderers render(HTMLTableRowElement elem, int dataIdx, Data value);

	interface SiblingRowRenderers {
		RowRenderer forward();
		RowRenderer backward();
	}
}
