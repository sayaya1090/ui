package net.sayaya.ui.input;

import elemental2.dom.*;

import static org.jboss.gwt.elemento.core.Elements.div;

public abstract class TextFieldOutlined<V> extends TextField<V, TextFieldOutlined<V>> {
	TextFieldOutlined(HTMLInputElement input) {
		super(input);
	}

	@Override
	protected final HTMLDivElement initialize() {
		return div().css("mdc-text-field", "mdc-text-field--outlined")
					.add(input)
					.add(div().css("mdc-notched-outline")
							  .add(div().css("mdc-notched-outline__leading"))
							  .add(div().css("mdc-notched-outline__notch")
										.add(label))
							  .add(div().css("mdc-notched-outline__trailing")))
					.element();
	}
}
