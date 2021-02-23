package net.sayaya.ui.sheet;

import net.sayaya.ui.event.HasSelectionChangeHandlers;

import java.util.Arrays;

public interface SheetSelectable extends HasSelectionChangeHandlers<Data[]> {
	Data[] value();
	@Override
	default Data[] selection() {
		return Arrays.stream(value()).filter(d->d.state() == Data.DataState.SELECTED).toArray(Data[]::new);
	}
}
