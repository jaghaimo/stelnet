package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.util.Misc;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import uilib.Button;
import uilib.DynamicGroup;
import uilib.HorizontalViewContainer;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.property.Position;
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
        addFromNextFactory(elements, size.reduce(new Size(0, currentSize.getHeight() - 24)));
        return elements;
    }

    public void setQueryType(QueryTypeButton active) {
        for (QueryTypeButton button : buttons) {
            if (!active.equals(button)) {
                button.setStateOn(false);
            }
        }
    }

    private void addFromNextFactory(List<Renderable> elements, Size size) {
        for (QueryTypeButton button : buttons) {
            if (button.isStateOn()) {
                elements.addAll(button.getNextFactory().create(size));
                addQueryButtons(elements);
                return;
            }
        }
        addInitial(elements, size);
    }

    private void addInitial(List<Renderable> elements, Size size) {
        elements.add(
            new HorizontalViewContainer(
                new Paragraph("Select a type of query you would like to perform.", size.getWidth(), 10, Alignment.MID)
            )
        );
    }

    private void addQueryButtons(List<Renderable> elements) {
        Size buttonSize = new Size(0, QueryTypeFactory.FIRST_ROW_HEIGHT);
        Button search = new Button(buttonSize, "Search", true, Misc.getButtonTextColor());
        Button selectAndSearch = new Button(buttonSize, "Select and Search", true, Misc.getButtonTextColor());
        search.setOffset(new Position(0, -QueryTypeFactory.FIRST_ROW_HEIGHT));
        selectAndSearch.setOffset(new Position(0, -QueryTypeFactory.FIRST_ROW_HEIGHT));
        HorizontalViewContainer horizontalViewContainer = new HorizontalViewContainer(search, selectAndSearch);
        horizontalViewContainer.setSize(new Size(400, QueryTypeFactory.FIRST_ROW_HEIGHT));
        horizontalViewContainer.setOffset(new Position(0, -QueryTypeFactory.FIRST_ROW_HEIGHT));
        elements.add(horizontalViewContainer);
    }
}
