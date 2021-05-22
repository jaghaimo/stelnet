package stelnet.commodity.view;

import java.util.List;

import org.lwjgl.input.Keyboard;

import stelnet.commodity.CommodityTab;
import stelnet.commodity.data.BuyTableContent;
import stelnet.commodity.data.ProfitTableContent;
import stelnet.commodity.data.SellTableContent;
import stelnet.commodity.market.MarketApiWrapper;
import stelnet.commodity.market.MarketRepository;
import stelnet.ui.AbstractRenderable;
import stelnet.ui.Size;
import stelnet.ui.TabViewContainer;
import stelnet.ui.Table;
import stelnet.ui.TableContent;

public class TableViewFactory {

    public AbstractRenderable createContainer(String commodityId, CommodityTab activeTab, Size size) {
        float width = size.getWidth() - 210;
        float height = size.getHeight() - 35;
        float tabsHeight = 15f;
        float tableHeight = height - tabsHeight;
        MarketRepository marketRepository = new MarketRepository(commodityId);
        TabViewContainer tabViewContainer = new TabViewContainer();
        addBuyTab(tabViewContainer, commodityId, activeTab, width, tableHeight, marketRepository.getBuyMarkets());
        addSellTab(tabViewContainer, commodityId, activeTab, width, tableHeight, marketRepository.getSellMarkets());
        addProfitTab(tabViewContainer, commodityId, activeTab, width, tableHeight, marketRepository);
        return tabViewContainer;
    }

    private void addBuyTab(TabViewContainer tabViewContainer, String commodityId, CommodityTab activeTab, float width,
            float tableHeight, List<MarketApiWrapper> buyMarkets) {
        TabButton buyButton = new TabButton(CommodityTab.BUY, activeTab, Keyboard.KEY_B);
        TableContent tableContent = new BuyTableContent(commodityId, buyMarkets);
        Table table = new Table(commodityId, width, tableHeight, tableContent);
        tabViewContainer.addTab(buyButton, table, isActive(CommodityTab.BUY, activeTab));
    }

    private void addSellTab(TabViewContainer tabViewContainer, String commodityId, CommodityTab activeTab, float width,
            float tableHeight, List<MarketApiWrapper> sellMarkets) {
        TabButton sellButton = new TabButton(CommodityTab.SELL, activeTab, Keyboard.KEY_S);
        TableContent tableContent = new SellTableContent(commodityId, sellMarkets);
        Table table = new Table(commodityId, width, tableHeight, tableContent);
        tabViewContainer.addTab(sellButton, table, isActive(CommodityTab.SELL, activeTab));
    }

    private void addProfitTab(TabViewContainer tabViewContainer, String commodityId, CommodityTab activeTab,
            float width, float tableHeight, MarketRepository marketRepository) {
        TabButton profitButton = new TabButton(CommodityTab.PROFIT, activeTab, Keyboard.KEY_P);
        TableContent tableContent = new ProfitTableContent(commodityId, marketRepository);
        Table table = new Table(commodityId, width, tableHeight, tableContent);
        tabViewContainer.addTab(profitButton, table, isActive(CommodityTab.PROFIT, activeTab));
    }

    private boolean isActive(CommodityTab current, CommodityTab active) {
        return current.equals(active);
    }
}
