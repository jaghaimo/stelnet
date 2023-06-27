package stelnet.board.commodity.view.intel;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import org.lwjgl.input.Keyboard;
import stelnet.board.commodity.CommodityIntel;
import stelnet.util.L10n;
import uilib.Button;
import uilib.property.Size;

public class DeleteIntel extends Button {

    private final CommodityIntel intel;

    public DeleteIntel(final float width, final CommodityIntel intel) {
        super(new Size(width, 24), L10n.commodity("DELETE"), true);
        this.intel = intel;
        setShortcut(Keyboard.KEY_D);
    }

    @Override
    public boolean hasPrompt() {
        return true;
    }

    @Override
    public void onConfirm(final IntelUIAPI ui) {
        intel.remove();
    }

    @Override
    public void onPrompt(final TooltipMakerAPI tooltipMaker) {
        tooltipMaker.addPara(L10n.commodity("DELETE_CONFIRMATION"), Misc.getTextColor(), 0f);
    }
}
