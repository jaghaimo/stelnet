package stelnet.board.query;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import stelnet.board.query.provider.FactionProvider;
import stelnet.board.query.provider.ItemProvider;
import stelnet.board.query.provider.PeopleProvider;
import stelnet.board.query.provider.ShipProvider;
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
    private final QueryManager queryManager = new QueryManager();
    private transient AddQueryFactory addQueryFactory = new AddQueryFactory();
    private transient QueryListFactory queryListFactory = new QueryListFactory(queryManager);

    public Object readResolve() {
        addQueryFactory = new AddQueryFactory();
        queryListFactory = new QueryListFactory(queryManager);
        return this;
    }

    @Override
    public List<Renderable> toRenderableList(Size size) {
        return new QueryView(this).create(size);
    }

    public void resetCache() {
        MarketUpdater.reset();
        FactionProvider.reset();
        ItemProvider.reset();
        PeopleProvider.reset();
        ShipProvider.reset();
    }
}
