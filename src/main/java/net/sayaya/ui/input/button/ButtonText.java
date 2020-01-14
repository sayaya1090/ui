package net.sayaya.ui.input.button;

import net.sayaya.ui.input.Button;

public class ButtonText extends Button<ButtonText> {
	public ButtonText(String text) {
		element().setAttribute("value", text);
	}
}
