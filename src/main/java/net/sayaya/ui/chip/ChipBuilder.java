package net.sayaya.ui.chip;

import elemental2.dom.EventListener;
import elemental2.dom.HTMLElement;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain=true)
public class ChipBuilder<C extends Chip<C>> {
	private String text;
	private Chip<C> chip;
	private ChipBuilder(){}
	public static ChipBuilder<Chip.ChipImpl> chip() {
		return new ChipBuilder<Chip.ChipImpl>().setChip(new Chip.ChipImpl());
	}
	public ChipBuilder<ChipDecorator.ChipRemovable> removable(EventListener... listeners) {
		ChipDecorator.ChipRemovable chip = ChipDecorator.setRemovable(this.chip);
		for(EventListener listener: listeners) chip.addDetachHandler(listener);
		return new ChipBuilder<ChipDecorator.ChipRemovable>().setChip(chip).setText(text);
	}
	public ChipBuilder<C> thumbnails(HTMLElement thumbnail) {
		chip = ChipDecorator.setThumbnails(chip, thumbnail);
		return this;
	}
	public C element() {
		return chip.setText(text);
	}
}
