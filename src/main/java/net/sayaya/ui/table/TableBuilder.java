package net.sayaya.ui.table;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLInputElement;
import elemental2.dom.HTMLLabelElement;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jboss.gwt.elemento.core.Elements;
import org.jboss.gwt.elemento.core.InputType;

import java.util.LinkedList;

public class TableBuilder<V> {
	private TableHeaderBuilder header;
	private TableBodyBuilder body;
	private Mapper<V, Data> mapper;
	private TableBuilder(){}
	public static TableBuilder<?> table() {
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
		private Renderer<Void> headerRenderer = (e, n)-> {
			HTMLLabelElement elem = null;
			if(e == null) elem = Elements.label().element();
			else elem = (HTMLLabelElement)e;
			elem.textContent = id;
			return elem;
		};
		private TableCellBuilder<V> builder = new TableCellBuilder<>();
		private Mapper<Data, V> mapper = data->(V)data.get(id, Object.class);
		private TableHeaderCellBuilder(String id) {
			this.id = id;
		}
		public static TableHeaderCellBuilder<?> cell(String id) {
			return new TableHeaderCellBuilder<>(id);
		}
		public <T> TableHeaderCellBuilder<T> builder(Mapper<Data, T> mapper, Renderer<T> renderer) {
			TableHeaderCellBuilder<T> builder = new TableHeaderCellBuilder<>(id);
			builder.colspan = colspan;
			builder.rowspan = rowspan;
			builder.widthMin = widthMin;
			builder.widthMax = widthMax;
			builder.headerRenderer = headerRenderer;
			builder.mapper = mapper;
			builder.builder.renderer = renderer;
			return builder;
		}
		private TableHeaderCell<V> build(TableHeaderRow parent) {
			return new TableHeaderCell<>(parent, headerRenderer, builder, mapper, colspan, rowspan, widthMin, widthMax);
		}
	}
	public static class TableCellBuilder<V> {
		private Renderer<V> renderer = (e, v)->{
			HTMLLabelElement elem = null;
			if(e == null) elem = Elements.label().element();
			else elem = (HTMLLabelElement) e;
			elem.innerHTML = v!=null?String.valueOf(v):"";
			return elem;
		};
		public TableCell<V> build(TableHeaderCell<V> column) {
			return new TableCell<>(column, renderer);
		}
	}
	@Setter
	@Accessors(fluent = true)
	public static class TableBodyBuilder {
		private int numOfRowsPrepared = 100;
		private ContextBodyBuilder contextBuilder;
		private TableBodyBuilder() {}
		public static TableBodyBuilder body() {
			return new TableBodyBuilder();
		}
		private TableBody build(TableHeader header) {
			TableBody elem = new TableBody(header, contextBuilder);
			for(int i = 0; i < numOfRowsPrepared; ++i) {
				for(int j = 0; j < header.getRowCount(); ++j) {
					TableHeaderRow template = header.getRow(j);
					elem.add(new TableBodyRow(template));
				}
			}
			return elem;
		}
	}
	public static class ContextBodyBuilder {

	}
}
