package stelnet.board.query.view.list;

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
import uilib.RenderableComponent;
import uilib.RenderableFactory;
import uilib.Spacer;
import uilib.property.Location;
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
        if (elements.isEmpty()) {
            elements.add(new Spacer(7));
            elements.add(new Paragraph(L10n.get(QueryL10n.NO_QUERIES), width));
        } else {
            elements.add(0, getGlobalButtons());
        }
        return elements;
    }

    public RenderableComponent getPreview(Size size) {
        Query activeQuery = manager.getActiveQuery();
        RenderableComponent preview = getPreviewComponent(size, activeQuery);
        preview.setLocation(Location.TOP_RIGHT);
        return preview;
    }

    private Renderable getGlobalButtons() {
        List<Renderable> elements = new LinkedList<>();
        return new HorizontalViewContainer(elements);
    }

    private RenderableComponent getPreviewComponent(Size size, Query activeQuery) {
        if (activeQuery != null) {
            return activeQuery.getPreview(size);
        }
        return new Spacer(0);
    }
}
