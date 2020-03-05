package net.sayaya.ui.table;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLTableCellElement;
import net.sayaya.ui.IsHTMLElement;

import static org.jboss.gwt.elemento.core.Elements.td;

public class TableCell<T> implements IsHTMLElement<HTMLTableCellElement, TableCell<T>> {
	private final HTMLTableCellElement element;
	private final TableHeaderCell<T> header;
	private final Renderer<T> renderer;
	TableCell(TableHeaderCell<T> header, Renderer<T> renderer) {
		this.header = header;
		this.renderer = renderer;
		element = td().element();
		if(header.getColspan()!=null) element.colSpan = header.getColspan();
		if(header.getRowspan()!=null) element.rowSpan = header.getRowspan();
	}
	public TableCell<T> update(Data data) {
		DomGlobal.console.log("A");
		T value = header.getMapper().map(data);
		DomGlobal.console.log(value);
		element().appendChild(renderer.render(value));
		return this;
	}
	@Override
	public HTMLTableCellElement element() {
		return element;
	}
}
