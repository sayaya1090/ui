package net.sayaya.ui;

import com.google.gwt.core.client.*;
import com.google.gwt.user.client.Random;
import elemental2.dom.*;
import jsinterop.base.JsPropertyMap;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.sayaya.ui.animate.Animation;
import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.grid.*;
import net.sayaya.ui.input.Radio;
import net.sayaya.ui.input.TextField;
import net.sayaya.ui.input.TextFieldOutlined;
import net.sayaya.ui.layout.GridLayoutResponsive;
import net.sayaya.ui.layout.TabBar;
import net.sayaya.ui.style.Style;
import net.sayaya.ui.table.RowRenderer;
import net.sayaya.ui.table.Table;
import net.sayaya.ui.table.Viewport;
import org.jboss.gwt.elemento.core.Elements;
import org.jboss.gwt.elemento.core.InputType;
import org.jboss.gwt.elemento.core.builder.HtmlContentBuilder;

import java.util.Date;

import static net.sayaya.ui.table.TableBuilder.TableBodyBuilder.body;
import static net.sayaya.ui.table.TableBuilder.TableHeaderBuilder.header;
import static net.sayaya.ui.table.TableBuilder.TableHeaderRowBuilder.row;
import static net.sayaya.ui.table.TableBuilder.TableHeaderCellBuilder.cell;
import static net.sayaya.ui.table.TableBuilder.table;
import static org.jboss.gwt.elemento.core.Elements.*;

public class Test implements EntryPoint {
	private final HtmlContentBuilder<HTMLDivElement> content = div();
	@Override
	public void onModuleLoad() {
		LayoutTest();
		ProgressTest();
		RadioTest();
		// AnimationTest();
		TestButtonText();
		TestChip();
		TestList();
		// TestTable();
		// TestGrid();
	}
	void LayoutTest() {
		TopBar.TopBarButton menu = TopBar.TopBarButton.nav().build("menu");
		TopBar top = TopBar.topbar()
						   .section(TopBar.TopBarSection.left().build()
														.add(menu)
														.title("Test Top Bar"))
						   .section(TopBar.TopBarSection.right().build()
														.add(new TabBar().element())
														.add(TopBar.TopBarButton.action().build("file_download")))
						   .build(content.element());
		Drawer drawer = Drawer.drawer().header(Drawer.DrawerHeader.drawerHeader()
																  .title(label().add("Mail"))
																  .subtitle(label().add("AAA")))
							  .content(Drawer.DrawerContent.drawerContent()
														   .header("Mail")
														   .divider()
														   .add(Drawer.DrawerListItem.item().icon("inbox").text("Inbox").activate(true))
														   .add(Drawer.DrawerListItem.item().icon("star").text("Star"))
														   .add(Drawer.DrawerListItem.item().icon("send").text("Sent Main")))
							  .build();
		HTMLDivElement div = div().add(top).add(content).element();
		Drawer.setContent(div);
		Elements.body().add(drawer);
		Elements.body().add(div);
		menu.addClickHandler(evt->drawer.toggle());
		GridLayoutResponsive.addHandler(evt->{
			DomGlobal.alert(evt.getGridInfo());
		});
	}
	void ProgressTest() {
		ProgressBar elem = ProgressBar.progressBar().build();
		content.add(elem.determinate(true).buffer(0.5).progress(0.2));
	}
	void RadioTest() {
		Radio<String> a = Radio.radio("RADIO", "A").element();
		Radio<String> b = Radio.radio("RADIO", "B").element();
		Radio<String> c = Radio.radio("RADIO", "C").element();
		Radio<String> d = Radio.radio("RADIO", "D").element();
		a.addValueChangeHandler(evt->{
			DomGlobal.alert(evt.value());
		});
		content.add(a).add(b).add(c).add(d).element();
	}
	void AnimationTest() {
		Button tmp = Button.button().contain().text("Text Button").build();
		content.add(tmp);
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
		Chip chip = Chip.chip().text("Chip").removable(evt->{
			DomGlobal.alert("Remove Chip");
		}).build();
		content.add(chip);
	}
	void TestList() {
		List list = List.list()
				.add(List.ListItem.item().label("AA").build())
				.add(List.ListItem.item().label("BB", "CCCCCCCCC").build())
				.add(List.ListItem.item().label("BB", "CCCCCCCCC").build())
				.add(List.Divider.divider())
				.add(List.ListItem.item().label("BB", "CCCCCCCCC").selectable().build())
				.add(List.ListItem.item().label("DD", "CCCCCCCCC").selectable().build())
				.build();
		content.add(list);
	}
	void TestButtonText() {
		Button tmp = Button.button().contain().text("Text Button").build();
		HandlerRegistration handler = tmp.addClickHandler(evt->{
			DomGlobal.alert("Hello, World!!");
		});
		content.add(tmp);

		Button tmp2 = Button.button().flat().text("Button Enabled")
				.iconBefore(Icon.icon("sync").build())
				.build().enabled(true);
		content.add(tmp2);

		Button tmp3 = Button.button().contain().text("Button Disabled").build().enabled(false);
		content.add(tmp3);

		Button tmp4 = Button.button().outline().text("Button Focused").build().focus().accessKey('A');
		content.add(tmp4);

		/*CheckBox tmp5 = InputBuilder.checkbox().element().focus().accessKey('A');
		content.add(InputDecorator.label(tmp5).setLabel("Label"));
		tmp5.addValueChangeHandler(evt->{
		//	DomGlobal.alert(evt.getValue());
		});*/

		TextFieldOutlined<String> tmp6 = TextField.textBox().label("tmp6").outlined().iconLeading("favorite").iconTrailing("visibility").element();
		tmp6.addValueChangeHandler(evt->{
		// 	DomGlobal.alert(evt.getValue());
		});
		content.add(tmp6);
		/*EmailBox tmp7 = new EmailBox().setFocus().setAccessKey('C').setStyle(style);
		content.add(tmp7);
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
		content.add(new Viewport(table.values(values)));
	}
	void TestGrid() {
		GridTree grid = GridTree.builder().scrollY(false)
				.header(HeaderOption.builder().height(50)
						.column(HeaderColumn.builder().name("id").renderer(()->{
							HTMLDivElement div = div().element();
							HTMLInputElement chk = input(InputType.checkbox).element();
							div.appendChild(chk);
							return div;
						}).build())
						.build())
				.colunmOptions(ColumnOption.builder().minWidth(300).frozenCount(1).build())
				.column(Column.builder(String.class).header("Id").name("id").editor("text").onBeforeChange(evt->{
					DomGlobal.console.log(evt.getValue() + "=>" + evt.getNextValue());
				}).renderer(()->div().element(),
						(elem, prop)->{
							elem.innerHTML = prop.value();
						}).build())
				.column(Column.builder(String.class).header("B").name("city").editor("text").widthMin(800).build())
				.column(Column.builder(String.class).header("C").name("country").editor("text").widthMin(800).build())
				.data(new DatumNode[]{
						new DatumNode().put("id", "10012").put("city", "CDAF").put("country", "FWEFEWF")
								.attribute(DatumNode.Attribute.builder().expanded(true).build())
								.children(new DatumNode().put("id", "10012").put("city", "CDAF").put("country", "FWEFEWF"),new DatumNode().put("id", "10012").put("city", "CDAF").put("country", "FWEFEWF"), new DatumNode().put("id", "10012").put("city", "CDAF").put("country", "FWEFEWF"))})
				.treeColumnOptions(new GridTreeSettings.TreeColumnOption().name("id")
						.useCascadingCheckbox(false)
						.useIcon(false))
				.element();
		content.add(grid);
	}
}
