package net.sayaya.ui.table;

import elemental2.dom.HTMLTableSectionElement;
import lombok.AccessLevel;
import lombok.Getter;
import net.sayaya.ui.HTMLElementBuilder;
import org.jboss.elemento.HtmlContentBuilder;

import java.util.ArrayList;

@Getter
public class TableHeader extends HTMLElementBuilder<HTMLTableSectionElement, TableHeader> {
	private final int numOfCellsMaxShown;
	private final int fixedColumns;
	private final HtmlContentBuilder<HTMLTableSectionElement> element;
	@Getter(AccessLevel.NONE)
	private final ArrayList<TableHeaderRow> rows = new ArrayList<>();
	@Getter(AccessLevel.NONE)
	private final Table<?> parent;
	TableHeader(HtmlContentBuilder<HTMLTableSectionElement> e, Table<?> parent, int numOfCellsMaxShown, int fixedColumns) {
		super(e);
		this.element = e;
		this.parent = parent;
		this.numOfCellsMaxShown = numOfCellsMaxShown;
		this.fixedColumns = fixedColumns;
	}
	void add(TableHeaderRow row) {
		rows.add(row);
		element().appendChild(row.element());
	}
	int rowCount() {
		return rows.size();
	}
	TableHeaderRow row(int row) {
		return rows.get(row);
	}

	@Override
	public TableHeader that() {
		return this;
	}
}
