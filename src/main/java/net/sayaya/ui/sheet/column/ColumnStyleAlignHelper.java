package net.sayaya.ui.sheet.column;

import elemental2.dom.HTMLTableCellElement;

import java.util.function.Supplier;

public final class ColumnStyleAlignHelper<SELF> {
	private final Supplier<SELF> _self;
	private ColumnStyleFn<String> align;
	ColumnStyleAlignHelper(Supplier<SELF> columnBuilder) {
		_self = columnBuilder;
	}
	HTMLTableCellElement apply(HTMLTableCellElement td, int row, String prop, String value) {
		if(align!=null)     td.style.textAlign  = align.apply(td, row, prop, value);
		return td;
	}
	public SELF align(String align) {
		if(align == null) return align((ColumnStyleFn<String>)null);
		return align((td, row, prop, value)->align);
	}
	public SELF align(ColumnStyleFn<String> align) {
		this.align = align;
		return that();
	}
	private SELF that() {
		return _self.get();
	}
}
