package net.sayaya.ui.table;

import elemental2.dom.HTMLTableSectionElement;
import lombok.AccessLevel;
import lombok.Getter;
import net.sayaya.ui.IsHTMLElement;

import java.util.ArrayList;

import static org.jboss.gwt.elemento.core.Elements.tbody;

public class TableBody implements IsHTMLElement<HTMLTableSectionElement, TableBody> {
	private final HTMLTableSectionElement element = tbody().element();
	private final TableBuilder.ContextBodyBuilder contextBuilder;
	@Getter(AccessLevel.NONE)
	private final TableHeader header;
	private final ArrayList<TableBodyRow> rows = new ArrayList<>();
	private Data[] values;
	TableBody(TableHeader header, TableBuilder.ContextBodyBuilder contextBuilder) {
		this.header = header;
		this.contextBuilder = contextBuilder;
	}
	void add(TableBodyRow row) {
		rows.add(row);
		element().appendChild(row.element());
	}
	void update(Data[] values) {
		this.values = values;
		update(0);
	}
	void update(int idx) {
		for(int i = 0; i < Math.min(values.length, rows.size()/header.getRowCount()); ++i) {
			for(int j = 0; j < header.getRowCount(); ++j) {
				rows.get(i*header.getRowCount() + j).update(values[idx + i]);
			}
		}
	}
	int rowCount() {
		return rows.size();
	}
	@Override
	public HTMLTableSectionElement element() {
		return element;
	}
}
