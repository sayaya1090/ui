package net.sayaya.ui.table;

import com.google.gwt.core.client.Scheduler;
import elemental2.dom.HTMLTableCellElement;
import lombok.AccessLevel;
import lombok.Getter;
import net.sayaya.ui.IsHTMLElement;

import static org.jboss.gwt.elemento.core.Elements.th;

@Getter
public class TableHeaderCell implements IsHTMLElement<HTMLTableCellElement, TableHeaderCell> {
	private final HTMLTableCellElement element;
	private final TableHeaderRow parent;
	@Getter(AccessLevel.MODULE)
	private final Integer colspan;
	@Getter(AccessLevel.MODULE)
	private final Integer rowspan;
	private final int widthMin;
	private final Integer widthMax;
	private int col;
	TableHeaderCell(TableHeaderRow parent, HeaderRenderer renderer, Integer colspan, Integer rowspan, int widthMin, Integer widthMax) {
		this.parent = parent;
		this.colspan = colspan;
		this.rowspan = rowspan;
		this.widthMin = widthMin;
		this.widthMax = widthMax;
		element = th().element();
		renderer.render(element,0, 0);
		element.addEventListener("DOMNodeInserted", evt->{
			Scheduler.get().scheduleDeferred(()->{
				element.style.setProperty("top", String.valueOf(parent.element().offsetTop) + "px");
			});
		});
		if(colspan != null) element.colSpan = colspan;
		if(rowspan != null) element.rowSpan = rowspan;
	}
	@Override
	public HTMLTableCellElement element() {
		return element;
	}
}
