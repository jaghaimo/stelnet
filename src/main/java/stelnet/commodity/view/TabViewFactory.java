package stelnet.commodity.view;

import org.lwjgl.input.Keyboard;

import lombok.RequiredArgsConstructor;
import stelnet.commodity.CommodityTab;
import stelnet.commodity.data.BuyTableContent;
import stelnet.commodity.data.ProfitTableContent;
import stelnet.commodity.data.SellTableContent;
import stelnet.commodity.market.MarketRepository;
import uilib.Renderable;
import uilib.TabViewContainer;
import uilib.Table;
import uilib.TableContent;
import uilib.property.Size;

@RequiredArgsConstructor
public class TabViewFactory {

    private final String commodityId;
    private final CommodityTab activeTab;

    public Renderable createContainer(Size size) {
        float width = size.getWidth() - 210;
        float height = size.getHeight() - 54;
        MarketRepository marketRepository = new MarketRepository(commodityId);
        TabViewContainer tabViewContainer = new TabViewContainer();
        tabViewContainer.setSize(new Size(width, height));

        tabViewContainer.addTab(getTabButton(CommodityTab.BUY, Keyboard.KEY_B), getBuyTable(width, marketRepository),
                isActive(CommodityTab.BUY));
        tabViewContainer.addTab(getTabButton(CommodityTab.SELL, Keyboard.KEY_S), getSellTable(width, marketRepository),
                isActive(CommodityTab.SELL));
        tabViewContainer.addTab(getTabButton(CommodityTab.PROFIT, Keyboard.KEY_P),
                getProfitTable(width, marketRepository), isActive(CommodityTab.PROFIT));

        return tabViewContainer;
    }

    private Table getBuyTable(float width, MarketRepository marketRepository) {
        TableContent tableContent = new BuyTableContent(commodityId, marketRepository.getBuyMarkets());
        return new Table(commodityId, width, 0, tableContent);
    }

    private Table getSellTable(float width, MarketRepository marketRepository) {
        TableContent tableContent = new SellTableContent(commodityId, marketRepository.getSellMarkets());
        return new Table(commodityId, width, 0, tableContent);
    }

    private Table getProfitTable(float width, MarketRepository marketRepository) {
        TableContent tableContent = new ProfitTableContent(commodityId, marketRepository);
        return new Table(commodityId, width, 0, tableContent);
    }

    private CommodityTabButton getTabButton(CommodityTab currentTab, int keyboardShortcut) {
        return new CommodityTabButton(currentTab, activeTab, keyboardShortcut);
    }

    private boolean isActive(CommodityTab currentTab) {
        return currentTab.equals(activeTab);
    }
}
