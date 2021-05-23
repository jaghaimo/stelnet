package stelnet.commodity.view;

import org.lwjgl.input.Keyboard;

import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
public class TabViewFactory {

    private final String commodityId;
    private final CommodityTab activeTab;

    public AbstractRenderable createContainer(Size size) {
        float width = size.getWidth() - 210;
        float height = size.getHeight() - 50;
        MarketRepository marketRepository = new MarketRepository(commodityId);
        TabViewContainer tabViewContainer = new TabViewContainer();

        tabViewContainer.addTab(
                getTabButton(CommodityTab.BUY, Keyboard.KEY_B),
                getBuyTable(width, height, marketRepository),
                isActive(CommodityTab.BUY)
        );
        tabViewContainer.addTab(
                getTabButton(CommodityTab.SELL, Keyboard.KEY_S),
                getSellTable(width, height, marketRepository),
                isActive(CommodityTab.SELL)
        );
        tabViewContainer.addTab(
                getTabButton(CommodityTab.PROFIT, Keyboard.KEY_P),
                getProfitTable(width, height, marketRepository),
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

    private CommodityTabButton getTabButton(CommodityTab currentTab, int keyboardShortcut) {
        return new CommodityTabButton(currentTab, activeTab, keyboardShortcut);
    }

    private boolean isActive(CommodityTab currentTab) {
        return currentTab.equals(activeTab);
    }
}
