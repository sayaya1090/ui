package net.sayaya.ui;

public interface Focusable<W extends Focusable<W>> extends FluentInterface<W> {
	W setAccessKey(char key);
	W setFocus();
}
