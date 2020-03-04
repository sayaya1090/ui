package net.sayaya.ui.input;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import elemental2.dom.*;
import net.sayaya.ui.IsHTMLElement;

import org.jboss.gwt.elemento.core.Elements;
import static org.jboss.gwt.elemento.core.Elements.div;

public class InputDecorator {
	public interface InputDecoratorResource extends ClientBundle {
		@Source("InputDecorator.gss")
		InputDecoratorResource.Style style();

		interface Style extends CssResource {
			String labeled();
		}
	}
	private static final InputDecoratorResource RESOURCE =  GWT.create(InputDecoratorResource.class);
	public static final InputDecoratorResource.Style GSS = RESOURCE.style();
	static {
		RESOURCE.style().ensureInjected();
	}
	public static class InputLabeled implements IsHTMLElement<HTMLElement, InputLabeled> {
		private final HTMLLabelElement label = Elements.label().element();
		private final HTMLDivElement element;
		InputLabeled(Input<?, ?> delegate) {
			element = div().css(GSS.labeled()).add(delegate).add(label).element();
			if(delegate.element().id == null || delegate.element().id.isEmpty()) delegate.element().id = Document.get().createUniqueId();
			delegate.element().placeholder = "-";
			label.setAttribute("for", delegate.element().id);
		}
		public InputLabeled setLabel(String label) {
			this.label.innerHTML = label;
			return this;
		}
		@Override
		public HTMLElement element() {
			return element;
		}
	}
	public static InputLabeled label(Input<?, ?> input) {
		return new InputLabeled(input);
	}

	public static Object helper(Input<?, ?> input) {
		return null;
	}
}
