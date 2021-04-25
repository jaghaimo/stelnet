package stelnet.commodity.view;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import org.lwjgl.input.Keyboard;
import stelnet.commodity.CommodityBoard.CommodityTab;
import stelnet.commodity.data.MarketFactory;
import stelnet.commodity.data.content.buy.BuyMarketFactory;
import stelnet.commodity.data.content.buy.BuyTableContent;
import stelnet.commodity.data.content.profit.ProfitTableContent;
import stelnet.commodity.data.content.sell.SellMarketFactory;
import stelnet.commodity.data.content.sell.SellTableContent;
import stelnet.ui.*;

import java.util.Arrays;
import java.util.List;

public class CommodityViewFactory {

    private IntelSelectionFactory intelSelectionFactory;

    public CommodityViewFactory(IntelSelectionFactory intelSelectionFactory) {
        this.intelSelectionFactory = intelSelectionFactory;
    }

    public Renderable get(String commodityId, CommodityTab activeTab, float width, float height) {
        float tabsHeight = 15f;
        float tableHeight = height - tabsHeight;
        TableContent tableContent = getTableContent(commodityId, activeTab);
        Renderable tabs = getTabs(activeTab);
        Renderable table = new Table(commodityId, width, tableHeight, tableContent);
        return new Stack(tabs, table);
    }

    private Renderable getTabs(CommodityTab activeTab) {
        Renderable buyButton = new TabButton(CommodityTab.BUY, activeTab, Keyboard.KEY_B);
        Renderable sellButton = new TabButton(CommodityTab.SELL, activeTab, Keyboard.KEY_S);
        Renderable profitButton = new TabButton(CommodityTab.PROFIT, activeTab, Keyboard.KEY_P);
        return new Row(Arrays.asList(buyButton, sellButton, profitButton));
    }

    private TableContent getTableContent(String commodityId, CommodityTab activeTab) {
        TableContent tableContent = null;
        if (activeTab == CommodityTab.BUY) {
            tableContent = getBuyTableContent(commodityId);
        } else if (activeTab == CommodityTab.SELL) {
            tableContent = getSellTableContent(commodityId);
        } else if (activeTab == CommodityTab.PROFIT) {
            tableContent = getProfitTableContent(commodityId);
        }
        return tableContent;
    }

    private TableContent getBuyTableContent(String commodityId) {
        return new BuyTableContent(commodityId, getMarkets(new BuyMarketFactory(commodityId)));
    }

    private TableContent getSellTableContent(String commodityId) {
        return new SellTableContent(commodityId, getMarkets(new SellMarketFactory(commodityId)));
    }

    private TableContent getProfitTableContent(String commodityId) {
        return new ProfitTableContent(commodityId, getMarkets(new BuyMarketFactory(commodityId)));
    }

    private List<MarketAPI> getMarkets(MarketFactory factory) {
        List<MarketAPI> markets = factory.getMarkets();
        intelSelectionFactory.setMarkets(markets);
        return markets;
    }
}
