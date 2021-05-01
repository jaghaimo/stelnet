package stelnet.commodity.view;

import org.lwjgl.input.Keyboard;
import stelnet.commodity.CommodityTab;
import stelnet.commodity.data.BuyTableContent;
import stelnet.commodity.data.MarketTableContent;
import stelnet.commodity.data.SellTableContent;
import stelnet.commodity.market.MarketRepository;
import stelnet.ui.*;

import java.util.Arrays;

public class TableViewFactory {

    public AbstractRenderable create(String commodityId, CommodityTab activeTab, Size size) {
        float width = size.getWidth() - 210;
        float height = size.getHeight() - 35;
        float tabsHeight = 15f;
        float tableHeight = height - tabsHeight;

        HorizontalViewContainer tabs = createTabs(activeTab);
        MarketTableContent tableContent = createTableContent(commodityId, activeTab);
        Table table = new Table(commodityId, width, tableHeight, tableContent);
        return new VerticalViewContainer(tabs, table);
    }

    private HorizontalViewContainer createTabs(CommodityTab activeTab) {
        AbstractRenderable buyButton = new TabButton(CommodityTab.BUY, activeTab, Keyboard.KEY_B);
        AbstractRenderable sellButton = new TabButton(CommodityTab.SELL, activeTab, Keyboard.KEY_S);
        return new HorizontalViewContainer(Arrays.asList(buyButton, sellButton));
    }

    private MarketTableContent createTableContent(String commodityId, CommodityTab activeTab) {
        MarketTableContent tableContent = null;
        MarketRepository marketRepository = new MarketRepository(commodityId);

        if (activeTab == CommodityTab.BUY) {
            tableContent = new BuyTableContent(commodityId, marketRepository.getBuyMarketByCommodity(commodityId));
        } else if (activeTab == CommodityTab.SELL) {
            tableContent = new SellTableContent(commodityId, marketRepository.getSellMarketByCommodity(commodityId));
        }

        return tableContent;
    }
}
