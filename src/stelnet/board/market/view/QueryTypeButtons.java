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
public class QueryTypeButtons implements ViewContainerFactory {

    private final QueryTypeButton[] buttons;
    private final HorizontalViewFactory defaultFactory;

    public QueryTypeButtons() {
        HorizontalViewFactory emptyFactory = new EmptyHorizontalViewFactory();
        buttons =
            new QueryTypeButton[] {
                new QueryTypeButton(this, "Personnel", new PersonnelButtons()),
                new QueryTypeButton(this, "Weapons", emptyFactory),
                new QueryTypeButton(this, "Fighters", emptyFactory),
                new QueryTypeButton(this, "Modspecs", emptyFactory),
                new QueryTypeButton(this, "Ships", emptyFactory),
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
