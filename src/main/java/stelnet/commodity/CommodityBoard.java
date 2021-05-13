package stelnet.commodity;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import lombok.Getter;
import lombok.Setter;
import stelnet.BaseBoard;
import stelnet.commodity.view.ButtonViewFactory;
import stelnet.commodity.view.DeleteViewFactory;
import stelnet.commodity.view.IntelViewFactory;
import stelnet.commodity.view.TableViewFactory;
import stelnet.helper.GlobalSettingsHelper;
import stelnet.helper.IntelHelper;
import stelnet.l10n.CommodityBundle;
import stelnet.ui.Renderable;
import stelnet.ui.Size;

@Setter
@Getter
public class CommodityBoard extends BaseBoard {

    private String commodityId = Commodities.SUPPLIES;
    private CommodityTab activeTab = CommodityTab.BUY;
    private final IntelTracker intelTracker = new IntelTracker();

    public static CommodityBoard getInstance() {
        IntelInfoPlugin intel = IntelHelper.getFirstIntel(CommodityBoard.class);
        if (intel == null) {
            BaseIntelPlugin board = new CommodityBoard();
            IntelHelper.addIntel(board, true);
        }
        return (CommodityBoard) intel;
    }

    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        CommodityBundle bundle = new CommodityBundle();
        info.addPara(bundle.boardTitle(), getTitleColor(mode), 0);
        info.addPara(bundle.boardDescription(), getBulletColorForMode(mode), 1f);
        info.addPara("", 1f);
    }

    @Override
    public String getIcon() {
        return GlobalSettingsHelper.getSpriteName("commodity");
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
        // @formatter:off
        return Arrays.<Renderable>asList(
                new TableViewFactory().createContainer(commodityId, activeTab, size),
                new IntelViewFactory(intelTracker).createContainer(commodityId, activeTab, size),
                new ButtonViewFactory().createContainer(commodityId, size),
                new DeleteViewFactory().createContainer(commodityId, size)
        );
        // @formatter:on
    }

    @Override
    protected String getTag() {
        return CommodityIntel.TAG;
    }
}
