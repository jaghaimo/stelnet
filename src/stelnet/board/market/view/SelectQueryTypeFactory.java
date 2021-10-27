package stelnet.board.market.view;

import com.fs.starfarer.api.ui.Alignment;
import java.util.Arrays;
import java.util.List;
import uilib.HorizontalViewContainer;
import uilib.Paragraph;
import uilib.property.Size;

public class SelectQueryTypeFactory implements HorizontalViewFactory {

    @Override
    public List<HorizontalViewContainer> getAll(Size size) {
        return Arrays.asList(
            new HorizontalViewContainer(
                new Paragraph("Select a type of query you would like to perform.", size.getWidth(), 10, Alignment.MID)
            )
        );
    }
}
