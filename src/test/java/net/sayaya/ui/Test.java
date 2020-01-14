package net.sayaya.ui;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import net.sayaya.ui.input.button.ButtonText;
import org.jboss.gwt.elemento.core.Elements;

public class Test implements EntryPoint {
	@Override
	public void onModuleLoad() {
		Elements.body().add(new MsgBox());

		ButtonText tmp = new ButtonText("Text Button");
		tmp.addClickHandler(evt->{
			Window.alert("Hello, World!");
		});
		Elements.body().add(tmp);
	}
}
