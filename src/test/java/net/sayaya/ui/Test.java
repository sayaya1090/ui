package net.sayaya.ui;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Random;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLInputElement;
import jdk.jfr.DataAmount;
import jsinterop.base.JsPropertyMap;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.sayaya.ui.animate.Animation;
import net.sayaya.ui.button.ButtonBuilder;
import net.sayaya.ui.chip.ChipBuilder;
import net.sayaya.ui.chip.ChipDecorator;
import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.input.CheckBox;
import net.sayaya.ui.button.ButtonImpl;
import net.sayaya.ui.input.InputBuilder;
import net.sayaya.ui.input.InputDecorator;
import net.sayaya.ui.input.TextBox;
import net.sayaya.ui.layout.GridLayoutResponsive;
import net.sayaya.ui.style.Style;
import net.sayaya.ui.table.Table;
import net.sayaya.ui.table.Viewport;
import org.jboss.gwt.elemento.core.Elements;
import org.jboss.gwt.elemento.core.InputType;

import java.util.Date;

import static net.sayaya.ui.table.TableBuilder.TableBodyBuilder.body;
import static net.sayaya.ui.table.TableBuilder.TableHeaderBuilder.header;
import static net.sayaya.ui.table.TableBuilder.TableHeaderRowBuilder.row;
import static net.sayaya.ui.table.TableBuilder.TableHeaderCellBuilder.cell;
import static net.sayaya.ui.table.TableBuilder.table;
import static org.jboss.gwt.elemento.core.Elements.label;

public class Test implements EntryPoint {
	@Override
	public void onModuleLoad() {
		// LayoutTest();
		// AnimationTest();
		TestButtonText();
		// TestChip();
		TestTable();
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
		chip.addDetachHandler(evt->{
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
		Elements.body().add(InputDecorator.label(tmp5).setLabel("Label"));
		tmp5.addValueChangeHandler(evt->{
		//	DomGlobal.alert(evt.getValue());
		});

		TextBox tmp6 = InputBuilder.text().element().setAccessKey('B');
		Elements.body().add(InputDecorator.label(tmp6).setLabel("Label"));
		tmp6.addValueChangeHandler(evt->{
		// 	DomGlobal.alert(evt.getValue());
		});

		/*EmailBox tmp7 = new EmailBox().setFocus().setAccessKey('C').setStyle(style);
		Elements.body().add(tmp7);
		tmp7.addValueChangeHandler(evt->{
			DomGlobal.alert(evt.getValue());
		});*/
	}

	void TestTable() {
		@Data
		@Accessors(fluent = true)
		class T {
			int row;
			String t1;
			String t2;
			String v1;
			Integer v2;
			Date v3;
			double v4;
			long v5;
			String v6;
		}
		T[] values = new T[100000];
		for(int i = 0; i < values.length; ++i) {
			String ch = (char)('A'+i%26)+"";
			String ch2 = (char)('a'+i%26)+"";
			int idx = i+1;
			values[i] = new T().row(idx).t1(ch + idx)
					.t2(ch2 + idx).v1("Value " + idx)
					.v2(idx).v3(null)
					.v4(Math.floor(Random.nextInt())/100.0).v5(Random.nextInt())
					.v6(ch);
		}
		Table<T> table = table().set(header().numOfColumnsPrepared(30)
											 .add(row().add(cell("A1").rowspan(2)
																	  .builder(d->d.get("A1", Integer.class), v-> {
																		  HTMLInputElement elem = Elements.input(InputType.number).element();
																		  elem.valueAsNumber = v;
																		  return elem;
																	  }))
													   .add(cell("C1").colspan(4))
													   .add(cell("C2").colspan(2)))
											 .add(row().add(cell("V1"))
													   .add(cell("V2"))
													   .add(cell("V3"))
													   .add(cell("V4"))
													   .add(cell("V5"))
													   .add(cell("V6"))))
								.set(body().numOfRowsPrepared(20))
								.map((T t)-> new net.sayaya.ui.table.Data().put("A1", t.row)
																		   .put("C1", t.t1).put("C2", t.t2)
																		   .put("V1", t.v1).put("V2", t.v2).put("V3", t.v3).put("V4", t.v4).put("V5", t.v5).put("V6", t.v6)).build();
		Elements.body().add(new Viewport(table.setValues(values)));
	}
}
