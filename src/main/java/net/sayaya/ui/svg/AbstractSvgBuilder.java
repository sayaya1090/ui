package net.sayaya.ui.svg;

import elemental2.dom.DomGlobal;
import elemental2.dom.Event;
import elemental2.dom.HTMLElement;
import org.jboss.elemento.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static java.util.Arrays.asList;
import static org.jboss.elemento.EventType.bind;

public abstract class AbstractSvgBuilder<E extends SVGElement, B extends AbstractSvgBuilder<E, B>> implements TypedBuilder<E, AbstractSvgBuilder<E, B>>, IsSvgElement<E, B> {
    private final static String SVG_NAMESPACE = "http://www.w3.org/2000/svg";
    private final E element;
    protected AbstractSvgBuilder(String tag) {
        this((E)DomGlobal.document.createElementNS(SVG_NAMESPACE, tag));
    }
    private AbstractSvgBuilder(E element) {
        this.element = element;
    }
    @Override
    public E element() {
        return element;
    }
    public B id() {
        return id(Id.unique());
    }
    public B id(String id) {
        element().id = id;
        return that();
    }
    public B css(String... classes) {
        if (classes != null) {
            List<String> failSafeClasses = new ArrayList<>();
            for (String c : classes) {
                if (c != null) {
                    if (c.contains(" ")) {
                        failSafeClasses.addAll(asList(c.split(" ")));
                    } else {
                        failSafeClasses.add(c);
                    }
                }
            }
            for (String failSafeClass : failSafeClasses) {
                element().classList.add(failSafeClass);
            }
        }
        return that();
    }
    public B ncss(String... classes) {
        if(classes == null) return that();
        List<String> failSafeClasses = new ArrayList<>();
        for (String c : classes) if (c != null) {
            if (c.contains(" ")) failSafeClasses.addAll(asList(c.split(" ")));
            else failSafeClasses.add(c);
        }
        for (String failSafeClass : failSafeClasses) element().classList.remove(failSafeClass);
        return that();
    }
    public B clear() {
        E elem = element();
        elem.childNodes.setLength(0);
        return that();
    }
    public B toggle(String className) {
        element().classList.toggle(className);
        return that();
    }
    public B toggle(String className, boolean force) {
        element().classList.toggle(className, force);
        return that();
    }
    public B toggle(String className, Supplier<Boolean> force) {
        element().classList.toggle(className, force.get());
        return that();
    }
    public B attr(String name, String value) {
        if(value!=null) element().setAttribute(name, value);
        else element().removeAttribute(name);
        return that();
    }
    public B add(SVGElement child) {
        element().append(child);
        return that();
    }
    public B add(IsSvgElement child) {
        element().append(child.element());
        return that();
    }
    public B aria(String name, String value) {
        String safeName = name.startsWith("aria-") ? name : "aria-" + name;
        return attr(safeName, value);
    }
    public B apply(Consumer<SVGElement> consumer) {
        consumer.accept(element());
        return that();
    }
    public <V extends Event> B on(EventType<V, ?> type, EventCallbackFn<V> callback) {
        bind(element(), type, callback);
        return that();
    }
    public Iterable<HTMLElement> findAll(By selector) {
        return Elements.findAll(element(), selector);
    }
    public <F extends HTMLElement> F find(By selector) {
        return Elements.find(element(), selector);
    }
    public <F extends HTMLElement> F closest(By selector) {
        return Elements.closest(element(), selector);
    }
}