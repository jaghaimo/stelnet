package stelnet.commodity.view;

import java.util.Arrays;

import org.lwjgl.input.Keyboard;

import stelnet.commodity.CommodityTab;
import stelnet.commodity.data.BuyTableContent;
import stelnet.commodity.data.ProfitTableContent;
import stelnet.commodity.data.SellTableContent;
import stelnet.commodity.market.MarketRepository;
import stelnet.ui.AbstractRenderable;
import stelnet.ui.HorizontalViewContainer;
import stelnet.ui.Size;
import stelnet.ui.Table;
import stelnet.ui.TableContent;
import stelnet.ui.VerticalViewContainer;

public class TableViewFactory {

    public AbstractRenderable createContainer(String commodityId, CommodityTab activeTab, Size size) {
        float width = size.getWidth() - 210;
        float height = size.getHeight() - 35;
        float tabsHeight = 15f;
        float tableHeight = height - tabsHeight;
        HorizontalViewContainer tabs = createTabs(activeTab);
        TableContent tableContent = createTableContent(commodityId, activeTab);
        Table table = new Table(commodityId, width, tableHeight, tableContent);
        return new VerticalViewContainer(tabs, table);
    }

    private HorizontalViewContainer createTabs(CommodityTab activeTab) {
        AbstractRenderable buyButton = new TabButton(CommodityTab.BUY, activeTab, Keyboard.KEY_B);
        AbstractRenderable sellButton = new TabButton(CommodityTab.SELL, activeTab, Keyboard.KEY_S);
        AbstractRenderable profitButton = new TabButton(CommodityTab.PROFIT, activeTab, Keyboard.KEY_P);
        return new HorizontalViewContainer(Arrays.asList(buyButton, sellButton, profitButton));
    }

    private TableContent createTableContent(String commodityId, CommodityTab activeTab) {
        TableContent tableContent = null;
        MarketRepository marketRepository = MarketRepository.getInstance(commodityId);

        if (activeTab == CommodityTab.BUY) {
            tableContent = new BuyTableContent(commodityId, marketRepository.getBuyMarketByCommodity(commodityId));
        } else if (activeTab == CommodityTab.SELL) {
            tableContent = new SellTableContent(commodityId, marketRepository.getSellMarketByCommodity(commodityId));
        } else if (activeTab == CommodityTab.PROFIT) {
            tableContent = new ProfitTableContent(commodityId);
        }

        return tableContent;
    }
}
