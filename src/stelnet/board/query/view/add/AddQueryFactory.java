package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.util.Misc;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import stelnet.CommonL10n;
import uilib.Button;
import uilib.DynamicGroup;
import uilib.HorizontalViewContainer;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.property.Position;
import uilib.property.Size;

@Getter
public class AddQueryFactory extends PreviewableQueryFactory {

    private final QueryTypeButton[] buttons;

    public AddQueryFactory() {
        buttons =
            new QueryTypeButton[] {
                new QueryTypeButton(this, CommonL10n.PERSONNEL, new PersonnelQueryFactory()),
                new QueryTypeButton(this, CommonL10n.WEAPONS, new WeaponQueryFactory()),
                new QueryTypeButton(this, CommonL10n.FIGHTER_WINGS, new FighterQueryFactory()),
                new QueryTypeButton(this, CommonL10n.MODSPECS, new ModspecQueryFactory()),
                new QueryTypeButton(this, CommonL10n.SHIPS, new ShipQueryFactory()),
            };
        buttons[0].setStateOn(true);
    }

    @Override
    public List<Renderable> create(Size size) {
        initSizeHelper(size);
        List<Renderable> elements = new LinkedList<>();
        addQueryTypes(elements);
        addFromNextFactory(elements, size.reduce(new Size(0, FIRST_ROW_HEIGHT)));
        return elements;
    }

    public void setQueryType(QueryTypeButton active) {
        for (QueryTypeButton button : buttons) {
            button.setStateOn(active.equals(button));
        }
    }

    private void addQueryTypes(List<Renderable> elements) {
        Paragraph label = new Paragraph("Type of query", sizeHelper.getTextWidth(), 4, Alignment.RMID);
        DynamicGroup group = new DynamicGroup(sizeHelper.getGroupWidth(), buttons);
        FIRST_ROW_HEIGHT = group.getSize().getHeight();
        elements.add(new HorizontalViewContainer(label, group));
    }

    private void addFromNextFactory(List<Renderable> elements, Size size) {
        RenderableFactory nextFactory = getNextFactory();
        if (nextFactory == null) {
            setQueryType(buttons[0]);
            nextFactory = getNextFactory();
        }
        elements.addAll(nextFactory.create(size));
        addQueryButtons(elements);
    }

    private void addQueryButtons(List<Renderable> elements) {
        final float BUTTON_HEIGHT = 30;
        Size buttonSize = new Size(0, BUTTON_HEIGHT);
        Paragraph label = new Paragraph("", sizeHelper.getTextWidth());
        Button search = new Button(buttonSize, "Search", true, Misc.getButtonTextColor());
        Button selectAndSearch = new Button(buttonSize, "Select and Search", true, Misc.getButtonTextColor());
        HorizontalViewContainer horizontalViewContainer = new HorizontalViewContainer(label, search, selectAndSearch);
        horizontalViewContainer.setSize(new Size(sizeHelper.getGroupWidth(), BUTTON_HEIGHT));
        horizontalViewContainer.setOffset(new Position(0, -PreviewableQueryFactory.FIRST_ROW_HEIGHT));
        elements.add(horizontalViewContainer);
    }

    private RenderableFactory getNextFactory() {
        for (QueryTypeButton button : buttons) {
            if (button.isStateOn()) {
                return button.getNextFactory();
            }
        }
        return null;
    }
}
