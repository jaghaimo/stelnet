package stelnet.board.market.view;

import com.fs.starfarer.api.ui.Alignment;
import java.util.LinkedList;
import java.util.List;
import stelnet.filter.market.person.IsPostedAs;
import uilib.AreaCheckbox;
import uilib.DynamicGroup;
import uilib.HorizontalViewContainer;
import uilib.Paragraph;
import uilib.property.Size;

public class PersonnelButtons implements HorizontalViewFactory {

    private final AreaCheckbox[] buttons;

    public PersonnelButtons() {
        buttons =
            new AreaCheckbox[] {
                new PersonnelButton(this, "Administrator", IsPostedAs.admin()),
                new PersonnelButton(this, "Officer", IsPostedAs.officer()),
                new PersonnelButton(this, "Mercenary", IsPostedAs.mercenary()),
                new PersonnelButton(this, "Agent", IsPostedAs.agent()),
            };
    }

    @Override
    public List<HorizontalViewContainer> getAll(Size size) {
        float width = size.getWidth();
        float textWidth = Math.max(width / 4, 200);
        float groupWidth = width - textWidth;
        List<HorizontalViewContainer> containers = new LinkedList<>();
        addPositions(containers, textWidth, groupWidth);
        return containers;
    }

    private void addPositions(List<HorizontalViewContainer> containers, float textWidth, float groupWidth) {
        containers.add(
            new HorizontalViewContainer(
                new Paragraph("What type of personnel are you after?", textWidth, 4, Alignment.RMID),
                new DynamicGroup(groupWidth, buttons)
            )
        );
    }
}
