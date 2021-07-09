package stelnet.market;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.L10n;
import stelnet.helper.GlobalSettingsHelper;
import stelnet.helper.IntelHelper;
import stelnet.market.dialog.MarketSelector;
import stelnet.ui.Button;
import stelnet.ui.EventHandler;
import stelnet.ui.Paragraph;
import stelnet.ui.Renderable;
import stelnet.ui.property.Size;

public class MarketViewBoard extends BaseBoard {

    private MarketAPI selectedMarket;

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
        selectedMarket = market;
    }

    @Override
    protected BoardInfo getBoardInfo() {
        return new BoardInfo(L10n.get("marketViewTitle"), L10n.get("marketViewDescription"));
    }

    @Override
    protected List<Renderable> getRenderables(Size size) {
        String marketName = selectedMarket != null ? selectedMarket.getName() : "None";
        Button pickMe = new Button(new Size(200, 24), "Pick me", true, Misc.getButtonTextColor());
        pickMe.setHandler(new EventHandler() {
            @Override
            public void onConfirm(IntelUIAPI ui) {
                ui.showDialog(null, new MarketSelector(ui));
            }
        });
        return Arrays.<Renderable>asList(
                new Paragraph(marketName, size.getWidth()),
                pickMe
        );
    }

    @Override
    protected String getTag() {
        return MarketQueryIntel.TAG;
    }
}
