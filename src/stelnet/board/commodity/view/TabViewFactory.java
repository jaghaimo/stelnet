package stelnet.board.commodity.view;

import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.lwjgl.input.Keyboard;
import stelnet.board.commodity.CommodityState.CommodityTab;
import stelnet.board.commodity.market.MarketRepository;
import stelnet.board.commodity.market.table.BuyTableContent;
import stelnet.board.commodity.market.table.ProfitTableContent;
import stelnet.board.commodity.market.table.SellTableContent;
import stelnet.board.commodity.view.button.CommodityTabButton;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.TabViewContainer;
import uilib.Table;
import uilib.TableContent;
import uilib.property.Size;

@RequiredArgsConstructor
public class TabViewFactory implements RenderableFactory {

    private final String commodityId;
    private final CommodityTab commodityTab;

    @Override
    public List<Renderable> create(Size size) {
        float width = size.getWidth() - 210;
        float height = size.getHeight() - 24;
        MarketRepository marketRepository = new MarketRepository(commodityId);
        TabViewContainer tabViewContainer = new TabViewContainer();
        tabViewContainer.setSize(new Size(width, height));
        addBuyTab(tabViewContainer, width, marketRepository);
        addSellTab(tabViewContainer, width, marketRepository);
        addProfitTab(tabViewContainer, width, marketRepository);
        return Collections.<Renderable>singletonList(tabViewContainer);
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
        return new CommodityTabButton(currentTab, commodityTab, keyboardShortcut);
    }

    private boolean isActive(CommodityTab currentTab) {
        return currentTab.equals(commodityTab);
    }
}
