package stelnet.board.query;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import stelnet.board.query.provider.ItemQueryProvider;
import stelnet.board.query.provider.MarketProvider;
import stelnet.board.query.provider.PeopleQueryProvider;
import stelnet.board.query.provider.ShipQueryProvider;
import stelnet.board.query.view.add.AddQueryFactory;
import stelnet.board.query.view.list.QueryListFactory;
import stelnet.board.query.view.manage.ManageResultsFactory;
import uilib.Renderable;
import uilib.RenderableState;
import uilib.property.Size;

@Getter
@Setter
public class QueryState implements RenderableState, Serializable {

    public static enum QueryBoardTab {
        LIST,
        NEW,
        MANAGE,
    }

    private final QueryManager queryManager;
    private transient QueryBoardTab activeTab;
    private transient AddQueryFactory addQueryFactory;
    private transient QueryListFactory queryListFactory;
    private transient ManageResultsFactory manageResultsFactory;

    public QueryState() {
        queryManager = new QueryManager();
        readResolve();
    }

    public Object readResolve() {
        activeTab = QueryBoardTab.LIST;
        addQueryFactory = new AddQueryFactory();
        queryListFactory = new QueryListFactory(queryManager);
        manageResultsFactory = new ManageResultsFactory(queryManager);
        return this;
    }

    public void resetCache() {
        ItemQueryProvider.resetCache();
        PeopleQueryProvider.resetCache();
        ShipQueryProvider.resetCache();
        MarketProvider.reset();
    }

    @Override
    public List<Renderable> toRenderableList(Size size) {
        return new QueryView(this).create(size);
    }
}
