package net.sayaya.ui.sheet.column;

import elemental2.dom.HTMLTableCellElement;

import java.util.function.Supplier;

public final class ColumnStyleColorHelper<SELF> {
	private final Supplier<SELF> _self;
	private ColumnStyleFn<String> color;
	private ColumnStyleFn<String> colorBackground;
	ColumnStyleColorHelper(Supplier<SELF> columnBuilder) {
		_self = columnBuilder;
	}
	HTMLTableCellElement apply(HTMLTableCellElement td, int row, String prop, String value) {
		if(color!=null)             td.style.color              = color.apply(td, row, prop, value);
		if(colorBackground!=null)   td.style.backgroundColor    = colorBackground.apply(td, row, prop, value);
		return td;
	}
	public SELF color(String color) {
		if(color == null) return color((ColumnStyleFn<String>)null);
		return color((td, row, prop, value)->color);
	}
	public SELF color(ColumnStyleFn<String> color) {
		this.color = color;
		return that();
	}
	public SELF colorBackground(String colorBackground) {
		if(colorBackground == null) return colorBackground((ColumnStyleFn<String>)null);
		return colorBackground((td, row, prop, value)->colorBackground);
	}
	public SELF colorBackground(ColumnStyleFn<String> colorBackground) {
		this.colorBackground = colorBackground;
		return that();
	}
	private SELF that() {
		return _self.get();
	}
}
