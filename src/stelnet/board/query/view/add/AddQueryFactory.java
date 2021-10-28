package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.Alignment;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import uilib.DummyRenderableFactory;
import uilib.DynamicGroup;
import uilib.HorizontalViewContainer;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.property.Size;

@Getter
public class AddQueryFactory implements RenderableFactory {

    private final QueryTypeButton[] buttons;
    private final RenderableFactory defaultFactory;

    public AddQueryFactory() {
        RenderableFactory emptyFactory = new DummyRenderableFactory();
        buttons =
            new QueryTypeButton[] {
                new QueryTypeButton(this, "Personnel", new PersonnelButtonFactory()),
                new QueryTypeButton(this, "Weapons", emptyFactory),
                new QueryTypeButton(this, "Fighters", emptyFactory),
                new QueryTypeButton(this, "Modspecs", emptyFactory),
                new QueryTypeButton(this, "Ships", emptyFactory),
            };
        defaultFactory = new QueryTypeFactory();
    }

    @Override
    public List<Renderable> create(Size size) {
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
        elements.addAll(nextFactory().create(size));
        return elements;
    }

    public void setQueryType(QueryTypeButton active) {
        for (QueryTypeButton button : buttons) {
            if (!active.equals(button)) {
                button.setStateOn(false);
            }
        }
    }

    private RenderableFactory nextFactory() {
        for (QueryTypeButton button : buttons) {
            if (button.isStateOn()) {
                return button.getNextFactory();
            }
        }
        return defaultFactory;
    }
}
