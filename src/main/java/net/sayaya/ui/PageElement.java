package net.sayaya.ui;

import elemental2.dom.*;
import net.sayaya.ui.event.HasValueChangeHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;
import org.jboss.elemento.HtmlContentBuilder;

import java.util.HashSet;
import java.util.Set;

import static org.jboss.elemento.Elements.*;

public class PageElement extends HTMLElementBuilder<HTMLDivElement, PageElement> implements HasValueChangeHandlers<PageElement> {
	public static PageElement instance() {
		return new PageElement(div().style("display: flex;" +
									"padding-left: 0;" +
									"list-style: none;" +
									"border-radius: 0.25rem;"));
	}
	private long total;
	private long idx;
	private int show;
	private Boolean isAsc;
	private final HtmlContentBuilder<HTMLLabelElement> lblTotal = label().style("display: inline-block; margin-top: auto; margin-bottom: auto; margin-right: auto;");
	private final HtmlContentBuilder<HTMLLabelElement> lblPageMax = label().style("display: inline-block; margin-top: auto; margin-bottom: auto; margin-right: 5px;");
	private final HtmlContentBuilder<HTMLLabelElement> lblIdxFirst = label().style("display: inline-block; margin-top: auto; margin-bottom: auto; ");
	private final HtmlContentBuilder<HTMLLabelElement> lblIdxLast = label().style("display: inline-block; margin-top: auto; margin-bottom: auto; margin-right: 5px;");
	private final ButtonElement btnPrevious = ButtonElement.icon("chevron_left");
	private final ButtonElement btnNext = ButtonElement.icon("chevron_right");
	private final ButtonElement btnFirst = ButtonElement.icon("first_page");
	private final ButtonElement btnLast = ButtonElement.icon("last_page");
	private final HtmlContentBuilder<HTMLDivElement> sort = div();
	private DropDownElement iptSort = null;
	private final DropDownElement iptAsc = DropDownElement.outlined(ListElement.singleLineList().add(ListElement.singleLine().label("Asc")).add(ListElement.singleLine().label("Desc"))).style("width: 120px;")
			.text("Order");
	private final TextFieldElement<Double> iptPage = TextFieldElement.numberBox().outlined().style("display: inline-block; margin-top: auto; margin-bottom: auto; padding-right: 2px; width: 60px;height: 28px; background-color: #FFFFFF; font-size: 13px !important;").attr("min", "1");
	private final HtmlContentBuilder<HTMLDivElement> _this;
	public PageElement(HtmlContentBuilder<HTMLDivElement> e) {
		super(e.css("page"));
		_this = e.add(lblIdxFirst)
				 .add(lblIdxLast)
				 .add(lblTotal)
				 .add(btnFirst)
				 .add(btnPrevious)
				 .add(iptPage)
				 .add(lblPageMax)
				 .add(btnNext)
				 .add(btnLast)
				 .add(sort);
		iptPage.element().getElementsByClassName("mdc-notched-outline__notch").asList()
			   .forEach(c->((HTMLElement)c).style.paddingRight = CSSProperties.PaddingRightUnionType.of("0px"));
		iptPage.input().style("text-align: right; padding-left: 2px; ");
		iptPage.onValueChange(evt->{
			try {
				page(evt.value().longValue());
			} catch(Exception ignore){}
		});
		btnFirst.onClick(evt->{
			iptPage.value(1.0);
			page(1);
		});
		btnPrevious.onClick(evt->{
			try {
				long page = iptPage.value().longValue() - 1;
				iptPage.value(page+0.0);
				page(page);
			} catch(Exception ignore){}
		});
		btnNext.onClick(evt->{
			try {
				long page = iptPage.value().longValue() + 1;
				iptPage.value(page+0.0);
				page(page);
			} catch(Exception ignore){}
		});
		btnLast.onClick(evt->{
			Double page = Math.ceil(total/(double)show);
			iptPage.value(1.0);
			page(page.longValue());
		});
		iptAsc.onSelectionChange(evt->{
			if("Asc".equalsIgnoreCase(iptAsc.value())) this.isAsc = true;
			else if("Desc".equalsIgnoreCase(iptAsc.value())) this.isAsc = false;
			else this.isAsc = null;
			try { fire(); } catch(Exception ignore){}
		});
	}
	public long total() {
		return total;
	}
	public PageElement total(long total) {
		this.total = total;
		lblTotal.textContent("of " + total);
		lblPageMax.textContent("of " + ((total-1)/show+1));
		lblIdxLast.textContent(String.valueOf(Math.min(total, idx+show)));
		iptPage.attr("max", String.valueOf(Math.ceil(total/(double)show)));
		if(idx >= total - show) {
			btnNext.enabled(false);
			btnLast.enabled(false);
		} else {
			btnNext.enabled(true);
			btnLast.enabled(true);
		}
		iptPage.input().autofocus(true);
		return this;
	}
	public int show() {
		return show;
	}
	public PageElement show(int show) {
		this.show = show;
		iptPage.value(Math.ceil((idx+1)/(double)show));
		lblPageMax.textContent("of " + (Math.ceil((total-1)/(double)show)+1));
		lblIdxLast.textContent(String.valueOf(Math.min(total, idx+show)));
		if(idx >= total - show) {
			btnNext.enabled(false);
			btnLast.enabled(false);
		} else {
			btnNext.enabled(true);
			btnLast.enabled(true);
		}
		return this;
	}
	public long idx() {
		return idx;
	}
	public PageElement idx(long idx) {
		this.idx = idx;
		iptPage.value(Math.ceil((idx+1)/(double)show));
		lblIdxFirst.textContent((idx+1) + " - ");
		lblIdxLast.textContent(String.valueOf(Math.min(total, idx+show)));
		if(idx <= 0) {
			btnPrevious.enabled(false);
			btnFirst.enabled(false);
		} else {
			btnPrevious.enabled(true);
			btnFirst.enabled(true);
		}
		if(idx >= total - show) {
			btnNext.enabled(false);
			btnLast.enabled(false);
		} else {
			btnNext.enabled(true);
			btnLast.enabled(true);
		}
		return this;
	}
	public long page() {
		return idx/show;
	}
	private void page(long page) {
		idx(show*(page-1));
		fire();
	}
	public PageElement sortable(String c1, String... columns) {
		ListElement<ListElement.SingleLineItem> listElementSort = ListElement.singleLineList();
		listElementSort.add(ListElement.singleLine().label(c1));
		if(columns!=null) for(String column: columns) listElementSort.add(ListElement.singleLine().label(column));
		iptSort = DropDownElement.outlined(listElementSort).text("Sort column").style("width: 160px;");
		sort.style("display: flex;margin-left: auto;").add(iptSort).add(iptAsc);
		iptSort.onSelectionChange(evt->{
			try { fire(); } catch(Exception ignore){}
		});
		return that();
	}
	public PageElement sort(String column, boolean isAsc) {
		if(iptSort == null) return that();
		iptSort.select(column);
		if(isAsc) iptAsc.select(0);
		else iptAsc.select(1);
		return that();
	}
	public boolean isAsc() {
		if(isAsc == null) return true;
		else return isAsc;
	}
	public String sortBy() {
		return iptSort.value();
	}
	@Override
	public PageElement that() {
		return this;
	}

	@Override
	public PageElement value() {
		return that();
	}

	private void fire() {
		Event evt;
		try {
			evt = new Event("change");
		} catch(Exception e) {
			evt = new CustomEvent("change");
		}
		ValueChangeEvent<PageElement> e = ValueChangeEvent.event(evt, this);
		for(ValueChangeEventListener<PageElement> listener: listeners) listener.handle(e);
	}
	private final Set<ValueChangeEventListener<PageElement>> listeners = new HashSet<>();
	@Override
	public HandlerRegistration onValueChange(ValueChangeEventListener<PageElement> listener) {
		listeners.add(listener);
		return ()->listeners.remove(listener);
	}
}
