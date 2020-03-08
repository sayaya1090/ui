package net.sayaya.ui.table;

import elemental2.dom.HTMLTableCellElement;
import net.sayaya.ui.IsHTMLElement;

import static org.jboss.gwt.elemento.core.Elements.td;

public class TableCell<T> implements IsHTMLElement<HTMLTableCellElement, TableCell<T>> {
	private final HTMLTableCellElement element;
	private final CellRenderer<T> renderer;
	TableCell(CellRenderer<T> renderer) {
		this.renderer = renderer;
		element = td().element();
	}
	public TableCell<T> update(int dataIdx, int col, T value) {
		renderer.render(element, dataIdx, col, value);
		return this;
	}
	@Override
	public HTMLTableCellElement element() {
		return element;
	}
}
