package stelnet.board.query;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import stelnet.board.query.view.add.AddQueryFactory;
import stelnet.board.query.view.list.QueryListFactory;
import uilib.Renderable;
import uilib.RenderableState;
import uilib.property.Size;

@Getter
@Setter
public class QueryState implements RenderableState, Serializable {

    public static enum QueryBoardTab {
        LIST,
        NEW,
    }

    private QueryBoardTab activeTab = QueryBoardTab.LIST;
    private final AddQueryFactory addQueryFactory = new AddQueryFactory();
    private final QueryListFactory queryListFactory = new QueryListFactory(this);
    private final QueryManager queryManger = new QueryManager();

    @Override
    public List<Renderable> toRenderables(Size size) {
        return new QueryView(this).create(size);
    }
}
