package net.sayaya.ui.table;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLTableSectionElement;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.sayaya.ui.IsHTMLElement;

import java.util.LinkedList;

import static org.jboss.gwt.elemento.core.Elements.tbody;

public class TableBody implements IsHTMLElement<HTMLTableSectionElement, TableBody> {
	private final HTMLTableSectionElement element = tbody().element();
	private final TableBuilder.ContextBodyBuilder contextBuilder;
	@Getter(AccessLevel.NONE)
	private final TableHeader header;
	private final LinkedList<TableBodyRow> buffer = new LinkedList<>();
	private Data[] values;
	private final BodyCursor head = new BodyCursor();
	private final BodyCursor tail = new BodyCursor();
	private final RowRenderer renderer;
	TableBody(TableHeader header, RowRenderer renderer, TableBuilder.ContextBodyBuilder contextBuilder) {
		this.header = header;
		this.renderer = renderer;
		this.contextBuilder = contextBuilder;
	}
	void add(TableBodyRow row) {
		buffer.add(row);
		element().appendChild(row.element());
	}
	TableBody values(Data[] values) {
		this.values = values;
		return this;
	}
	void set(int idx) {
		head.data = tail.data = values[idx];
		head.renderer = tail.renderer = renderer;
		head.dataIdx = tail.dataIdx = idx;
		for(TableBodyRow row: buffer) {
			tail.renderer = row.update(tail).forward();
			if(tail.renderer == null) {
				tail.data = values[++tail.dataIdx];
				tail.renderer = renderer;
			}
		}
		DomGlobal.console.log("Tail:" + tail.dataIdx + ", " + tail.data + ", " + tail.renderer);
	}
	int bufferSize() {
		return buffer.size();
	}
	private boolean isTop() {
		return head.dataIdx == 0 && head.renderer == renderer;
	}
	private boolean isBottom() {
		return tail.dataIdx >= (values.length-1) && tail.renderer == null;
	}
	private void cursor(TableBodyRow row, BodyCursor cursor) {
		cursor.renderer = row.renderer();
		cursor.data = row.data();
		cursor.dataIdx = row.dataIdx();
	}
	double increase() {
		if(isBottom()) return 0;
		TableBodyRow row = buffer.removeFirst();
		element.appendChild(row.element());
		double move = row.element().offsetHeight;
		buffer.addLast(row);
		tail.renderer = row.update(tail).forward();
		if(tail.renderer == null) {
			tail.data = values[++tail.dataIdx];
			tail.renderer = renderer;
		}
		TableBodyRow top = buffer.getFirst();
		cursor(top, head);
		return move;
	}
	double decrease() {
		if(isTop()) return 0;
		TableBodyRow row = buffer.removeLast();
		element.insertBefore(element.lastChild, element.firstChild);
		double move = row.element().offsetHeight;
		buffer.addFirst(row);
		RowRenderer tmp = head.renderer;
		head.renderer = row.update(head).backward();
		if(tmp == renderer) head.data = values[--head.dataIdx];
		TableBodyRow bottom = buffer.getLast();
		cursor(bottom, tail);
		return move;
	}

	@Override
	public HTMLTableSectionElement element() {
		return element;
	}

	@Getter
	@Accessors(fluent=true)
	@ToString
	static class BodyCursor {
		private Data data;
		private RowRenderer renderer;
		private int dataIdx;
		private BodyCursor(){}
	}
}
