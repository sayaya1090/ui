package net.sayaya.ui.chip;

import elemental2.dom.*;
import jsinterop.base.JsPropertyMap;
import net.sayaya.ui.animate.Animation;
import net.sayaya.ui.style.Style;

import static net.sayaya.ui.animate.Animation.animate;
import static org.jboss.gwt.elemento.core.Elements.i;
import static org.jboss.gwt.elemento.core.Elements.span;

public class ChipDecorator {
	public static class ChipRemovable extends Chip<ChipRemovable> {
		private final Chip<?> chip;
		private final HTMLElement remove = i().css("fa fa-times-circle").element();
		private final Style styleRemove = Style.build().marginLeft("5px");
		ChipRemovable(Chip<?> chip) {
			this.chip = chip;
			remove.addEventListener("click", evt->{
				Animation.AnimationImpl fade = animate(chip.element(), 300, JsPropertyMap.of("opacity", "1"), JsPropertyMap.of("opacity", "0"));
				fade.onfinish = chip.element()::remove;
			});
			styleRemove.apply(remove.style);
			chip.element().appendChild(remove);
		}
		@Override
		HTMLLabelElement label() {
			return chip.label();
		}
		@Override
		public ChipRemovable text(String text) {
			chip.text(text);
			return this;
		}
		@Override
		public String text() {
			return chip.text();
		}
		@Override
		public HTMLElement element() {
			return chip.element();
		}
	}
	static ChipRemovable removable(Chip<?> chip) {
		return new ChipRemovable(chip);
	}
	static <C extends Chip<C>> Chip<C> thumbnails(Chip<C> chip, HTMLElement thumbnail) {
		HTMLLabelElement label = chip.label();
		while(label.previousElementSibling != null) chip.element().removeChild(label.previousElementSibling);
		HTMLElement span = span().add(thumbnail).element();
		Style style = Style.build().display("inline-flex").overflow("hidden").width("18px").marginRight("5px").marginLeft("-6px").borderRadius("10px").borderWidth("1px").borderStyle("solid");
		style.apply(span.style);
		chip.element().insertBefore(span, label);
		thumbnail.style.marginLeft = CSSProperties.MarginLeftUnionType.of("auto");
		thumbnail.style.marginRight = CSSProperties.MarginRightUnionType.of("auto");
		return chip;
	}
}
