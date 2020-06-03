package net.sayaya.ui.table;

import elemental2.dom.HTMLTableCellElement;
import net.sayaya.ui.HTMLElementBuilder;
import org.jboss.elemento.HtmlContentBuilder;

public class TableCell<T> extends HTMLElementBuilder<HTMLTableCellElement, TableCell<T>> {
	private final HtmlContentBuilder<HTMLTableCellElement> element;
	private final CellRenderer<T> renderer;
	TableCell(HtmlContentBuilder<HTMLTableCellElement> e, CellRenderer<T> renderer) {
		super(e);
		this.element = e;
		this.renderer = renderer;
	}
	public TableCell<T> update(int dataIdx, int col, T value) {
		renderer.render(element(), dataIdx, col, value);
		return this;
	}

	@Override
	public TableCell<T> that() {
		return null;
	}
}
