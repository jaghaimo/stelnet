package stelnet.commodity;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.commodity.view.ButtonViewFactory;
import stelnet.commodity.view.DeleteViewFactory;
import stelnet.commodity.view.IntelViewFactory;
import stelnet.commodity.view.TabViewFactory;
import stelnet.util.IntelManager;
import stelnet.util.L10n;
import stelnet.util.Settings;
import stelnet.util.Tagger;
import uilib.Renderable;
import uilib.property.Size;

@Setter
@Getter
public class CommodityBoard extends BaseBoard {

    private String commodityId = Commodities.SUPPLIES;
    private CommodityTab activeTab = CommodityTab.BUY;
    private final IntelTracker intelTracker = new IntelTracker();

    public static CommodityBoard getInstance() {
        IntelInfoPlugin intel = IntelManager.getFirstIntel(
            CommodityBoard.class
        );
        if (intel == null) {
            BaseIntelPlugin board = new CommodityBoard();
            IntelManager.addIntel(board, true);
        }
        return (CommodityBoard) intel;
    }

    @Override
    public String getIcon() {
        return Settings.getSpriteName("commodity");
    }

    public void deleteIntel() {
        intelTracker.removeAll();
    }

    public void deleteIntel(CommodityIntel intel) {
        intelTracker.remove(intel);
    }

    public void deleteIntel(String commodityId) {
        intelTracker.removeCommodity(commodityId);
    }

    @Override
    protected List<Renderable> getRenderables(Size size) {
        return Arrays.<Renderable>asList(
            new TabViewFactory(commodityId, activeTab).createContainer(size),
            new IntelViewFactory(intelTracker)
                .createContainer(commodityId, activeTab, size),
            new ButtonViewFactory().createContainer(commodityId, size),
            new DeleteViewFactory().createContainer(commodityId, size)
        );
    }

    @Override
    protected BoardInfo getBoardInfo() {
        return new BoardInfo(
            L10n.get("commodityBoardTitle"),
            L10n.get("commodityBoardDescription")
        );
    }

    @Override
    protected String getTag() {
        return Tagger.COMMODITY;
    }
}
