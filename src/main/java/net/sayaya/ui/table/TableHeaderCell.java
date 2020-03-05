package net.sayaya.ui.table;

import com.google.gwt.core.client.Scheduler;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLTableCellElement;
import lombok.AccessLevel;
import lombok.Getter;
import net.sayaya.ui.IsHTMLElement;

import java.util.Objects;

import static org.jboss.gwt.elemento.core.Elements.th;

@Getter
public class TableHeaderCell<V> implements IsHTMLElement<HTMLTableCellElement, TableHeaderCell<V>> {
	private final HTMLTableCellElement element;
	private final TableHeaderRow parent;
	@Getter(AccessLevel.MODULE)
	private final Integer colspan;
	@Getter(AccessLevel.MODULE)
	private final Integer rowspan;
	private final int widthMin;
	private final Integer widthMax;
	private int col;
	private final Mapper<Data, V> mapper;
	private final TableBuilder.TableCellBuilder<V> builder;
	TableHeaderCell(TableHeaderRow parent, Renderer<Void> headerRenderer, TableBuilder.TableCellBuilder<V> builder, Mapper<Data, V> mapper, Integer colspan, Integer rowspan, int widthMin, Integer widthMax) {
		this.parent = parent;
		this.builder = builder;
		this.mapper = mapper;
		this.colspan = colspan;
		this.rowspan = rowspan;
		this.widthMin = widthMin;
		this.widthMax = widthMax;
		element = th().add(headerRenderer.render(null)).element();
		element.addEventListener("DOMNodeInserted", evt->{
			Scheduler.get().scheduleDeferred(()->{
				element.style.setProperty("top", String.valueOf(parent.element().offsetTop) + "px");
			});
		});
		if(colspan != null) element.colSpan = colspan;
		if(rowspan != null) element.rowSpan = rowspan;
	}
	TableCell<V> build() {
		return builder.build(this);
	}
	@Override
	public HTMLTableCellElement element() {
		return element;
	}
}
