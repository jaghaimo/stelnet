package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.Alignment;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import uilib.DynamicGroup;
import uilib.HorizontalViewContainer;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.property.Size;

@Getter
public class AddQueryFactory implements RenderableFactory {

    private final QueryTypeButton[] buttons;

    public AddQueryFactory() {
        buttons =
            new QueryTypeButton[] {
                new QueryTypeButton(this, "Personnel", new PersonnelQueryFactory()),
                new QueryTypeButton(this, "Weapons", new WeaponQueryFactory()),
                new QueryTypeButton(this, "Fighters", new FighterQueryFactory()),
                new QueryTypeButton(this, "Modspecs", new ModspecQueryFactory()),
                new QueryTypeButton(this, "Ships", new ShipQueryFactory()),
            };
    }

    @Override
    public List<Renderable> create(Size size) {
        float width = size.getWidth();
        float textWidth = Math.min(width / 4, 200);
        float groupWidth = width - textWidth;
        List<Renderable> elements = new LinkedList<>();
        elements.add(
            new HorizontalViewContainer(
                new Paragraph("Type of query", textWidth, 4, Alignment.RMID),
                new DynamicGroup(groupWidth, buttons)
            )
        );
        Size currentSize = elements.get(0).getSize();
        elements.addAll(nextFactory().create(size.reduce(new Size(0, currentSize.getHeight()))));
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
        return new RenderableFactory() {
            @Override
            public List<Renderable> create(Size size) {
                return Collections.<Renderable>singletonList(
                    new HorizontalViewContainer(
                        new Paragraph(
                            "Select a type of query you would like to perform.",
                            size.getWidth(),
                            10,
                            Alignment.MID
                        )
                    )
                );
            }
        };
    }
}
