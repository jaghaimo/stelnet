package stelnet.board.market.view;

import com.fs.starfarer.api.ui.Alignment;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import uilib.DynamicGroup;
import uilib.HorizontalViewContainer;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.VerticalViewContainer;
import uilib.ViewContainerFactory;
import uilib.property.Size;

@Getter
public class QueryTypeFactory implements ViewContainerFactory {

    private final QueryTypeButton[] buttons;
    private final HorizontalViewFactory defaultFactory;

    public QueryTypeFactory() {
        buttons =
            new QueryTypeButton[] {
                new QueryTypeButton(this, "Personnel"),
                new QueryTypeButton(this, "Weapons"),
                new QueryTypeButton(this, "Fighters"),
                new QueryTypeButton(this, "Modspecs"),
                new QueryTypeButton(this, "Ships"),
            };
        defaultFactory = new SelectQueryTypeFactory();
    }

    public void setStateOn(QueryTypeButton active) {
        for (QueryTypeButton button : buttons) {
            button.setStateOn(false);
            if (active.equals(button)) {
                button.setStateOn(true);
            }
        }
    }

    private HorizontalViewFactory nextFactory() {
        for (QueryTypeButton button : buttons) {
            if (button.isStateOn()) {
                return button.getNextFactory();
            }
        }
        return defaultFactory;
    }

    @Override
    public Renderable createContainer(Size size) {
        float width = size.getWidth();
        float textWidth = Math.max(width / 4, 200);
        float groupWidth = width - textWidth;
        List<Renderable> elements = new LinkedList<>();
        elements.add(
            new HorizontalViewContainer(
                new Paragraph("What would you like to search for?", textWidth, 4, Alignment.RMID),
                new DynamicGroup(groupWidth, buttons)
            )
        );
        elements.addAll(nextFactory().getAll(size));
        return new VerticalViewContainer(elements);
    }
}
