package uilib;

import java.util.Arrays;
import java.util.List;
import uilib.property.Size;

/**
 * Dummy factory that returns empty list of renderables.
 */
public class DummyRenderableFactory implements RenderableFactory {

    @Override
    public List<Renderable> create(Size size) {
        return Arrays.asList();
    }
}
