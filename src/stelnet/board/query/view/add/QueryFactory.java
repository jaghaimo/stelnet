package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.util.Misc;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.Setter;
import stelnet.board.query.provider.QueryProvider;
import stelnet.filter.Filter;
import stelnet.filter.LogicalOr;
import stelnet.util.L10n;
import uilib.Button;
import uilib.Heading;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.Spacer;
import uilib.UiConstants;
import uilib.property.Size;

public abstract class QueryFactory {

    @Setter
    protected SizeHelper sizeHelper = new SizeHelper();

    protected void addSection(List<Renderable> elements, Enum<?> translationId, boolean isEnabled) {
        addSpacer(elements, UiConstants.DEFAULT_SPACER * 2);
        Heading heading = new Heading(
            " " + getLabelText(translationId),
            Misc.getBasePlayerColor(),
            Misc.getDarkPlayerColor()
        );
        heading.setAlignment(Alignment.LMID);
        heading.setSize(new Size(sizeHelper.getGroupAndTextWidth(), UiConstants.DEFAULT_ROW_HEIGHT));
        if (!isEnabled) {
            heading.setForegroundColor(Misc.scaleAlpha(Misc.getBasePlayerColor(), 0.3f));
            heading.setBackgroundColor(Misc.scaleAlpha(Misc.getDarkPlayerColor(), 0.2f));
        }
        elements.add(heading);
        addSpacer(elements, UiConstants.DEFAULT_SPACER);
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
            if (button.isEnabled() && button.isStateOn()) {
                selectedFilters.add(button.getFilter());
            }
        }
        filters.add(new LogicalOr(selectedFilters, type));
    }

    private String getLabelText(Enum<?> label) {
        if (label != null) {
            return L10n.get(label);
        }
        return "";
    }

    public abstract Set<Filter> getFilters(boolean forResults);

    public abstract RenderableComponent getPreview(Set<Filter> filters, Size size);

    public abstract QueryProvider getProvider();

    protected abstract List<Renderable> getQueryBuildingComponents();

    protected abstract Button[] getFinalComponents();
}
