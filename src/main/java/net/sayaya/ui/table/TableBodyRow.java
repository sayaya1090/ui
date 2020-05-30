package net.sayaya.ui.table;

import elemental2.dom.HTMLTableRowElement;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import net.sayaya.ui.IsHTMLElement;

import static org.jboss.elemento.Elements.tr;

@Getter
@Accessors(fluent = true)
public final class TableBodyRow implements IsHTMLElement<HTMLTableRowElement, TableBodyRow> {
	@Getter(AccessLevel.NONE)
	private final int rowHeightMin;
	@Getter(AccessLevel.NONE)
	private final Integer rowHeightMax;
	@Getter(AccessLevel.NONE)
	private final HTMLTableRowElement element = tr().element();
	@Getter(AccessLevel.PACKAGE)
	private RowRenderer renderer;
	@Getter(AccessLevel.PACKAGE)
	private Data data;
	@Getter(AccessLevel.PACKAGE)
	private int dataIdx;
	public TableBodyRow(int rowHeightMin, Integer rowHeightMax) {
		this.rowHeightMin = rowHeightMin;
		this.rowHeightMax = rowHeightMax;
	}
	public RowRenderer.SiblingRowRenderers update(TableBody.BodyCursor cursor) {
		this.renderer = cursor.renderer();
		this.data = cursor.data();
		this.dataIdx = cursor.dataIdx();
		return cursor.renderer().render(element, cursor.dataIdx(), cursor.data());
	}
	@Override
	public HTMLTableRowElement element() {
		return element;
	}
}
