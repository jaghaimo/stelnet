package stelnet;

import java.util.List;
import uilib.Renderable;
import uilib.property.Size;

public interface RenderableState {
    public List<Renderable> toRenderables(Size size);
}
