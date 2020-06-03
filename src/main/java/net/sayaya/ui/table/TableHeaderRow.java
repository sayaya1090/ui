package net.sayaya.ui.table;

import elemental2.dom.HTMLTableRowElement;
import lombok.AccessLevel;
import lombok.Getter;
import net.sayaya.ui.HTMLElementBuilder;
import org.jboss.elemento.HtmlContentBuilder;

import java.util.ArrayList;
import java.util.stream.Stream;

@Getter
public class TableHeaderRow extends HTMLElementBuilder<HTMLTableRowElement, TableHeaderRow> {
	private final HtmlContentBuilder<HTMLTableRowElement> element;
	@Getter(AccessLevel.NONE)
	private final TableHeader parent;
	private final int rowHeightMin;
	private final Integer rowHeightMax;
	@Getter(AccessLevel.NONE)
	private final ArrayList<TableHeaderCell> cells = new ArrayList<>();
	TableHeaderRow(HtmlContentBuilder<HTMLTableRowElement> e, TableHeader parent, int rowHeightMin, Integer rowHeightMax) {
		super(e);
		this.element = e;
		this.parent = parent;
		this.rowHeightMin = rowHeightMin;
		this.rowHeightMax = rowHeightMax;
	}
	public TableHeaderRow add(TableHeaderCell cell) {
		cells.add(cell);
		element().appendChild(cell.element());
		return this;
	}
	Stream<TableHeaderCell> forEachColumn() {
		return cells.stream();
	}
	@Override
	public TableHeaderRow that() {
		return this;
	}
}
