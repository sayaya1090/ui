package net.sayaya.ui.table;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLTableSectionElement;
import lombok.AccessLevel;
import lombok.Getter;
import net.sayaya.ui.IsHTMLElement;

import java.util.LinkedList;

import static org.jboss.gwt.elemento.core.Elements.tbody;

public class TableBody implements IsHTMLElement<HTMLTableSectionElement, TableBody> {
	private final HTMLTableSectionElement element = tbody().element();
	private final TableBuilder.ContextBodyBuilder contextBuilder;
	@Getter(AccessLevel.NONE)
	private final TableHeader header;
	private final LinkedList<TableBodyRow> rows = new LinkedList<>();
	private Data[] values;
	private int cursorMin = 0;
	private int cursorMax = 0;
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
		DomGlobal.console.log("Update Body");
		for(int i = 0; i < Math.min(values.length, rows.size()/header.getRowCount()); ++i) {
			for(int j = 0; j < header.getRowCount(); ++j) {
				rows.get(i*header.getRowCount() + j).update(values[i]);
			}
		}
		cursorMin = 0;
		cursorMax = Math.min(values.length, rows.size()/header.getRowCount());
	}
	int bufferSize() {
		return rows.size();
	}
	double increase(int n) {
		double height = 0;
		int i = 0;
		for(; i < n; ++i) {
			if(cursorMax + i >= values.length) break;
			Data datum = values[cursorMax + i];
			for(int j = 0; j < header.getRowCount(); ++j) {
				TableBodyRow row = rows.removeFirst();
				height += row.element().offsetHeight;
				rows.addLast(row);
				element.appendChild(row.update(datum).element());
			}
		}
		cursorMin += i;
		cursorMax += i;
		return height;
	}
	double decrease(int n) {
		double height = n;
		int i = 0;
		for(; i < n; ++i) {
			if(cursorMin - i < 0) break;
			Data datum = values[cursorMin - i];
			for(int j = 0; j < header.getRowCount(); ++j) {
				TableBodyRow row = rows.removeLast();
				height += row.element().offsetHeight;
				rows.addFirst(row);
				element.insertBefore(row.update(datum).element(), element.firstChild);
			}
		}
		cursorMin -= i;
		cursorMax -= i;
		return height;
	}
	@Override
	public HTMLTableSectionElement element() {
		return element;
	}
}
