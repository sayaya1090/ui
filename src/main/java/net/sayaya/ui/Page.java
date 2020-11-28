package net.sayaya.ui;

import elemental2.dom.*;
import net.sayaya.ui.event.HasValueChangeHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.HtmlContentBuilder;

import java.util.HashSet;
import java.util.Set;

import static org.jboss.elemento.Elements.*;

public class Page extends HTMLElementBuilder<HTMLDivElement, Page> implements HasValueChangeHandlers<Page> {
	public static Page instance() {
		return new Page(div().style("display: flex;" +
									"padding-left: 0;" +
									"list-style: none;" +
									"border-radius: 0.25rem;"));
	}
	private long total;
	private long idx;
	private int show;
	private final HtmlContentBuilder<HTMLLabelElement> lblTotal = label().style("display: inline-block; margin-top: auto; margin-bottom: auto; margin-right: auto;");
	private final HtmlContentBuilder<HTMLLabelElement> lblPageMax = label().style("display: inline-block; margin-top: auto; margin-bottom: auto; margin-right: 5px;");
	private final HtmlContentBuilder<HTMLLabelElement> lblIdxFirst = label().style("display: inline-block; margin-top: auto; margin-bottom: auto; ");
	private final HtmlContentBuilder<HTMLLabelElement> lblIdxLast = label().style("display: inline-block; margin-top: auto; margin-bottom: auto; margin-right: 5px;");
	private final Button btnPrevious = Button.icon("chevron_left");
	private final Button btnNext = Button.icon("chevron_right");
	private final Button btnFirst = Button.icon("first_page");
	private final Button btnLast = Button.icon("last_page");
	private final TextField<Double> iptPage = TextField.numberBox().outlined().style("display: inline-block; margin-top: auto; margin-bottom: auto; padding-right: 2px; width: 60px;height: 28px; background-color: #FFFFFF; border: 1px solid #DDD; font-size: 13px !important;").attr("min", "1");
	private final HtmlContentBuilder<HTMLDivElement> _this;
	public Page(HtmlContentBuilder<HTMLDivElement> e) {
		super(e);
		_this = e.add(lblIdxFirst)
				 .add(lblIdxLast)
				 .add(lblTotal)
				 .add(btnFirst)
				 .add(btnPrevious)
				 .add(iptPage)
				 .add(lblPageMax)
				 .add(btnNext)
				 .add(btnLast);
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
	}
	public long total() {
		return total;
	}
	public Page total(long total) {
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
	public Page show(int show) {
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
	public Page idx(long idx) {
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
	@Override
	public Page that() {
		return this;
	}

	@Override
	public Page value() {
		return that();
	}

	private void fire() {
		Event evt;
		try {
			evt = new Event("change");
		} catch(Exception e) {
			evt = new CustomEvent("change");
		}
		ValueChangeEvent<Page> e = ValueChangeEvent.event(evt, this);
		for(ValueChangeEventListener<Page> listener: listeners) listener.handle(e);
	}
	private final Set<ValueChangeEventListener<Page>> listeners = new HashSet<>();
	@Override
	public HandlerRegistration onValueChange(ValueChangeEventListener<Page> listener) {
		listeners.add(listener);
		return ()->listeners.remove(listener);
	}
}
