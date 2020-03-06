package net.sayaya.ui.table;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLTableCellElement;
import net.sayaya.ui.IsHTMLElement;

import static org.jboss.gwt.elemento.core.Elements.td;

public class TableCell<T> implements IsHTMLElement<HTMLTableCellElement, TableCell<T>> {
	private final HTMLTableCellElement element;
	private final TableHeaderCell<T> header;
	private final Renderer<T> renderer;
	private HTMLElement content;
	TableCell(TableHeaderCell<T> header, Renderer<T> renderer) {
		this.header = header;
		this.renderer = renderer;
		element = td().element();
		if(header.getColspan()!=null) element.colSpan = header.getColspan();
		if(header.getRowspan()!=null) element.rowSpan = header.getRowspan();
	}
	public TableCell<T> update(Data data) {
		T value = header.getMapper().map(data);
		content = renderer.render(content, value);
		if(content!=null) element.appendChild(content);
		return this;
	}
	@Override
	public HTMLTableCellElement element() {
		return element;
	}
}
