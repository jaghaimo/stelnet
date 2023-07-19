package stelnet.board.query.view.list;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.Query;
import stelnet.board.query.QueryManager;
import stelnet.util.L10n;
import uilib.*;
import uilib.property.Size;

@RequiredArgsConstructor
public class QueryListFactory implements RenderableFactory {

    private final QueryManager manager;

    @Override
    public List<Renderable> create(final Size size) {
        final float width = size.getWidth();
        final List<Renderable> elements = new LinkedList<>();
        for (final Query query : manager.getQueries()) {
            elements.add(new QueryRow(width, query));
        }

        final boolean enableButtons = manager.getQueries().size() > 0;
        elements.add(new Spacer(UiConstants.DEFAULT_SPACER));
        elements.add(getGlobalButtons(enableButtons, size.getWidth()));

        Collections.reverse(elements);
        addNewQueries(elements, width);
        return elements;
    }

    private void addNewQueries(final List<Renderable> elements, final float width) {
        if (elements.isEmpty()) {
            elements.add(new Paragraph(L10n.query("NO_QUERIES"), width));
        }
    }

    private void adjustSpacerWidth(final List<Renderable> elements, final Spacer spacer, final float width) {
        final float spacerWidth = calculateRemaining(width, elements);
        spacer.setSize(new Size(spacerWidth, 0));
    }

    private float calculateRemaining(final float width, final List<Renderable> elements) {
        float total = 0;
        for (final Renderable element : elements) {
            total += element.getSize().getWidth();
        }
        return Math.max(0, width - total);
    }

    private Renderable getGlobalButtons(final boolean enableButtons, final float width) {
        final Spacer spacer = new Spacer(0);
        final List<Renderable> elements = new LinkedList<>();
        elements.add(new RefreshAllButton(manager, enableButtons, 0));
        elements.add(new Spacer(UiConstants.DEFAULT_SPACER));
        elements.add(new EnableAllButton(manager, enableButtons, 0));
        elements.add(new Spacer(UiConstants.DEFAULT_SPACER));
        elements.add(new DisableAllButton(manager, enableButtons, 0));
        elements.add(spacer);
        elements.add(new DeleteAllButton(manager, enableButtons, 0));
        adjustSpacerWidth(elements, spacer, width);
        return new HorizontalViewContainer(elements);
    }
}
