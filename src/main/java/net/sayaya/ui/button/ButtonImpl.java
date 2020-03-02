package net.sayaya.ui.button;

import net.sayaya.ui.ButtonBase;

public class ButtonImpl extends ButtonBase<ButtonImpl> {
	ButtonImpl(String text) {
		element().innerHTML=text;
	}
}
