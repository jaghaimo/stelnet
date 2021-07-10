package stelnet.market;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import lombok.Getter;
import lombok.Setter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.L10n;
import stelnet.helper.GlobalSettingsHelper;
import stelnet.helper.IntelHelper;
import stelnet.market.dialog.MarketSelector;
import stelnet.market.view.MvTabViewFactory;
import stelnet.storage.ButtonManager;
import stelnet.storage.FilterManager;
import stelnet.storage.StorageTab;
import stelnet.ui.Button;
import stelnet.ui.EventHandler;
import stelnet.ui.Renderable;
import stelnet.ui.property.Location;
import stelnet.ui.property.Size;

@Getter
@Setter
public class MarketViewBoard extends BaseBoard {

    private final FilterManager filterManager = new FilterManager();
    private final ButtonManager buttonManager = new ButtonManager(filterManager);
    private StorageTab activeTab = StorageTab.ITEMS;
    private MarketProvider marketProvider = new MarketProvider(null);

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

    @Override
    protected BoardInfo getBoardInfo() {
        return new BoardInfo(L10n.get("marketViewTitle"), L10n.get("marketViewDescription"));
    }

    @Override
    protected List<Renderable> getRenderables(Size size) {
        return Arrays.<Renderable>asList(
                new MvTabViewFactory(buttonManager, filterManager, activeTab, marketProvider).createContainer(size),
                getButton()
        );
    }

    private Button getButton() {
        Button button = new Button(new Size(200, 24), "Pick me", true, Misc.getButtonTextColor());
        button.setLocation(Location.BOTTOM_RIGHT);
        button.setHandler(new EventHandler() {
            @Override
            public void onConfirm(IntelUIAPI ui) {
                ui.showDialog(null, new MarketSelector(ui));
            }
        });
        return button;
    }

    @Override
    protected String getTag() {
        return MarketQueryIntel.TAG;
    }
}
