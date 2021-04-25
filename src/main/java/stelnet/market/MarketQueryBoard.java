package stelnet.market;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.BaseBoard;
import stelnet.helper.IntelHelper;
import stelnet.helper.SettingHelper;
import stelnet.market.view.ControlRow;
import stelnet.market.view.Queries;
import stelnet.ui.Position;
import stelnet.ui.Size;

/**
 * Information board for managing displayed information intel.
 *
 * Use this board to add, remove, refresh, disable, or enable intel queries to
 * dynamically update displayed intel.
 */
public class MarketQueryBoard extends BaseBoard {

    private List<IntelQuery> queries = new ArrayList<IntelQuery>();

    public static MarketQueryBoard getInstance() {
        IntelInfoPlugin intel = IntelHelper.getFirstIntel(MarketQueryBoard.class);
        if (intel == null) {
            MarketQueryBoard board = new MarketQueryBoard();
            IntelHelper.addIntel(board, true);
        }
        return (MarketQueryBoard) intel;
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
        Size size = new Size(width, height);
        (new ControlRow(queries)).render(panel, size, new Position(0, 0));
        (new Queries(queries)).render(panel, size, new Position(0, 38));
    }

    @Override
    public String getIcon() {
        return SettingHelper.getSpriteName("market");
    }

    @Override
    public Set<String> getIntelTags(SectorMapAPI map) {
        Set<String> tags = super.getIntelTags(map);
        tags.add(MarketResultIntel.TAG);
        return tags;
    }
}
