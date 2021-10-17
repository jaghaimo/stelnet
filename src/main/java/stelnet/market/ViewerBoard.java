package stelnet.market;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;

import lombok.Getter;
import lombok.Setter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.market.data.MarketProvider;
import stelnet.market.view.MarketSelectButton;
import stelnet.market.view.ViewerTabViewFactory;
import stelnet.storage.ButtonManager;
import stelnet.storage.FilterManager;
import stelnet.storage.StorageTab;
import stelnet.util.IntelManager;
import stelnet.util.L10n;
import stelnet.util.Settings;
import stelnet.util.Tagger;
import uilib.Renderable;
import uilib.property.Size;

@Getter
@Setter
public class ViewerBoard extends BaseBoard {

    private final FilterManager filterManager = new FilterManager();
    private final ButtonManager buttonManager = new ButtonManager(filterManager);
    private StorageTab activeTab = StorageTab.ITEMS;
    private MarketProvider marketProvider = new MarketProvider(null);

    public static ViewerBoard getInstance() {
        IntelInfoPlugin intel = IntelManager.getFirstIntel(ViewerBoard.class);
        if (intel == null) {
            ViewerBoard board = new ViewerBoard();
            IntelManager.addIntel(board, true);
        }
        return (ViewerBoard) intel;
    }

    @Override
    public String getIcon() {
        return Settings.getSpriteName("viewer");
    }

    @Override
    public IntelSortTier getSortTier() {
        return IntelSortTier.TIER_1;
    }

    @Override
    protected BoardInfo getBoardInfo() {
        return new BoardInfo(L10n.get("marketViewTitle"), L10n.get("marketViewDescription"));
    }

    @Override
    protected List<Renderable> getRenderables(Size size) {
        return Arrays.<Renderable>asList(
                new ViewerTabViewFactory(buttonManager, filterManager, activeTab, marketProvider).createContainer(size),
                new MarketSelectButton());
    }

    @Override
    protected String getTag() {
        return Tagger.MARKET;
    }
}
