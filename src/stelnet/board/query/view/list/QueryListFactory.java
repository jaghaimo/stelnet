package stelnet.board.query.view.list;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.Query;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.QueryManager;
import stelnet.util.L10n;
import uilib.HorizontalViewContainer;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.Spacer;
import uilib.UiConstants;
import uilib.property.Size;

@RequiredArgsConstructor
public class QueryListFactory implements RenderableFactory {

    private final QueryManager manager;

    @Override
    public List<Renderable> create(Size size) {
        float width = size.getWidth();
        List<Renderable> elements = new LinkedList<>();
        for (Query query : manager.getQueries()) {
            elements.add(new QueryRow(width, query));
        }

        boolean enableButtons = manager.getQueries().size() > 0;
        elements.add(new Spacer(UiConstants.DEFAULT_SPACER));
        elements.add(getGlobalButtons(enableButtons, size.getWidth()));

        Collections.reverse(elements);
        addNewQueries(elements, width);
        return elements;
    }

    private void addNewQueries(List<Renderable> elements, float width) {
        if (elements.isEmpty()) {
            elements.add(new Paragraph(L10n.get(QueryL10n.NO_QUERIES), width));
        }
    }

    private void adjustSpacerWidth(List<Renderable> elements, Spacer spacer, float width) {
        float spacerWidth = calculateRemaining(width, elements);
        spacer.setSize(new Size(spacerWidth, 0));
    }

    private float calculateRemaining(float width, List<Renderable> elements) {
        float total = 0;
        for (Renderable element : elements) {
            total += element.getSize().getWidth();
        }
        return Math.max(0, width - total);
    }

    private Renderable getGlobalButtons(boolean enableButtons, float width) {
        Spacer spacer = new Spacer(0);
        List<Renderable> elements = new LinkedList<>();
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
