package net.sayaya.ui.sheet.column;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import net.sayaya.ui.sheet.Column;

import java.util.function.Supplier;

@Getter(AccessLevel.PACKAGE)
@Accessors(fluent = true)
public final class ColumnBuilderDefaultHelper<SELF> {
	private final Supplier<SELF> _self;
	private String name;
	private Supplier<Integer> width;
	private Supplier<Boolean> readOnly;
	ColumnBuilderDefaultHelper(Supplier<SELF> columnBuilder) {
		_self = columnBuilder;
	}
	public SELF name(String name) {
		this.name = name;
		return that();
	}
	public SELF width(Integer width) {
		if(width == null) return width((Supplier<Integer>)null);
		else return width(()->width);
	}
	public SELF width(Supplier<Integer> width) {
		this.width = width;
		return that();
	}
	public SELF readOnly(Boolean readOnly) {
		if(readOnly == null) return readOnly((Supplier<Boolean>)null);
		else return readOnly(()->readOnly);
	}
	public SELF readOnly(Supplier<Boolean> readOnly) {
		this.readOnly = readOnly;
		return that();
	}
	Integer width() {
		if(width==null) return null;
		return width();
	}
	boolean readOnly() {
		if(readOnly == null) return false;
		return readOnly();
	}
	Column build() {
		return Column.defaults().header(name).width(width!=null?width.get():null).readOnly(readOnly!=null?readOnly.get():false);
	}
	private SELF that() {
		return _self.get();
	}
}
