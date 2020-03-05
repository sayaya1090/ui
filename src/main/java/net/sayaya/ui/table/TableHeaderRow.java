package net.sayaya.ui.table;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLTableRowElement;
import lombok.AccessLevel;
import lombok.Getter;
import net.sayaya.ui.IsHTMLElement;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.jboss.gwt.elemento.core.Elements.tr;

@Getter
public class TableHeaderRow implements IsHTMLElement<HTMLTableRowElement, TableHeaderRow> {
	private final HTMLTableRowElement element = tr().element();
	@Getter(AccessLevel.NONE)
	private final TableHeader parent;
	private final int rowHeightMin;
	private final Integer rowHeightMax;
	@Getter(AccessLevel.NONE)
	private final ArrayList<TableHeaderCell<?>> cells = new ArrayList<>();
	TableHeaderRow(TableHeader parent, int rowHeightMin, Integer rowHeightMax) {
		this.parent = parent;
		this.rowHeightMin = rowHeightMin;
		this.rowHeightMax = rowHeightMax;
	}
	public TableHeaderRow add(TableHeaderCell<?> cell) {
		cells.add(cell);
		element.appendChild(cell.element());
		return this;
	}
	Stream<TableHeaderCell<?>> forEachColumn() {
		return cells.stream();
	}
	@Override
	public HTMLTableRowElement element() {
		return element;
	}
}
