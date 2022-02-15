package stelnet.board.commodity.view.intel;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import org.lwjgl.input.Keyboard;
import stelnet.board.commodity.CommodityBoard;
import stelnet.board.commodity.CommodityIntel;
import stelnet.board.commodity.CommodityL10n;
import stelnet.util.L10n;
import uilib.Button;
import uilib.property.Size;

public class DeleteIntel extends Button {

    private final CommodityIntel intel;

    public DeleteIntel(float width, CommodityIntel intel) {
        super(new Size(width, 24), L10n.get(CommodityL10n.DELETE), true);
        this.intel = intel;
        setShortcut(Keyboard.KEY_D);
    }

    @Override
    public boolean hasPrompt() {
        return true;
    }

    @Override
    public void onConfirm(IntelUIAPI ui) {
        CommodityBoard board = CommodityBoard.getInstance(CommodityBoard.class);
        board.getRenderableState().deleteIntel(intel);
    }

    @Override
    public void onPrompt(TooltipMakerAPI tooltipMaker) {
        tooltipMaker.addPara(L10n.get(CommodityL10n.DELETE_CONFIRMATION), Misc.getTextColor(), 0f);
    }
}
