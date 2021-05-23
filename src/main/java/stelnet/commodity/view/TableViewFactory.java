package stelnet.commodity.view;

import org.lwjgl.input.Keyboard;

import lombok.AllArgsConstructor;
import stelnet.commodity.CommodityTab;
import stelnet.commodity.data.BuyTableContent;
import stelnet.commodity.data.ProfitTableContent;
import stelnet.commodity.data.SellTableContent;
import stelnet.commodity.market.MarketRepository;
import stelnet.ui.AbstractRenderable;
import stelnet.ui.Size;
import stelnet.ui.TabViewContainer;
import stelnet.ui.Table;
import stelnet.ui.TableContent;

@AllArgsConstructor
public class TableViewFactory {

    private String commodityId;
    private CommodityTab activeTab;

    public AbstractRenderable createContainer(Size size) {
        float width = size.getWidth() - 210;
        float height = size.getHeight() - 35;
        float tabsHeight = 15f;
        float tableHeight = height - tabsHeight;
        MarketRepository marketRepository = new MarketRepository(commodityId);
        TabViewContainer tabViewContainer = new TabViewContainer();

        tabViewContainer.addTab(
                getTabButton(CommodityTab.BUY, Keyboard.KEY_B),
                getBuyTable(width, tableHeight, marketRepository),
                isActive(CommodityTab.BUY)
        );
        tabViewContainer.addTab(
                getTabButton(CommodityTab.SELL, Keyboard.KEY_S),
                getSellTable(width, tableHeight, marketRepository),
                isActive(CommodityTab.SELL)
        );
        tabViewContainer.addTab(
                getTabButton(CommodityTab.PROFIT, Keyboard.KEY_P),
                getProfitTable(width, tableHeight, marketRepository),
                isActive(CommodityTab.PROFIT)
        );

        return tabViewContainer;
    }

    private Table getBuyTable(float width, float tableHeight, MarketRepository marketRepository) {
        TableContent tableContent = new BuyTableContent(commodityId, marketRepository.getBuyMarkets());
        return new Table(commodityId, width, tableHeight, tableContent);
    }

    private Table getSellTable(float width, float tableHeight, MarketRepository marketRepository) {
        TableContent tableContent = new SellTableContent(commodityId, marketRepository.getSellMarkets());
        return new Table(commodityId, width, tableHeight, tableContent);
    }

    private Table getProfitTable(float width, float tableHeight, MarketRepository marketRepository) {
        TableContent tableContent = new ProfitTableContent(commodityId, marketRepository);
        return new Table(commodityId, width, tableHeight, tableContent);
    }

    private TabButton getTabButton(CommodityTab currentTab, int keyboardShortcut) {
        return new TabButton(currentTab, activeTab, keyboardShortcut);
    }

    private boolean isActive(CommodityTab currentTab) {
        return currentTab.equals(activeTab);
    }
}
