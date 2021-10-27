package stelnet.board.market.view;

import java.util.List;
import uilib.HorizontalViewContainer;
import uilib.property.Size;

public interface HorizontalViewFactory {
    public List<HorizontalViewContainer> getAll(Size size);
}
