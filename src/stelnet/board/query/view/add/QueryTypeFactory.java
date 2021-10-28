package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.Alignment;
import java.util.Collections;
import java.util.List;
import uilib.HorizontalViewContainer;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.property.Size;

public class QueryTypeFactory implements RenderableFactory {

    @Override
    public List<Renderable> create(Size size) {
        return Collections.<Renderable>singletonList(
            new HorizontalViewContainer(
                new Paragraph("Select a type of query you would like to perform.", size.getWidth(), 10, Alignment.MID)
            )
        );
    }
}
