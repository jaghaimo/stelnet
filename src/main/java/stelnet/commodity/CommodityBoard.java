package stelnet.commodity;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.Getter;
import lombok.Setter;
import stelnet.BaseBoard;
import stelnet.commodity.view.ButtonViewFactory;
import stelnet.commodity.view.DeleteViewFactory;
import stelnet.commodity.view.TableViewFactory;
import stelnet.helper.IntelHelper;
import stelnet.helper.SettingHelper;
import stelnet.ui.Renderable;
import stelnet.ui.Size;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Setter
@Getter
public class CommodityBoard extends BaseBoard {

    private String activeId;
    private CommodityTab activeTab;
    private TableViewFactory tableViewFactory;

    private CommodityBoard() {
        activeId = Commodities.SUPPLIES;
        activeTab = CommodityTab.BUY;
        tableViewFactory = new TableViewFactory();
    }

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
        info.addPara("Commodity Market", getTitleColor(mode), 0);
        info.addPara("Compare and track commodity prices among all known markets.", getBulletColorForMode(mode), 1f);
        info.addPara("", 1f);
    }

    @Override
    public String getIcon() {
        return SettingHelper.getSpriteName("commodity");
    }

    @Override
    public Set<String> getIntelTags(SectorMapAPI map) {
        Set<String> tags = super.getIntelTags(map);
        tags.add(CommodityIntel.TAG);
        return tags;
    }

    @Override
    protected List<Renderable> getRenderables(Size size) {
        return Arrays.<Renderable>asList(
                tableViewFactory.create(activeId, activeTab, size),
                new ButtonViewFactory().get(activeId, size),
                new DeleteViewFactory().get(activeId, size)
        );
    }
}
