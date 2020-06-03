package net.sayaya.ui.table;

import elemental2.dom.HTMLTableRowElement;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import net.sayaya.ui.HTMLElementBuilder;
import org.jboss.elemento.HtmlContentBuilder;

@Getter
@Accessors(fluent = true)
public final class TableBodyRow extends HTMLElementBuilder<HTMLTableRowElement, TableBodyRow> {
	@Getter(AccessLevel.NONE)
	private final int rowHeightMin;
	@Getter(AccessLevel.NONE)
	private final Integer rowHeightMax;
	@Getter(AccessLevel.NONE)
	private final HtmlContentBuilder<HTMLTableRowElement> element;
	@Getter(AccessLevel.PACKAGE)
	private RowRenderer renderer;
	@Getter(AccessLevel.PACKAGE)
	private Data data;
	@Getter(AccessLevel.PACKAGE)
	private int dataIdx;
	public TableBodyRow(HtmlContentBuilder<HTMLTableRowElement> e, int rowHeightMin, Integer rowHeightMax) {
		super(e);
		this.element = e;
		this.rowHeightMin = rowHeightMin;
		this.rowHeightMax = rowHeightMax;
	}
	public RowRenderer.SiblingRowRenderers update(TableBody.BodyCursor cursor) {
		this.renderer = cursor.renderer();
		this.data = cursor.data();
		this.dataIdx = cursor.dataIdx();
		return cursor.renderer().render(element(), cursor.dataIdx(), cursor.data());
	}

	@Override
	public TableBodyRow that() {
		return this;
	}
}
