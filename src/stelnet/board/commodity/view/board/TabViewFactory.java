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
    public List<Renderable> create(final Size size) {
        final float width = size.getWidth() - 210;
        float height = size.getHeight();
        if (!isActive(CommodityAction.PROFIT)) {
            height -= 24;
        }
        final TabViewContainer tabViewContainer = new TabViewContainer();
        tabViewContainer.setSize(new Size(width, height));
        addBuyTab(tabViewContainer, width);
        addSellTab(tabViewContainer, width);
        addProfitTab(tabViewContainer, width);
        return Collections.<Renderable>singletonList(tabViewContainer);
    }

    private void addBuyTab(final TabViewContainer tabViewContainer, final float width) {
        final TabViewButton tabButton = getTabButton(CommodityAction.BUY, Keyboard.KEY_B);
        final Table table = getBuyTable(width);
        final boolean isActive = isActive(CommodityAction.BUY);
        tabViewContainer.addTab(tabButton, table, isActive);
    }

    private void addSellTab(final TabViewContainer tabViewContainer, final float width) {
        final TabViewButton tabButton = getTabButton(CommodityAction.SELL, Keyboard.KEY_S);
        final Table table = getSellTable(width);
        final boolean isActive = isActive(CommodityAction.SELL);
        tabViewContainer.addTab(tabButton, table, isActive);
    }

    private void addProfitTab(final TabViewContainer tabViewContainer, final float width) {
        final TabViewButton tabButton = getTabButton(CommodityAction.PROFIT, Keyboard.KEY_P);
        final Table table = getProfitTable(width);
        final boolean isActive = isActive(CommodityAction.PROFIT);
        tabViewContainer.addTab(tabButton, table, isActive);
    }

    private Table getBuyTable(final float width) {
        final List<MarketAPI> markets = CommodityAction.BUY.getMarkets(commodityId);
        final TableContent tableContent = new BuyTableContent(commodityId, markets);
        return new Table(commodityId, width, 0, tableContent);
    }

    private Table getSellTable(final float width) {
        final List<MarketAPI> markets = CommodityAction.SELL.getMarkets(commodityId);
        final TableContent tableContent = new SellTableContent(commodityId, markets);
        return new Table(commodityId, width, 0, tableContent);
    }

    private Table getProfitTable(final float width) {
        final List<MarketAPI> sellMarkets = CommodityAction.PROFIT.getSellMarkets(commodityId);
        final List<MarketAPI> buyMarkets = CommodityAction.PROFIT.getBuyMarkets(commodityId);
        final TableContent tableContent = new ProfitTableContent(sellMarkets, buyMarkets, commodityId);
        return new Table(commodityId, width, 0, tableContent);
    }

    private TabViewButton getTabButton(final CommodityAction currentTab, final int keyboardShortcut) {
        return new TabViewButton(currentTab, commodityAction, keyboardShortcut);
    }

    private boolean isActive(final CommodityAction currentAction) {
        return currentAction.equals(commodityAction);
    }
}
