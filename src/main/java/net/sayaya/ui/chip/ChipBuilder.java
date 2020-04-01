package net.sayaya.ui.chip;

import elemental2.dom.EventListener;
import elemental2.dom.HTMLElement;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(fluent=true)
public class ChipBuilder<C extends Chip<C>> {
	private String text;
	private Chip<C> chip;
	private ChipBuilder(){}
	public static ChipBuilder<Chip.ChipImpl> chip() {
		return new ChipBuilder<Chip.ChipImpl>().chip(new Chip.ChipImpl());
	}
	public ChipBuilder<ChipDecorator.ChipRemovable> removable(EventListener... listeners) {
		ChipDecorator.ChipRemovable chip = ChipDecorator.removable(this.chip);
		for(EventListener listener: listeners) chip.addDetachHandler(listener);
		return new ChipBuilder<ChipDecorator.ChipRemovable>().chip(chip).text(text);
	}
	public ChipBuilder<C> thumbnails(HTMLElement thumbnail) {
		chip = ChipDecorator.thumbnails(chip, thumbnail);
		return this;
	}
	public C element() {
		return chip.text(text);
	}
}
