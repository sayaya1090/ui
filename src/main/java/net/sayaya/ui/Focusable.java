package net.sayaya.ui;

public interface Focusable<W extends Focusable<W>> {
	W accessKey(char key);
	W focus();
}
