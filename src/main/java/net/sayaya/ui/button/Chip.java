package net.sayaya.ui.button;

import net.sayaya.ui.ButtonBase;

public class Chip extends ButtonBase<Chip> {
	public Chip(String text) {
		element().setAttribute("value", text);
	}
}
