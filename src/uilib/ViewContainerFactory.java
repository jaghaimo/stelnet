package uilib;

import uilib.property.Size;

public interface ViewContainerFactory {
    public Renderable create(Size size);
}
