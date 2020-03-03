package net.sayaya.ui.input;

import elemental2.dom.CSSStyleDeclaration;
import elemental2.dom.HTMLElement;
import net.sayaya.ui.style.Style;
import org.jboss.gwt.elemento.core.InputType;
import static org.jboss.gwt.elemento.core.Elements.input;
import static org.jboss.gwt.elemento.core.Elements.span;

public class CheckBox extends InputBase<Boolean, CheckBox> {
	private final static Style style = new Style().setDisplay("none");
	private final HTMLElement psudo = span().element();
	private final static Style style2 = new Style().setDisplay("block");
	CheckBox() {
		super(input(InputType.checkbox).style().element());
		setStyle(style);
		element().appendChild(psudo);
		style2.apply(psudo.style);
	}
	/*
	  content: '';
  display: block;
  width: 20px;
  height: 20px;
  border: 1px solid #6cc0e5;
  position: absolute;
  left: 0;
  top: 0;
  opacity: .6;
  -webkit-transition: all .12s, border-color .08s;
  transition: all .12s, border-color .08s;
	 */
	@Override
	public Boolean getValue() {
		return element().checked;
	}
}
