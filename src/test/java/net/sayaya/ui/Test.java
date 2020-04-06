package net.sayaya.ui;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.TextBox;
import elemental2.dom.*;
import jsinterop.base.JsPropertyMap;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.sayaya.ui.animate.Animation;
import net.sayaya.ui.button.Button;
import net.sayaya.ui.button.ButtonBuilder;
import net.sayaya.ui.chip.ChipBuilder;
import net.sayaya.ui.chip.ChipDecorator;
import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.grid.*;
import net.sayaya.ui.input.TextField;
import net.sayaya.ui.input.TextFieldBuilder;
import net.sayaya.ui.input.TextFieldFilled;
import net.sayaya.ui.input.TextFieldOutlined;
import net.sayaya.ui.layout.GridLayoutResponsive;
import net.sayaya.ui.style.Style;
import net.sayaya.ui.table.RowRenderer;
import net.sayaya.ui.table.Table;
import net.sayaya.ui.table.Viewport;
import org.jboss.gwt.elemento.core.Elements;
import org.jboss.gwt.elemento.core.InputType;

import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;

import static net.sayaya.ui.table.TableBuilder.TableBodyBuilder.body;
import static net.sayaya.ui.table.TableBuilder.TableHeaderBuilder.header;
import static net.sayaya.ui.table.TableBuilder.TableHeaderRowBuilder.row;
import static net.sayaya.ui.table.TableBuilder.TableHeaderCellBuilder.cell;
import static net.sayaya.ui.table.TableBuilder.table;
import static org.jboss.gwt.elemento.core.Elements.label;
import static org.jboss.gwt.elemento.core.Elements.td;

