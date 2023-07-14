package stelnet.board.contact2;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.Fonts;
import java.awt.*;
import uilib2.UiConstants;
import uilib2.button.Button;
import uilib2.button.ButtonBuilder;
import uilib2.button.ButtonCustom;

public class ButtonFactory {

    public Button getLeft(final IntelInfoPlugin plugin, final Color base, final Color bg) {
        final String title = "<";
        return getAutoButton(title, base, bg);
    }

    public Button getNumber(final Color base, final Color bg) {
        final String title = "1";
        return getAutoButton(title, base, bg);
    }

    public Button getRight(final IntelInfoPlugin plugin, final Color base, final Color bg) {
        final String title = ">";
        return getAutoButton(title, base, bg);
    }

    public Button getShow(final IntelInfoPlugin plugin, final Color base, final Color bg) {
        final String title = "Show";
        return getAutoButton(title, base, bg);
    }

    public Button getCall(final IntelInfoPlugin plugin, final Color base, final Color bg) {
        final String title = "Call";
        return getAutoButton(title, base, bg);
    }

    private Button getAutoButton(final String title, final Color base, final Color bg) {
        final float width =
            Global.getSettings().computeStringWidth(title, Fonts.VICTOR_10) + UiConstants.SPACER_DEFAULT;
        return new ButtonBuilder(new ButtonCustom(title, null, base, bg, Alignment.MID, CutStyle.C2_MENU, width, 18, 0))
            .setVictor10()
            .build();
    }
}
