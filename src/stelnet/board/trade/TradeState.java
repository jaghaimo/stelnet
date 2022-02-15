package stelnet.board.trade;

import java.util.Collections;
import java.util.List;
import uilib.Renderable;
import uilib.RenderableState;
import uilib.property.Size;

public class TradeState implements RenderableState {

    @Override
    public List<Renderable> toRenderableList(Size size) {
        return Collections.emptyList();
    }
}
