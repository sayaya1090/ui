package net.sayaya.ui.sheet.column;

import net.sayaya.ui.sheet.Column;

@FunctionalInterface
public interface ColumnBuilder {
	Column build();

	static ColumnString string(String id) {
		return new ColumnString(id);
	}
	static ColumnCheckBox checkbox(String id) {
		return new ColumnCheckBox(id);
	}
}
