package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.Alignment;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.Setter;
import stelnet.board.query.provider.QueryProvider;
import stelnet.filter.Filter;
import stelnet.filter.LogicalOr;
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

    public abstract RenderableComponent getPreview(Size size);

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

    protected void addUnlabelledGroup(List<Renderable> elements, List<Renderable> groupElements) {
        elements.add(new HorizontalViewContainer(new DynamicGroup(sizeHelper.getGroupAndTextWidth(), groupElements)));
    }

    protected void addSection(List<Renderable> elements, Enum<?> translationId) {
        float width = sizeHelper.getGroupAndTextWidth();
        addSpacer(elements, 16);
        elements.add(new Paragraph(L10n.get(translationId), width));
        Line line = new Line(width);
        line.setOffset(new Position(0, -6));
        elements.add(line);
        addSpacer(elements, 2);
    }

    protected void addSpacer(List<Renderable> elements, float size) {
        elements.add(new Spacer(size));
    }

    protected void addToFilters(
        Set<Filter> filters,
        FilteringButton buttons[],
        String type,
        boolean defaultToAllFilters
    ) {
        Set<Filter> allFilters = new LinkedHashSet<>();
        Set<Filter> selectedFilters = new LinkedHashSet<>();
        for (FilteringButton button : buttons) {
            allFilters.add(button.getFilter());
            if (button.isStateOn()) {
                selectedFilters.add(button.getFilter());
            }
        }
        if (!selectedFilters.isEmpty()) {
            filters.add(new LogicalOr(selectedFilters, type));
            return;
        }
        if (defaultToAllFilters) {
            filters.add(new LogicalOr(allFilters, type));
        }
    }

    protected abstract Set<Filter> getFilters();

    protected abstract List<Renderable> getQueryBuilder();

    protected abstract QueryProvider getProvider();
}
