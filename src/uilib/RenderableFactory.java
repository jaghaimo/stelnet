package uilib;

import java.util.List;
import uilib.property.Size;

public interface RenderableFactory {
    public List<Renderable> create(Size size);
}
