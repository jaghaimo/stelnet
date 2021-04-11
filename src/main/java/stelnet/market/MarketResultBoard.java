package stelnet.market;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.helper.GlobalHelper;
import stelnet.helper.IntelHelper;
import stelnet.market.handler.ButtonHandler;
import stelnet.market.handler.ButtonHandlerFactory;
import stelnet.market.panel.BoardRow;
import stelnet.market.panel.ControlRow;
import stelnet.market.panel.EmptyQueriesRow;
import stelnet.market.panel.HeaderRow;
import stelnet.market.panel.QueryRow;

/**
 * Information board for managing displayed information intel.
 *
 * Use this board to add, remove, refresh, disable, or enable intel queries to
 * dynamically update displayed intel.
 */
public class MarketResultBoard extends BaseIntelPlugin {

    private List<IntelQuery> queries = new ArrayList<IntelQuery>();

    public static MarketResultBoard getInstance() {
        IntelInfoPlugin intel = IntelHelper.getFirstIntel(MarketResultBoard.class);
        if (intel == null) {
            MarketResultBoard board = new MarketResultBoard();
            IntelHelper.addIntel(board);
        }
        return (MarketResultBoard) intel;
    }

    @Override
    public void buttonPressConfirmed(Object buttonId, IntelUIAPI ui) {
        ButtonHandler handler = ButtonHandlerFactory.get(buttonId);
        handler.handle(queries, ui);
    }

    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        Color bulletColor = getBulletColorForMode(mode);
        Color highlightColor = Misc.getHighlightColor();
        info.addPara("Query Board", getTitleColor(mode), 0);
        int queriesPresent = queries.size();
        if (queriesPresent == 1) {
            info.addPara("Managing %s intel query.", 1f, bulletColor, highlightColor, "1");
        } else if (queriesPresent > 1) {
            info.addPara("Managing %s intel queries.", 1f, bulletColor, highlightColor, String.valueOf(queriesPresent));
        } else {
            info.addPara("No intel queries present.", bulletColor, 1f);
        }
        info.addPara("", 1f);
    }

    @Override
    public void createLargeDescription(CustomPanelAPI panel, float width, float height) {
        TooltipMakerAPI outer = panel.createUIElement(width, height, true);
        float innerWidth = width - 10;
        CustomPanelAPI inner = panel.createCustomPanel(innerWidth, height, null);

        float currentHeight = 0;
        for (BoardRow row : getFreshBoardRows(inner, innerWidth)) {
            row.render(currentHeight);
            currentHeight += row.getHeight();
        }

        inner.getPosition().setSize(innerWidth, currentHeight);
        outer.addCustom(inner, 0);
        panel.addUIElement(outer).inTL(0, 0);
    }

    @Override
    public String getIcon() {
        return GlobalHelper.getSpriteName("market");
    }

    @Override
    public Set<String> getIntelTags(SectorMapAPI map) {
        Set<String> tags = super.getIntelTags(map);
        tags.add(MarketResultIntel.TAG);
        return tags;
    }

    @Override
    public IntelSortTier getSortTier() {
        return IntelSortTier.TIER_0;
    }

    @Override
    public boolean hasSmallDescription() {
        return false;
    }

    @Override
    public boolean hasLargeDescription() {
        return true;
    }

    private List<BoardRow> getFreshBoardRows(CustomPanelAPI panel, float width) {
        List<BoardRow> elements = new ArrayList<>();
        elements.add(new ControlRow(panel, width, !queries.isEmpty()));
        elements.add(new HeaderRow(panel, width));

        if (queries.isEmpty()) {
            elements.add(new EmptyQueriesRow(panel, width));
        }

        for (int i = 0; i < queries.size(); i++) {
            IntelQuery query = queries.get(i);
            elements.add(new QueryRow(panel, width, i, query));
        }

        return elements;
    }
}
