package net.sayaya.ui.table;

import elemental2.dom.HTMLTableSectionElement;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import net.sayaya.ui.IsHTMLElement;

import java.util.ArrayList;

import static org.jboss.gwt.elemento.core.Elements.thead;

@Getter
public class TableHeader implements IsHTMLElement<HTMLTableSectionElement, TableHeader> {
	private final int numOfCellsMaxShown;
	private final int fixedColumns;
	private final HTMLTableSectionElement element = thead().element();
	@Getter(AccessLevel.NONE)
	private final ArrayList<TableHeaderRow> rows = new ArrayList<>();
	@Getter(AccessLevel.NONE)
	private final Table<?> parent;
	TableHeader(Table<?> parent, int numOfCellsMaxShown, int fixedColumns) {
		this.parent = parent;
		this.numOfCellsMaxShown = numOfCellsMaxShown;
		this.fixedColumns = fixedColumns;
	}
	void add(TableHeaderRow row) {
		rows.add(row);
		element().appendChild(row.element());
	}
	int getRowCount() {
		return rows.size();
	}
	TableHeaderRow getRow(int row) {
		return rows.get(row);
	}
	@Override
	public HTMLTableSectionElement element() {
		return element;
	}
}
