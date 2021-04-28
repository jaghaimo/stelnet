package stelnet.commodity;

import java.util.Set;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import lombok.Setter;
import stelnet.BaseBoard;
import stelnet.commodity.view.ButtonViewFactory;
import stelnet.commodity.view.CommodityViewFactory;
import stelnet.commodity.view.DeleteViewFactory;
import stelnet.commodity.view.IntelSelectionFactory;
import stelnet.helper.IntelHelper;
import stelnet.helper.SettingHelper;
import stelnet.ui.Size;

public class CommodityBoard extends BaseBoard {

    public enum CommodityTab {
        BUY("Buy"), SELL("Sell");

        public String title;

        private CommodityTab(String title) {
            this.title = title;
        }
    }

    @Setter
    private String activeId;
    @Setter
    private CommodityTab activeTab;
    private ButtonViewFactory buttonViewFactory;
    private CommodityViewFactory commodityViewFactory;
    private IntelSelectionFactory intelSelectionFactory;

    public static CommodityBoard getInstance() {
        IntelInfoPlugin intel = IntelHelper.getFirstIntel(CommodityBoard.class);
        if (intel == null) {
            BaseIntelPlugin board = new CommodityBoard();
            IntelHelper.addIntel(board, true);
        }
        return (CommodityBoard) intel;
    }

    public CommodityBoard() {
        readResolve();
    }

    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        info.addPara("Commodity Market", getTitleColor(mode), 0);
        info.addPara("Compare and track commodity prices among all known markets.", getBulletColorForMode(mode), 1f);
        info.addPara("", 1f);
    }

    @Override
    public void createLargeDescription(CustomPanelAPI panel, float width, float height) {
        // TODO: rework grid data into renderable views and remove method
        Size size = new Size(width, height);
        commodityViewFactory.get(activeId, activeTab, size).render(panel);
        intelSelectionFactory.get(activeId, activeTab, size).render(panel);
        buttonViewFactory.get(activeId, size).render(panel);
        new DeleteViewFactory().get(activeId, size).render(panel);
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

    protected Object readResolve() {
        if (activeId == null) {
            activeId = Commodities.SUPPLIES;
        }
        if (activeTab == null) {
            activeTab = CommodityTab.BUY;
        }
        if (buttonViewFactory == null) {
            buttonViewFactory = new ButtonViewFactory();
        }
        if (intelSelectionFactory == null) {
            intelSelectionFactory = new IntelSelectionFactory();
        }
        if (commodityViewFactory == null) {
            commodityViewFactory = new CommodityViewFactory(intelSelectionFactory);
        }
        return this;
    }
}
