package stelnet.market;

import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import stelnet.RenderableState;
import stelnet.market.view.QueryTabViewFactory;
import uilib.Renderable;
import uilib.property.Size;

@Getter
@Setter
public class QueryState implements RenderableState {

    private QueryTab activeTab = QueryTab.LIST;

    @Override
    public List<Renderable> toRenderables(Size size) {
        return Collections.singletonList(new QueryTabViewFactory(this).createContainer(size));
    }
}
