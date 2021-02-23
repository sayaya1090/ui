package net.sayaya.ui.sheet.function;

import elemental2.dom.Event;
import elemental2.dom.HTMLTableCellElement;
import jsinterop.annotations.*;

@JsType(isNative = true)
public interface CellEditor {
	void prepare(int row, int col, String prop, HTMLTableCellElement td, String value, Object cell);
	boolean isOpened();
	boolean isWaiting();
	void beginEditing(String newInitValue, Event evt);
	void finishEditing(boolean isRestore, boolean ctrlDown, Consumer<Boolean> callback);
	void focus();
	void cancelChanges();
	void enableFullEditMode();
	boolean isInFullEditMode();
}
