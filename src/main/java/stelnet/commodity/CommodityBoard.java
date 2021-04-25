package stelnet.commodity;

import java.util.Set;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.BaseBoard;
import stelnet.commodity.view.ButtonViewFactory;
import stelnet.commodity.view.CommodityViewFactory;
import stelnet.commodity.view.DeleteButton;
import stelnet.commodity.view.IntelSelectionFactory;
import stelnet.commodity.view.PurgeButton;
import stelnet.helper.IntelHelper;
import stelnet.helper.SettingHelper;
import stelnet.ui.GridRenderer;
import stelnet.ui.Size;
import stelnet.ui.Stack;

public class CommodityBoard extends BaseBoard {

    public enum CommodityTab {
        BUY("Buy"), SELL("Sell"), PROFIT("Profit");

        public String title;

        private CommodityTab(String title) {
            this.title = title;
        }
    }

    private String activeId;
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
        float commodityViewWidth = width - 210;
        float commodityViewHeight = height - 35;
        GridRenderer renderer = new GridRenderer(new Size(width, height));
        renderer.setTopLeft(commodityViewFactory.get(activeId, activeTab, commodityViewWidth, commodityViewHeight));
        renderer.setTopRight(buttonViewFactory.get(activeId));
        renderer.setBottomLeft(intelSelectionFactory.get(activeId, activeTab, commodityViewWidth));
        renderer.setBottomRight(new Stack(true, new PurgeButton(), new DeleteButton(activeId)));
        renderer.render(panel);
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

    public void setActiveId(String activeId) {
        this.activeId = activeId;
    }

    public void setActiveTab(CommodityTab activeTab) {
        this.activeTab = activeTab;
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
