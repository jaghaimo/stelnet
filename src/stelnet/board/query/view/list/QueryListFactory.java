package stelnet.board.query.view.list;

import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.Query;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.QueryManager;
import stelnet.util.L10n;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.RenderableFactory;
import uilib.property.Location;
import uilib.property.Size;

@RequiredArgsConstructor
public class QueryListFactory implements RenderableFactory {

    private final QueryManager manager;

    @Override
    public List<Renderable> create(Size size) {
        List<Renderable> renderables = new LinkedList<>();
        for (Query query : manager.getQueries()) {
            renderables.add(new QueryRow(size.getWidth(), query));
        }
        return renderables;
    }

    public RenderableComponent getPreview(Size size) {
        Query activeQuery = manager.getActiveQuery();
        RenderableComponent preview = getPreviewComponent(size, activeQuery);
        preview.setLocation(Location.TOP_RIGHT);
        return preview;
    }

    private RenderableComponent getPreviewComponent(Size size, Query activeQuery) {
        if (activeQuery != null) {
            return activeQuery.getProvider().getPreview(size);
        }
        return new Paragraph(L10n.get(QueryL10n.NO_QUERY_SELECTED), size.getWidth());
    }
}
