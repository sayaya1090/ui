package net.sayaya.ui.table;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLTableRowElement;
import lombok.AccessLevel;
import lombok.Getter;
import net.sayaya.ui.IsHTMLElement;

import java.util.ArrayList;

import static org.jboss.gwt.elemento.core.Elements.tr;

public class TableBodyRow implements IsHTMLElement<HTMLTableRowElement, TableBodyRow> {
	private final HTMLTableRowElement element = tr().element();
	private final int rowHeightMin;
	private final Integer rowHeightMax;
	private final TableHeaderRow header;
	@Getter(AccessLevel.NONE)
	private final ArrayList<TableCell<?>> cells = new ArrayList<>();
	TableBodyRow(TableHeaderRow header) {
		this.header = header;
		this.rowHeightMin = header.getRowHeightMin();
		this.rowHeightMax = header.getRowHeightMax();
		header.forEachColumn()
			  .map(h->h.build())
			  .forEach(cell->{
				  element.appendChild(cell.element());
				  cells.add(cell);
			  });
	}
	public TableBodyRow update(Data data) {
		for(int i = 0; i < cells.size(); ++i) cells.get(i).update(data);
		return this;
	}
	@Override
	public HTMLTableRowElement element() {
		return element;
	}
}
