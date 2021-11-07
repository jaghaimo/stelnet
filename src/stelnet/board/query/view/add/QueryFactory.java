package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.Alignment;
import java.util.LinkedList;
import java.util.List;
import lombok.Setter;
import stelnet.filter.Filter;
import stelnet.util.L10n;
import uilib.DynamicGroup;
import uilib.HorizontalViewContainer;
import uilib.Line;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.Spacer;
import uilib.property.Position;
import uilib.property.Size;

public abstract class QueryFactory {

    @Setter
    protected SizeHelper sizeHelper = new SizeHelper();

    protected void addLabeledGroup(List<Renderable> elements, Enum<?> label, List<Renderable> groupElements) {
        String labelText = "";
        if (label != null) {
            labelText = L10n.get(label);
        }
        elements.add(
            new HorizontalViewContainer(
                new Paragraph(labelText, sizeHelper.getTextWidth(), 4, Alignment.RMID),
                new DynamicGroup(sizeHelper.getGroupWidth(), groupElements)
            )
        );
    }

    protected void addSpacer(List<Renderable> elements, float size) {
        elements.add(new Spacer(size));
    }

    protected void beginSection(List<Renderable> elements, Enum<?> translationId) {
        float width = sizeHelper.getGroupAndTextWidth();
        addSpacer(elements, 10);
        elements.add(new Paragraph(L10n.get(translationId), width));
        Line line = new Line(width);
        line.setOffset(new Position(0, -6));
        elements.add(line);
        addSpacer(elements, 2);
    }

    protected void endSection(List<Renderable> elements) {
        Line line = new Line(sizeHelper.getGroupAndTextWidth());
        elements.add(line);
    }

    protected List<Filter> getFilters(FilteringButton buttons[]) {
        List<Filter> allFilters = new LinkedList<>();
        List<Filter> selectedFilters = new LinkedList<>();
        for (FilteringButton button : buttons) {
            allFilters.add(button.getFilter());
            if (button.isStateOn()) {
                selectedFilters.add(button.getFilter());
            }
        }
        return selectedFilters.isEmpty() ? allFilters : selectedFilters;
    }

    protected abstract List<Renderable> getQueryBuilder();

    protected abstract RenderableComponent getPreview(Size size);
}
