package stelnet.board.commodity.view;

import lombok.RequiredArgsConstructor;
import org.lwjgl.input.Keyboard;
import stelnet.board.commodity.CommodityState;
import stelnet.board.commodity.CommodityTab;
import stelnet.board.commodity.data.BuyTableContent;
import stelnet.board.commodity.data.ProfitTableContent;
import stelnet.board.commodity.data.SellTableContent;
import stelnet.board.commodity.market.MarketRepository;
import uilib.Renderable;
import uilib.TabViewContainer;
import uilib.Table;
import uilib.TableContent;
import uilib.ViewContainerFactory;
import uilib.property.Size;

@RequiredArgsConstructor
public class TabViewFactory implements ViewContainerFactory {

    private final String commodityId;
    private final CommodityTab activeTab;

    public TabViewFactory(CommodityState commodityState) {
        this(commodityState.getCommodityId(), commodityState.getActiveTab());
    }

    @Override
    public Renderable createContainer(Size size) {
        float width = size.getWidth() - 210;
        float height = size.getHeight() - 54;
        MarketRepository marketRepository = new MarketRepository(commodityId);
        TabViewContainer tabViewContainer = new TabViewContainer();
        tabViewContainer.setSize(new Size(width, height));
        addBuyTab(tabViewContainer, width, marketRepository);
        addSellTab(tabViewContainer, width, marketRepository);
        addProfitTab(tabViewContainer, width, marketRepository);
        return tabViewContainer;
    }

    private void addBuyTab(TabViewContainer tabViewContainer, float width, MarketRepository marketRepository) {
        CommodityTabButton tabButton = getTabButton(CommodityTab.BUY, Keyboard.KEY_B);
        Table table = getBuyTable(width, marketRepository);
        boolean isActive = isActive(CommodityTab.BUY);
        tabViewContainer.addTab(tabButton, table, isActive);
    }

    private void addSellTab(TabViewContainer tabViewContainer, float width, MarketRepository marketRepository) {
        CommodityTabButton tabButton = getTabButton(CommodityTab.SELL, Keyboard.KEY_S);
        Table table = getSellTable(width, marketRepository);
        boolean isActive = isActive(CommodityTab.SELL);
        tabViewContainer.addTab(tabButton, table, isActive);
    }

    private void addProfitTab(TabViewContainer tabViewContainer, float width, MarketRepository marketRepository) {
        CommodityTabButton tabButton = getTabButton(CommodityTab.PROFIT, Keyboard.KEY_P);
        Table table = getProfitTable(width, marketRepository);
        boolean isActive = isActive(CommodityTab.PROFIT);
        tabViewContainer.addTab(tabButton, table, isActive);
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
