package net.sayaya.ui.input;

import com.google.gwt.core.client.GWT;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import org.jboss.gwt.elemento.core.InputType;
import static org.jboss.gwt.elemento.core.Elements.input;

public class CheckBox extends InputBase<Boolean, CheckBox> {
	public interface Resource extends ClientBundle {
		@Source("CheckBox.gss")
		Style style();

		interface Style extends CssResource {
			String check();
		}
	}
	private static final Resource RESOURCE =  GWT.create(Resource.class);
	public static final Resource.Style GSS = RESOURCE.style();
	static {
		RESOURCE.style().ensureInjected();
	}
	CheckBox() {
		super(input(InputType.checkbox).css(GSS.check()).element());
	}
	@Override
	public Boolean value() {
		return element().checked;
	}
}
