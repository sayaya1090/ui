package net.sayaya.ui;

public interface Focusable<W extends Focusable<W>> extends FluentInterface<W> {
	W accessKey(char key);
	W focus();
}
