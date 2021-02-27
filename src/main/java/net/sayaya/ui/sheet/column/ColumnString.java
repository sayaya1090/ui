package net.sayaya.ui.sheet.column;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import net.sayaya.ui.sheet.Column;

import static org.jboss.elemento.Elements.span;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class ColumnString implements ColumnBuilder {
	private final String id;
	@Delegate
	private final ColumnBuilderDefaultHelper<ColumnString> defaultHelper = new ColumnBuilderDefaultHelper<>(()->this);
	@Delegate
	private final ColumnStyleTextHelper<ColumnString> textHelper = new ColumnStyleTextHelper<>(()->this);
	@Delegate
	private final ColumnStyleDataChangeHelper<ColumnString> dataChangeHelper = new ColumnStyleDataChangeHelper<>(()->this);
	@Delegate
	private final ColumnStyleColorHelper<ColumnString> colorHelper = new ColumnStyleColorHelper<>(()->this);
	@Delegate
	private final ColumnStyleColorConditionalHelper<ColumnString> colorConditionalHelper = new ColumnStyleColorConditionalHelper<>(()->this);
	@Delegate
	private final ColumnStyleAlignHelper<ColumnString> alignHelper = new ColumnStyleAlignHelper<>(()->this);
	@Override
	public Column build() {
		Column column = defaultHelper.build().data(id);
		return column.renderer((sheet, td, row, col, prop, value, ci)->{
			textHelper.apply(td, row, prop, value);
			colorHelper.apply(td, row, prop, value);
			dataChangeHelper.apply(sheet, td, row, prop);
			colorConditionalHelper.apply(td, row, prop, value);
			alignHelper.apply(td, row, prop, value);
			td.innerHTML = value;
			return td;
		}).headerRenderer(n->span().textContent(defaultHelper.name()).element());
	}
}
