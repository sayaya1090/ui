package net.sayaya.ui.button;

public class ButtonImpl extends Button<ButtonImpl> {
	ButtonImpl(String text) {
		element().innerHTML=text;
	}
}
