package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.Setter;
import stelnet.board.query.provider.QueryProvider;
import stelnet.filter.Filter;
import stelnet.filter.LogicalOr;
import stelnet.util.L10n;
import stelnet.util.SettingsUtils;
import uilib.Button;
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

    protected void addLabeledGroup(List<Renderable> elements, Enum<?> label, Button[] buttons, boolean isEnabled) {
        String labelText = "";
        if (label != null) {
            labelText = L10n.get(label);
        }
        Paragraph title = new Paragraph(labelText, sizeHelper.getTextWidth(), 4, Alignment.RMID);
        if (!isEnabled) {
            title.setColor(Misc.getGrayColor());
        }
        prepareButtons(buttons, isEnabled);
        elements.add(new HorizontalViewContainer(title, new DynamicGroup(sizeHelper.getGroupWidth(), buttons)));
    }

    protected void addUnlabelledGroup(List<Renderable> elements, Button[] buttons, boolean isEnabled) {
        prepareButtons(buttons, isEnabled);
        elements.add(new HorizontalViewContainer(new DynamicGroup(sizeHelper.getGroupAndTextWidth(), buttons)));
    }

    protected void addSection(List<Renderable> elements, Enum<?> translationId, boolean isEnabled) {
        float width = sizeHelper.getGroupAndTextWidth();
        addSpacer(elements, 16);
        Paragraph paragraph = new Paragraph(L10n.get(translationId), width);
        Color lineColor = SettingsUtils.getButtonHighlightColor();
        if (!isEnabled) {
            paragraph.setColor(Misc.getGrayColor());
            lineColor = Misc.getDarkPlayerColor();
        }
        Line line = new Line(width, lineColor);
        line.setOffset(new Position(0, -3));
        line.setPadding(0);
        elements.add(paragraph);
        elements.add(line);
        addSpacer(elements, 6);
    }

    protected void addSpacer(List<Renderable> elements, float size) {
        elements.add(new Spacer(size));
    }

    protected void addToFilters(Set<Filter> filters, FilteringButton buttons[], String type, boolean isEnabled) {
        if (!isEnabled) {
            return;
        }
        Set<Filter> selectedFilters = new LinkedHashSet<>();
        for (FilteringButton button : buttons) {
            Filter filter = button.getFilter();
            if (button.isStateOn()) {
                selectedFilters.add(filter);
            }
        }
        filters.add(new LogicalOr(selectedFilters, type));
    }

    private void prepareButtons(Button[] buttons, boolean isEnabled) {
        for (Button button : buttons) {
            button.setEnabled(isEnabled);
        }
    }

    public abstract Set<Filter> getFilters();

    public abstract RenderableComponent getPreview(Set<Filter> filters, Size size);

    public abstract QueryProvider getProvider();

    protected abstract List<Renderable> getQueryBuildingComponents();

    protected abstract Button[] getFinalComponents();
}
