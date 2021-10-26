package uilib;

import uilib.property.Size;

public interface ViewContainerFactory {
    public Renderable createContainer(Size size);
}
