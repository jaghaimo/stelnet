package stelnet.board.query;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import stelnet.board.query.provider.FactionProvider;
import stelnet.board.query.provider.ItemProvider;
import stelnet.board.query.provider.MarketProvider;
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

    private final QueryManager queryManager;
    private transient QueryBoardTab activeTab;
    private transient AddQueryFactory addQueryFactory;
    private transient QueryListFactory queryListFactory;

    public QueryState() {
        queryManager = new QueryManager();
        readResolve();
    }

    public Object readResolve() {
        activeTab = QueryBoardTab.LIST;
        addQueryFactory = new AddQueryFactory();
        queryListFactory = new QueryListFactory(queryManager);
        return this;
    }

    public void resetCache() {
        FactionProvider.reset();
        ItemProvider.reset();
        MarketProvider.reset();
        PeopleProvider.reset();
        ShipProvider.reset();
        MarketUpdater.reset();
    }

    @Override
    public List<Renderable> toRenderableList(Size size) {
        return new QueryView(this).create(size);
    }
}
