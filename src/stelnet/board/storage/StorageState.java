package stelnet.board.storage;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import stelnet.widget.viewer.ButtonManager;
import stelnet.widget.viewer.ContentRenderer;
import stelnet.widget.viewer.GroupingStrategy;
import stelnet.widget.viewer.MarketViewState;
import uilib.Renderable;
import uilib.RenderableState;
import uilib.property.Size;

@Getter
@Setter
public class StorageState implements RenderableState, MarketViewState {

    private transient ButtonManager buttonManager;
    private transient ContentRenderer contentRenderer;
    private transient GroupingStrategy displayStrategy;

    public StorageState() {
        readResolve();
    }

    @Override
    public List<Renderable> toRenderableList(final Size size) {
        return new StorageView(this).create(size);
    }

    public Object readResolve() {
        buttonManager = new ButtonManager();
        contentRenderer = ContentRenderer.ITEMS;
        displayStrategy = GroupingStrategy.UNIFIED;
        return this;
    }
}
