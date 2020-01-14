package net.sayaya.ui.handler;

public interface Focusable<W extends Focusable<W>> extends FluentInterface<W> {
	int getTabIndex();
	W setAccessKey(char key);
	W setFocus();
	W setTabIndex(int index);
}
