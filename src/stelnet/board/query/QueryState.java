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
        LIST("List"),
        NEW("New");

        public final String id;

        private QueryBoardTab(String id) {
            this.id = id;
        }
    }

    private QueryBoardTab activeTab = QueryBoardTab.LIST;
    private AddQueryFactory addQueryFactory = new AddQueryFactory();
    private QueryListFactory queryListFactory = new QueryListFactory();

    @Override
    public List<Renderable> toRenderables(Size size) {
        return new QueryView(this).create(size);
    }
}