public class Test implements EntryPoint {
	@Override
	public void onModuleLoad() {
		// LayoutTest();
		// AnimationTest();
		TestButtonText();
		// TestChip();
		// TestTable();
		// TestGrid();
	}
	void LayoutTest() {
		GridLayoutResponsive.addHandler(evt->{
			DomGlobal.alert(evt.getGridInfo());
		});
	}
	void AnimationTest() {
		Button tmp = ButtonBuilder.button().contain().text("Text Button").element();
		Elements.body().add(tmp);
		tmp.style(Style.build().position("relative"));
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
		ChipDecorator.ChipRemovable chip = ChipBuilder.chip().text("Chip").removable().element();
		chip.addDetachHandler(evt->{
			DomGlobal.alert("Remove Chip");
		});
		Elements.body().add(chip);
	}
	void TestButtonText() {
		Button tmp = ButtonBuilder.button().contain().text("Text Button").element();
		HandlerRegistration handler = tmp.addClickHandler(evt->{
			DomGlobal.alert("Hello, World!!");
		});
		Elements.body().add(tmp);

		Button tmp2 = ButtonBuilder.button().flat().text("Button Enabled").icon("sync").element().enabled(true);
		Elements.body().add(tmp2);

		Button tmp3 = ButtonBuilder.button().contain().text("Button Disabled").element().enabled(false);
		Elements.body().add(tmp3);

		Button tmp4 = ButtonBuilder.button().outline().text("Button Focused").element().focus().accessKey('A');
		Elements.body().add(tmp4);

		/*CheckBox tmp5 = InputBuilder.checkbox().element().focus().accessKey('A');
		Elements.body().add(InputDecorator.label(tmp5).setLabel("Label"));
		tmp5.addValueChangeHandler(evt->{
		//	DomGlobal.alert(evt.getValue());
		});*/

		TextFieldOutlined<String> tmp6 = TextFieldBuilder.textBox().label("tmp6").outlined().iconLeading("favorite").iconTrailing("visibility").build();
		tmp6.addValueChangeHandler(evt->{
		// 	DomGlobal.alert(evt.getValue());
		});
		Elements.body().add(tmp6);
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
		T[] values = new T[12800];
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
		@Setter
		abstract class RowRendererImpl implements RowRenderer {
			private RowRenderer forward;
			private RowRenderer backword;

			protected abstract void delegate(HTMLTableRowElement elem, int dataIdx, net.sayaya.ui.table.Data value);
			@Override
			public SiblingRowRenderers render(HTMLTableRowElement elem, int dataIdx, net.sayaya.ui.table.Data value) {
				delegate(elem, dataIdx, value);
				return new SiblingRowRenderers() {
					@Override
					public RowRenderer forward() {
						return forward;
					}

					@Override
					public RowRenderer backward() {
						return backword;
					}
				};
			}
		}
		RowRendererImpl row1 = new RowRendererImpl() {
			@Override
			protected void delegate(HTMLTableRowElement elem, int dataIdx, net.sayaya.ui.table.Data value) {
				HTMLTableCellElement td1;
				HTMLTableCellElement td2;
				HTMLTableCellElement td3;
				if(elem.childElementCount != 3) {
					while(elem.childElementCount > 0) elem.firstElementChild.remove();
					td1 = td().attr("rowspan", "2").element();
					td2 = td().attr("colspan", "4").element();
					td3 = td().attr("colspan", "2").element();
					elem.appendChild(td1);
					elem.appendChild(td2);
					elem.appendChild(td3);
				} else {
					td1 = (HTMLTableCellElement) elem.firstElementChild;
					td2 = (HTMLTableCellElement) elem.childNodes.item(1);
					td3 = (HTMLTableCellElement) elem.childNodes.item(2);
				}
				td1.innerHTML = value.get("A1", Integer.class)+"";
				td2.innerHTML = value.get("C1", String.class);
				td3.innerHTML = value.get("C2", String.class);
			}
		};
		RowRendererImpl row2 = new RowRendererImpl() {
			@Override
			protected void delegate(HTMLTableRowElement elem, int dataIdx, net.sayaya.ui.table.Data value) {
				HTMLTableCellElement td;
				if(elem.childElementCount <= 0) {
					td = td().element();
					elem.appendChild(td);
				} else td = (HTMLTableCellElement) elem.firstElementChild;
				td.innerHTML = value.get("V1", String.class);
			}
		};
		row1.setForward(row2);
		row1.setBackword(row2);
		row2.setBackword(row1);
		Table<T> table = table().set(header().numOfColumnsPrepared(30)
				.add(row().add(cell("A1").rowspan(2))
						.add(cell("C1").colspan(4))
						.add(cell("C2").colspan(2)))
				.add(row().add(cell("V1"))
						.add(cell("V2"))
						.add(cell("V3"))
						.add(cell("V4"))
						.add(cell("V5"))
						.add(cell("V6"))))
				.set(body().numOfRowsPrepared(100)
						.renderer(row1)).map((T t)-> new net.sayaya.ui.table.Data().put("A1", t.row)
						.put("C1", t.t1).put("C2", t.t2)
						.put("V1", t.v1).put("V2", t.v2).put("V3", t.v3).put("V4", t.v4).put("V5", t.v5).put("V6", t.v6)).build();
		Elements.body().add(new Viewport(table.values(values)));
	}
	void TestGrid() {
		Grid grid = Grid.builder().scrollX(false).scrollY(false)
						.columns(new Column[] {
								new Column().header("Id").name("id").editor("text"),
								new Column().header("City").name("city").renderer(()->{
									DomGlobal.console.log("Create");
									return Elements.input(InputType.text).element();
								}, (e, p)->{
									((HTMLInputElement)e).value = (String) p.value();
								}, (e, p)->{
									DomGlobal.console.log("FWFEW");
								}),
								new Column().header("Country").name("country").editor("text")
						}).treeColumnOption(new TreeColumnOption().id("id").useCascadingCheckbox(false).useIcon(false)).build();
		Elements.body().add(grid);
		Scheduler.get().scheduleFixedDelay(()->{
			Datum[] data = new Datum[]{
					new Datum().put("id", "10012").put("city", "CDAF").put("country", "FWEFEWF"),
					new Datum().put("id", "10013").put("city", "CDAF").put("country", "FWEFEWF"),
					new Datum().put("id", "10014").put("city", "CDAF").put("country", "FWEFEWF"),
					new Datum().put("id", "10015").put("city", "CDAF").put("country", "FWEFEWF")
			};
			grid.update(data);
			return false;
		}, 1000);
	}
}
