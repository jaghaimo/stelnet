package stelnet.board.commodity.view.board;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.lwjgl.input.Keyboard;
import stelnet.board.commodity.CommodityAction;
import stelnet.board.commodity.table.BuyTableContent;
import stelnet.board.commodity.table.ProfitTableContent;
import stelnet.board.commodity.table.SellTableContent;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.TabViewContainer;
import uilib.Table;
import uilib.TableContent;
import uilib.property.Size;

@RequiredArgsConstructor
public class TabViewFactory implements RenderableFactory {

    private final String commodityId;
    private final CommodityAction commodityAction;

    @Override
    public List<Renderable> create(Size size) {
        float width = size.getWidth() - 210;
        float height = size.getHeight();
        if (!isActive(CommodityAction.PROFIT)) {
            height -= 24;
        }
        TabViewContainer tabViewContainer = new TabViewContainer();
        tabViewContainer.setSize(new Size(width, height));
        addBuyTab(tabViewContainer, width);
        addSellTab(tabViewContainer, width);
        addProfitTab(tabViewContainer, width);
        return Collections.<Renderable>singletonList(tabViewContainer);
    }

    private void addBuyTab(TabViewContainer tabViewContainer, float width) {
        TabViewButton tabButton = getTabButton(CommodityAction.BUY, Keyboard.KEY_B);
        Table table = getBuyTable(width);
        boolean isActive = isActive(CommodityAction.BUY);
        tabViewContainer.addTab(tabButton, table, isActive);
    }

    private void addSellTab(TabViewContainer tabViewContainer, float width) {
        TabViewButton tabButton = getTabButton(CommodityAction.SELL, Keyboard.KEY_S);
        Table table = getSellTable(width);
        boolean isActive = isActive(CommodityAction.SELL);
        tabViewContainer.addTab(tabButton, table, isActive);
    }

    private void addProfitTab(TabViewContainer tabViewContainer, float width) {
        TabViewButton tabButton = getTabButton(CommodityAction.PROFIT, Keyboard.KEY_P);
        Table table = getProfitTable(width);
        boolean isActive = isActive(CommodityAction.PROFIT);
        tabViewContainer.addTab(tabButton, table, isActive);
    }

    private Table getBuyTable(float width) {
        List<MarketAPI> markets = CommodityAction.BUY.getMarkets(commodityId);
        TableContent tableContent = new BuyTableContent(commodityId, markets);
        return new Table(commodityId, width, 0, tableContent);
    }

    private Table getSellTable(float width) {
        List<MarketAPI> markets = CommodityAction.SELL.getMarkets(commodityId);
        TableContent tableContent = new SellTableContent(commodityId, markets);
        return new Table(commodityId, width, 0, tableContent);
    }

    private Table getProfitTable(float width) {
        List<MarketAPI> sellMarkets = CommodityAction.PROFIT.getSellMarkets(commodityId);
        List<MarketAPI> buyMarkets = CommodityAction.PROFIT.getBuyMarkets(commodityId);
        TableContent tableContent = new ProfitTableContent(sellMarkets, buyMarkets, commodityId);
        return new Table(commodityId, width, 0, tableContent);
    }

    private TabViewButton getTabButton(CommodityAction currentTab, int keyboardShortcut) {
        return new TabViewButton(currentTab, commodityAction, keyboardShortcut);
    }

    private boolean isActive(CommodityAction currentAction) {
        return currentAction.equals(commodityAction);
    }
}
