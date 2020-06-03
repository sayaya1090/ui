package net.sayaya.ui.table;

import com.google.gwt.core.client.Scheduler;
import elemental2.dom.HTMLTableCellElement;
import lombok.AccessLevel;
import lombok.Getter;
import net.sayaya.ui.HTMLElementBuilder;
import org.jboss.elemento.HtmlContentBuilder;

@Getter
public class TableHeaderCell extends HTMLElementBuilder<HTMLTableCellElement, TableHeaderCell> {
	private final HtmlContentBuilder<HTMLTableCellElement> element;
	private final TableHeaderRow parent;
	@Getter(AccessLevel.MODULE)
	private final Integer colspan;
	@Getter(AccessLevel.MODULE)
	private final Integer rowspan;
	private final int widthMin;
	private final Integer widthMax;
	private int col;
	TableHeaderCell(HtmlContentBuilder<HTMLTableCellElement> e, TableHeaderRow parent, HeaderRenderer renderer, Integer colspan, Integer rowspan, int widthMin, Integer widthMax) {
		super(e);
		this.element = e;
		this.parent = parent;
		this.colspan = colspan;
		this.rowspan = rowspan;
		this.widthMin = widthMin;
		this.widthMax = widthMax;
		renderer.render(element(),0, 0);
		element().addEventListener("DOMNodeInserted", evt->{
			Scheduler.get().scheduleDeferred(()->{
				element().style.setProperty("top", String.valueOf(parent.element().offsetTop) + "px");
			});
		});
		if(colspan != null) element().colSpan = colspan;
		if(rowspan != null) element().rowSpan = rowspan;
	}
	@Override
	public TableHeaderCell that() {
		return this;
	}
}
