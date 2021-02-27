package net.sayaya.ui.sheet.column;

import elemental2.dom.CSSProperties;
import elemental2.dom.HTMLTableCellElement;
import lombok.experimental.Delegate;

import java.util.function.Supplier;

public final class ColumnStyleTextHelper<SELF> {
	private final Supplier<SELF> _self;
	private ColumnStyleFn<String> font;
	private ColumnStyleFn<CSSProperties.FontSizeUnionType> fontSize;
	private ColumnStyleFn<Boolean> bold;
	private ColumnStyleFn<Boolean> italic;
	ColumnStyleTextHelper(Supplier<SELF> columnBuilder) {
		_self = columnBuilder;
	}
	HTMLTableCellElement apply(HTMLTableCellElement td, int row, String prop, String value) {
		if(font!=null)      td.style.fontFamily = font.apply(td, row, prop, value);
		if(fontSize!=null)  td.style.fontSize   = fontSize.apply(td, row, prop, value);
		if(bold!=null)      td.style.fontWeight = bold.apply(td, row, prop, value)?"bold":"normal";
		if(italic!=null)    td.style.fontStyle  = italic.apply(td, row, prop, value)?"italic":"normal";
		return td;
	}
	public SELF font(String font) {
		if(font == null) return font((ColumnStyleFn<String>)null);
		return font((td, row, prop, value)->font);
	}
	public SELF font(ColumnStyleFn<String> font) {
		this.font = font;
		return that();
	}
	public SELF fontSize(CSSProperties.FontSizeUnionType size) {
		if(size == null) return fontSize((ColumnStyleFn<CSSProperties.FontSizeUnionType>)null);
		return fontSize((td, row, prop, value)->size);
	}
	public SELF fontSize(ColumnStyleFn<CSSProperties.FontSizeUnionType> fontSize) {
		this.fontSize = fontSize;
		return that();
	}
	public SELF bold(boolean bold) {
		return bold((td, row, prop, value)->bold);
	}
	public SELF bold(ColumnStyleFn<Boolean> bold) {
		this.bold = bold;
		return that();
	}
	public SELF italic(boolean italic) {
		return italic((td, row, prop, value)->italic);
	}
	public SELF italic(ColumnStyleFn<Boolean> italic) {
		this.italic = italic;
		return that();
	}
	private SELF that() {
		return _self.get();
	}
}
