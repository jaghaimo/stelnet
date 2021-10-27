package stelnet.board.market;

import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import stelnet.board.market.view.QueryTabViewFactory;
import stelnet.board.market.view.QueryTypeButtons;
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
    private final QueryTypeButtons queryTypeFactory = new QueryTypeButtons();

    @Override
    public List<Renderable> toRenderables(Size size) {
        return Collections.singletonList(new QueryTabViewFactory(this).createContainer(size));
    }
}
