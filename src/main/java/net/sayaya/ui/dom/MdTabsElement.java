package net.sayaya.ui.dom;

import elemental2.dom.HTMLElement;
import elemental2.promise.Promise;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "mdc", name="MdTabs")
public class MdTabsElement extends HTMLElement {
    public HTMLElement[] tabs;
    public Object activeTab;
    public boolean autoActivate;
    public int activeTabIndex;

    public native Promise<Void> scrollToTab(int index);
}
