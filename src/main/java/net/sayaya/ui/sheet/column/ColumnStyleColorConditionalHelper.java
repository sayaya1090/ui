package net.sayaya.ui.sheet.column;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import elemental2.dom.HTMLTableCellElement;

import java.util.function.Supplier;

public final class ColumnStyleColorConditionalHelper<SELF> {
	private ColumnStyleFn<String> pattern;
	private ColumnStyleFn<String> colorConditional;
	private ColumnStyleFn<String> colorConditionalBackground;
	private final Supplier<SELF> _self;
	ColumnStyleColorConditionalHelper(Supplier<SELF> columnBuilder) {
		_self = columnBuilder;
	}
	HTMLTableCellElement apply(HTMLTableCellElement td, int row, String prop, String value) {
		if(value == null) return td;
		if(pattern == null) return td;
		String pattern = this.pattern.apply(td, row, prop, value);
		if(pattern == null || pattern.trim().isEmpty()) return td;

		RegExp regexp = RegExp.compile(pattern.trim());
		MatchResult m = regexp.exec(value.trim());
		if(m!=null) {
			if(colorConditional !=null)             td.style.color              = colorConditional.apply(td, row, prop, value);
			if(colorConditionalBackground !=null)   td.style.backgroundColor    = colorConditionalBackground.apply(td, row, prop, value);
		}
		return td;
	}
	public SELF pattern(String pattern) {
		if(pattern == null) return pattern((ColumnStyleFn<String>)null);
		return pattern((td, row, prop, value)->pattern);
	}
	public SELF pattern(ColumnStyleFn<String> pattern) {
		this.pattern = pattern;
		return that();
	}
	public SELF colorConditional(String color) {
		if(color == null) return colorConditional((ColumnStyleFn<String>)null);
		return colorConditional((td, row, prop, value)->color);
	}
	public SELF colorConditional(ColumnStyleFn<String> color) {
		this.colorConditional = color;
		return that();
	}
	public SELF colorConditionalBackground(String colorBackground) {
		if(colorBackground == null) return colorConditionalBackground((ColumnStyleFn<String>)null);
		return colorConditionalBackground((td, row, prop, value)->colorBackground);
	}
	public SELF colorConditionalBackground(ColumnStyleFn<String> colorBackground) {
		this.colorConditionalBackground = colorBackground;
		return that();
	}
	private SELF that() {
		return _self.get();
	}
}
