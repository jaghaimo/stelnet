package stelnet.board.market.view;

import java.util.Arrays;
import java.util.List;
import uilib.HorizontalViewContainer;
import uilib.property.Size;

public class EmptyHorizontalViewFactory implements HorizontalViewFactory {

    @Override
    public List<HorizontalViewContainer> getAll(Size size) {
        return Arrays.asList();
    }
}
