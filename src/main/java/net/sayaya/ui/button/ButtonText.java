package net.sayaya.ui.button;

import net.sayaya.ui.ButtonBase;

public class ButtonText extends ButtonBase<ButtonText> {
	public ButtonText(String text) {
		element().innerHTML=text;
	}
}
