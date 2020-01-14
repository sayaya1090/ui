package net.sayaya.ui;

import com.google.gwt.core.client.EntryPoint;
import org.jboss.gwt.elemento.core.Elements;

public class Test implements EntryPoint {
	@Override
	public void onModuleLoad() {
		Elements.body().add(new MsgBox());
	}
}
