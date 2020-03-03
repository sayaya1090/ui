package net.sayaya.ui;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import elemental2.dom.DomGlobal;
import jsinterop.base.JsPropertyMap;
import net.sayaya.ui.animate.Animation;
import net.sayaya.ui.button.ButtonBuilder;
import net.sayaya.ui.chip.ChipBuilder;
import net.sayaya.ui.chip.ChipDecorator;
import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.input.CheckBox;
import net.sayaya.ui.button.ButtonImpl;
import net.sayaya.ui.input.EmailBox;
import net.sayaya.ui.input.InputBuilder;
import net.sayaya.ui.input.TextBox;
import net.sayaya.ui.layout.GridLayoutResponsive;
import net.sayaya.ui.style.Style;
import org.jboss.gwt.elemento.core.Elements;

public class Test implements EntryPoint {
	@Override
	public void onModuleLoad() {
		LayoutTest();
		// AnimationTest();
		TestButtonText();
		TestChip();
	}
	void LayoutTest() {
		GridLayoutResponsive.addHandler(evt->{
			DomGlobal.alert(evt.getGridInfo());
		});
	}
	void AnimationTest() {
		ButtonImpl tmp = ButtonBuilder.button().contain().setText("Text Button").element();
		Elements.body().add(tmp);
		tmp.setStyle(new Style().setPosition("relative"));
		Animation.AnimationImpl t = Animation.animate(tmp.element(), 5000
				, JsPropertyMap.of("left", "0px", "backgroundColor", "#FF00FF", "opacity", "1")
				, JsPropertyMap.of("left", "300px", "opacity", "0")
				, JsPropertyMap.of("left", "0px", "backgroundColor", "#0000FF", "opacity", "1"));
		// Scheduler.get().scheduleFixedDelay(()->{t.finish(); return false;}, 1000);
		DomGlobal.console.info(t);
		t.onfinish = ()->{
			GWT.log("A");
		};
	}
	void TestChip() {
		ChipDecorator.ChipRemovable chip = ChipBuilder.chip().setText("Chip").removable().element();
		chip.addRemoveHandler(evt->{
			DomGlobal.alert("Remove Chip");
		});
		Elements.body().add(chip);
	}
	void TestButtonText() {
		ButtonImpl tmp = ButtonBuilder.button().contain().setText("Text Button").element();
		Style style = new Style().setBorder("1px solid #ddd").setFontFamily("Arial");
		tmp.setStyle(style);

		HandlerRegistration handler = tmp.addClickHandler(evt->{
			DomGlobal.alert("Hello, World!!");
		});
		Elements.body().add(tmp);

		ButtonImpl tmp2 = ButtonBuilder.button().contain().setText("Button Enabled").element().setEnabled(true);
		Elements.body().add(tmp2);

		ButtonImpl tmp3 = ButtonBuilder.button().contain().setText("Button Disabled").element().setEnabled(false);
		Elements.body().add(tmp3);

		ButtonImpl tmp4 = ButtonBuilder.button().contain().setText("Button Focused").element().setFocus().setAccessKey('A');
		Elements.body().add(tmp4);

		CheckBox tmp5 = InputBuilder.checkbox().element().setFocus().setAccessKey('A').setStyle(style);
		Elements.body().add(tmp5);
		tmp5.addValueChangeHandler(evt->{
			DomGlobal.alert(evt.getValue());
		});

		TextBox tmp6 = InputBuilder.text().element().setAccessKey('B').setStyle(style);
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
