package net.sayaya.ui.table;

import elemental2.dom.HTMLLabelElement;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jboss.gwt.elemento.core.Elements;

import java.util.LinkedList;

public class TableBuilder<V> {
	private TableHeaderBuilder header;
	private TableBodyBuilder body;
	private Mapper<V, Data> mapper;
	private TableBuilder(){}
	public static TableBuilder<?> table(){
		return new TableBuilder<>();
	}
	public <T> TableBuilder<T> map(Mapper<T, Data> mapper) {
		TableBuilder<T> builder = new TableBuilder<>();
		builder.mapper = mapper;
		builder.header = header;
		builder.body = body;
		return builder;
	}
	public TableBuilder<V> set(TableHeaderBuilder child) {
		this.header = child;
		return this;
	}
	public TableBuilder<V> set(TableBodyBuilder child) {
		this.body = child;
		return this;
	}
	public Table<V> build() {
		Table<V> elem = new Table<>(mapper);
		if(header!=null) {
			TableHeader head = header.build(elem);
			elem.set(head);
			if(body!=null) {
				TableBody child = body.build(head);
				elem.set(child);
			}
		}
		return elem;
	}
	@Setter
	@Accessors(fluent = true)
	public static class TableHeaderBuilder {
		private int numOfColumnsPrepared = 20;
		private int fixedColumns = 0;
		private final LinkedList<TableHeaderRowBuilder> children = new LinkedList<>();
		private TableHeaderBuilder() {}
		public static TableHeaderBuilder header() {
			return new TableHeaderBuilder();
		}
		public TableHeaderBuilder add(TableHeaderRowBuilder child) {
			children.add(child);
			return this;
		}
		private TableHeader build(Table<?> parent) {
			TableHeader elem = new TableHeader(parent, numOfColumnsPrepared, fixedColumns);
			for(TableHeaderRowBuilder child: children) elem.add(child.build(elem));
			return elem;
		}
	}
	@Setter
	@Accessors(fluent = true)
	public static class TableHeaderRowBuilder {
		private int rowHeightMin = 0;
		private Integer rowHeightMax = null;
		private TableHeaderRowBuilder() {}
		private final LinkedList<TableHeaderCellBuilder<?>> children = new LinkedList<>();
		public static TableHeaderRowBuilder row() {
			return new TableHeaderRowBuilder();
		}
		public TableHeaderRowBuilder add(TableHeaderCellBuilder<?> child) {
			children.add(child);
			return this;
		}
		private TableHeaderRow build(TableHeader parent) {
			TableHeaderRow elem = new TableHeaderRow(parent, rowHeightMin, rowHeightMax);
			for(TableHeaderCellBuilder<?> child: children) elem.add(child.build(elem));
			return elem;
		}
	}

	@Setter
	@Accessors(fluent = true)
	public static class TableHeaderCellBuilder<V> {
		private String id;
		private Integer colspan;
		private Integer rowspan;
		private int widthMin = 0;
		private Integer widthMax = null;
		private HeaderRenderer headerRenderer = (e, i, c)-> {
			if(e.firstElementChild == null) e.appendChild(Elements.label().element());
			HTMLLabelElement elem = (HTMLLabelElement)e.firstElementChild;
			elem.textContent = id;
		};
		private TableCellBuilder<V> builder = new TableCellBuilder<>();
		private TableHeaderCellBuilder(String id) {
			this.id = id;
		}
		public static TableHeaderCellBuilder<?> cell(String id) {
			return new TableHeaderCellBuilder<>(id);
		}
		public <T> TableHeaderCellBuilder<T> builder(CellRenderer<T> renderer) {
			TableHeaderCellBuilder<T> builder = new TableHeaderCellBuilder<>(id);
			builder.colspan = colspan;
			builder.rowspan = rowspan;
			builder.widthMin = widthMin;
			builder.widthMax = widthMax;
			builder.headerRenderer = headerRenderer;
			builder.builder.renderer = renderer;
			return builder;
		}
		private TableHeaderCell build(TableHeaderRow parent) {
			return new TableHeaderCell(parent, headerRenderer, colspan, rowspan, widthMin, widthMax);
		}
	}
	public static class TableCellBuilder<V> {
		private CellRenderer<V> renderer = (e, i, c, d)-> {
			if(e.firstElementChild == null) e.appendChild(Elements.label().element());
			HTMLLabelElement elem = (HTMLLabelElement)e.firstElementChild;
			elem.textContent = d!=null?String.valueOf(d):"";
		};
		public TableCell<V> build() {
			return new TableCell<>(renderer);
		}
	}
	@Setter
	@Accessors(fluent = true)
	public static class TableBodyBuilder {
		private int numOfRowsPrepared = 100;
		private ContextBodyBuilder contextBuilder;
		private RowRenderer renderer;
		private TableBodyBuilder() {}
		public static TableBodyBuilder body() {
			return new TableBodyBuilder();
		}
		private TableBody build(TableHeader header) {
			TableBody elem = new TableBody(header, renderer, contextBuilder);
			for(int i = 0; i < numOfRowsPrepared; ++i) elem.add(new TableBodyRow(0, 0));
			return elem;
		}
	}
	public static class ContextBodyBuilder {

	}
}
