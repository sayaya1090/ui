package net.sayaya.ui;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import elemental2.dom.DomGlobal;
import jsinterop.base.JsPropertyMap;
import net.sayaya.ui.animate.Animation;
import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.input.Checkbox;
import net.sayaya.ui.button.ButtonText;
import net.sayaya.ui.input.EmailBox;
import net.sayaya.ui.input.TextBox;
import net.sayaya.ui.layout.GridLayoutResponsive;
import net.sayaya.ui.style.Style;
import org.jboss.gwt.elemento.core.Elements;

public class Test implements EntryPoint {
	@Override
	public void onModuleLoad() {
		LayoutTest();
		// AnimationTest();
		MsgBoxText();
		TestButtonText();
	}
	void LayoutTest() {
		GridLayoutResponsive.addHandler(evt->{
			DomGlobal.alert(evt.getGridInfo());
		});
	}
	void AnimationTest() {
		ButtonText tmp = new ButtonText("Text Button");
		Elements.body().add(tmp);
		tmp.setStyle(new Style().setPosition("relative"));
		Animation.AnimationImpl t = Animation.animate(tmp.element(), 5000
				, JsPropertyMap.of("left", "0px", "backgroundColor", "#FF00FF", "opacity", "1")
				, JsPropertyMap.of("left", "300px", "opacity", "0")
				, JsPropertyMap.of("left", "0px", "backgroundColor", "#0000FF", "opacity", "1"));
		Scheduler.get().scheduleFixedDelay(()->{t.pause(); return false;}, 1000);
		DomGlobal.console.info(t);
		t.finished.then(a->{
			GWT.log("A");
			return null;
		});
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

		ButtonText tmp4 = new ButtonText("Button Focused").setFocus().setAccessKey('A');
		Elements.body().add(tmp4);

		Checkbox tmp5 = new Checkbox().setFocus().setAccessKey('A').setStyle(style);
		Elements.body().add(tmp5);
		tmp5.addValueChangeHandler(evt->{
			DomGlobal.alert(evt.getValue());
		});

		TextBox tmp6 = new TextBox().setFocus().setAccessKey('B').setStyle(style);
		Elements.body().add(tmp6);
		tmp6.addValueChangeHandler(evt->{
			DomGlobal.alert(evt.getValue());
		});

		EmailBox tmp7 = new EmailBox().setFocus().setAccessKey('C').setStyle(style);
		Elements.body().add(tmp7);
		tmp7.addValueChangeHandler(evt->{
			DomGlobal.alert(evt.getValue());
		});
	}
}
