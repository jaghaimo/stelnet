package stelnet.board.query.view.list;

import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.Query;
import stelnet.board.query.QueryState;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.property.Size;

@RequiredArgsConstructor
public class QueryListFactory implements RenderableFactory {

    private final QueryState queryState;

    @Override
    public List<Renderable> create(Size size) {
        List<Renderable> renderables = new LinkedList<>();
        for (Query query : queryState.getQueryManger().getQueries()) {
            renderables.add(new QueryRow(size.getWidth(), query));
        }
        // renderables.add(new DeleteAllButton());
        return renderables;
    }
}
