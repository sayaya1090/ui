package net.sayaya.ui;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import static org.jboss.gwt.elemento.core.Elements.*;
import static org.jboss.gwt.elemento.core.InputType.text;

public class MsgBox implements IsElement<HTMLElement> {
	private final HTMLElement root;
	private final HTMLElement newTodo;

	public MsgBox() {
		this.root = section().css("todoapp")
				.add(header().css("header")
						.add(h(1).textContent("Todo"))
						.add(newTodo = input(text).css("new-todo")
								.autofocus(true)
								.placeholder("New").element()))
				.element();
	}

	@Override
	public HTMLElement element() {
		return root;
	}
}
