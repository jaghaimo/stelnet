package stelnet.market;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.L10n;
import stelnet.helper.GlobalSettingsHelper;
import stelnet.helper.IntelHelper;
import stelnet.storage.ButtonManager;
import stelnet.storage.FilterManager;
import stelnet.storage.StorageTab;
import stelnet.storage.view.TabViewFactory;
import stelnet.ui.Renderable;
import stelnet.ui.property.Size;

public class MarketViewBoard extends BaseBoard {

    private final ButtonManager buttonManager = new ButtonManager();
    private final FilterManager filterManager = new FilterManager();
    private StorageTab activeTab = StorageTab.ITEMS;
    private MarketProvider marketProvider;

    public static MarketViewBoard getInstance() {
        IntelInfoPlugin intel = IntelHelper.getFirstIntel(MarketViewBoard.class);
        if (intel == null) {
            MarketViewBoard board = new MarketViewBoard();
            IntelHelper.addIntel(board, true);
        }
        return (MarketViewBoard) intel;
    }

    @Override
    public String getIcon() {
        return GlobalSettingsHelper.getSpriteName("viewer");
    }

    @Override
    public IntelSortTier getSortTier() {
        return IntelSortTier.TIER_1;
    }

    public void setMarket(MarketAPI market) {
        marketProvider = new MarketProvider(market);
    }

    @Override
    protected BoardInfo getBoardInfo() {
        return new BoardInfo(L10n.get("marketViewTitle"), L10n.get("marketViewDescription"));
    }

    @Override
    protected List<Renderable> getRenderables(Size size) {
        return Arrays.<Renderable>asList(
                new TabViewFactory(buttonManager, filterManager, activeTab, marketProvider).createContainer(size)
        );
        // TODO: add select market button based on below
        // String marketName = selectedMarket != null ? selectedMarket.getName() :
        // "None";
        // Button pickMe = new Button(new Size(200, 24), "Pick me", true,
        // Misc.getButtonTextColor());
        // pickMe.setHandler(new EventHandler() {
        // @Override
        // public void onConfirm(IntelUIAPI ui) {
        // ui.showDialog(null, new MarketSelector(ui));
        // }
        // });
        // return Arrays.<Renderable>asList(new Paragraph(marketName, size.getWidth()),
        // pickMe);
    }

    @Override
    protected String getTag() {
        return MarketQueryIntel.TAG;
    }
}
