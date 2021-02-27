package net.sayaya.ui.sheet.column;

import elemental2.dom.CSSProperties;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import net.sayaya.ui.sheet.Column;

import java.util.function.Supplier;

import static org.jboss.elemento.Elements.span;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class ColumnString implements ColumnBuilder {
	private final String id;
	private final ColumnBuilderDefaultHelper<ColumnString> defaultHelper = new ColumnBuilderDefaultHelper<>(()->this);
	private final ColumnStyleTextHelper<ColumnString> textHelper = new ColumnStyleTextHelper<>(()->this);
	private final ColumnStyleDataChangeHelper<ColumnString> dataChangeHelper = new ColumnStyleDataChangeHelper<>(()->this);
	private final ColumnStyleColorHelper<ColumnString> colorHelper = new ColumnStyleColorHelper<>(()->this);
	private final ColumnStyleColorConditionalHelper<ColumnString> colorConditionalHelper = new ColumnStyleColorConditionalHelper<>(()->this);
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

	public ColumnString name(String name) {return this.defaultHelper.name(name);}

	public ColumnString width(Integer width) {return this.defaultHelper.width(width);}

	public ColumnString width(Supplier<Integer> width) {return this.defaultHelper.width(width);}

	public ColumnString readOnly(Boolean readOnly) {return this.defaultHelper.readOnly(readOnly);}

	public ColumnString readOnly(Supplier<Boolean> readOnly) {return this.defaultHelper.readOnly(readOnly);}

	public ColumnString font(String font) {return this.textHelper.font(font);}

	public ColumnString font(ColumnStyleFn<String> font) {return this.textHelper.font(font);}

	public ColumnString fontSize(CSSProperties.FontSizeUnionType size) {return this.textHelper.fontSize(size);}

	public ColumnString fontSize(ColumnStyleFn<CSSProperties.FontSizeUnionType> fontSize) {return this.textHelper.fontSize(fontSize);}

	public ColumnString bold(boolean bold) {return this.textHelper.bold(bold);}

	public ColumnString bold(ColumnStyleFn<Boolean> bold) {return this.textHelper.bold(bold);}

	public ColumnString italic(boolean italic) {return this.textHelper.italic(italic);}

	public ColumnString italic(ColumnStyleFn<Boolean> italic) {return this.textHelper.italic(italic);}

	public ColumnString color(String color) {return this.colorHelper.color(color);}

	public ColumnString color(ColumnStyleFn<String> color) {return this.colorHelper.color(color);}

	public ColumnString colorBackground(String colorBackground) {return this.colorHelper.colorBackground(colorBackground);}

	public ColumnString colorBackground(ColumnStyleFn<String> colorBackground) {return this.colorHelper.colorBackground(colorBackground);}

	public ColumnString pattern(String pattern) {return this.colorConditionalHelper.pattern(pattern);}

	public ColumnString pattern(ColumnStyleFn<String> pattern) {return this.colorConditionalHelper.pattern(pattern);}

	public ColumnString colorConditional(String color) {return this.colorConditionalHelper.colorConditional(color);}

	public ColumnString colorConditional(ColumnStyleFn<String> color) {return this.colorConditionalHelper.colorConditional(color);}

	public ColumnString colorConditionalBackground(String colorBackground) {return this.colorConditionalHelper.colorConditionalBackground(colorBackground);}

	public ColumnString colorConditionalBackground(ColumnStyleFn<String> colorBackground) {return this.colorConditionalHelper.colorConditionalBackground(colorBackground);}

	public ColumnString align(String align) {return this.alignHelper.align(align);}

	public ColumnString align(ColumnStyleFn<String> align) {return this.alignHelper.align(align);}
}
