package stelnet.board.query;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import stelnet.board.query.view.add.AddQueryFactory;
import uilib.Renderable;
import uilib.RenderableState;
import uilib.property.Size;

@Getter
@Setter
public class QueryState implements RenderableState {

    public static enum QueryBoardTab {
        LIST("List"),
        NEW("New");

        public final String id;

        private QueryBoardTab(String id) {
            this.id = id;
        }
    }

    private QueryBoardTab activeTab = QueryBoardTab.LIST;
    private final AddQueryFactory queryTypeFactory = new AddQueryFactory();

    @Override
    public List<Renderable> toRenderables(Size size) {
        return new QueryView(this).create(size);
    }
}
