package net.sayaya.ui.input;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import org.jboss.gwt.elemento.core.InputType;

import static org.jboss.gwt.elemento.core.Elements.input;

public abstract class TextBoxBase<V, W extends InputBase<V, W>> extends InputBase<V, W> {
	public interface Resource extends ClientBundle {
		@Source("TextBox.gss")
		Resource.Style style();

		interface Style extends CssResource {
			String textbox();
		}
	}
	private static final Resource RESOURCE =  GWT.create(Resource.class);
	public static final Resource.Style GSS = RESOURCE.style();
	static {
		RESOURCE.style().ensureInjected();
	}
	protected TextBoxBase(InputType type) {
		super(input(type).css(GSS.textbox()).element());
	}
}
