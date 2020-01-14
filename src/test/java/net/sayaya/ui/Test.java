package net.sayaya.ui;

import com.google.gwt.core.client.EntryPoint;
import elemental2.dom.DomGlobal;
import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.input.Checkbox;
import net.sayaya.ui.input.button.ButtonText;
import net.sayaya.ui.style.Style;
import org.jboss.gwt.elemento.core.Elements;

public class Test implements EntryPoint {
	@Override
	public void onModuleLoad() {
		MsgBoxText();
		TestButtonText();
	}

	void MsgBoxText() {
		Elements.body().add(new MsgBox());
	}
	void TestButtonText() {
		ButtonText tmp = new ButtonText("Text Button");
		Style style = new Style().setBorder("1px solid #ddd").setFontFamily("Arial");
		tmp.setStyle(style);

		HandlerRegistration handler = tmp.addClickHandler(evt->{
			DomGlobal.alert("Hello, World!!");
		});
		Elements.body().add(tmp);

		ButtonText tmp2 = new ButtonText("Button Enabled").setEnabled(true);
		Elements.body().add(tmp2);

		ButtonText tmp3 = new ButtonText("Button Disabled").setEnabled(false);
		Elements.body().add(tmp3);

		ButtonText tmp4 = new ButtonText("Button Focused").setFocus().setAccessKey('A').setTabIndex(1);
		Elements.body().add(tmp4);

		Checkbox tmp5 = new Checkbox().setFocus().setAccessKey('A').setTabIndex(1).setStyle(style);
		Elements.body().add(tmp5);
	}
}
