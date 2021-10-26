package uilib;

import java.util.List;
import uilib.property.Size;

public interface RenderableState {
    public List<Renderable> toRenderables(Size size);
}
