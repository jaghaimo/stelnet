package stelnet.board.contact;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.Fonts;
import java.awt.*;
import lombok.RequiredArgsConstructor;
import stelnet.util.L10n;
import uilib2.UiConstants;
import uilib2.button.Button;
import uilib2.button.ButtonBuilder;
import uilib2.button.ButtonCustom;
import uilib2.button.EnabledButton;
import uilib2.intel.IntelCallbackBuilder;
import uilib2.intel.IntelUiCallback;
import uilib2.intel.actions.SelectItem;
import uilib2.intel.actions.UpdateForItem;

@RequiredArgsConstructor
public class ButtonFactory {

    private final Contact contact;

    public Button getLeft(final Color base, final Color bg) {
        final String title = "<";
        final IntelUiCallback callback = new IntelCallbackBuilder()
            .addConfirmAction(new ChangeSortIdx(contact, -1))
            .addConfirmAction(new UpdateForItem(contact.getContactBoard()))
            .build();
        return getAutoButton(title, callback, base, bg);
    }

    private Button getAutoButton(final String title, final Object data, final Color base, final Color bg) {
        final float width =
            Global.getSettings().computeStringWidth(title, Fonts.VICTOR_10) + UiConstants.SPACER_DEFAULT;
        return new ButtonBuilder(new ButtonCustom(title, data, base, bg, Alignment.MID, CutStyle.C2_MENU, width, 18, 0))
            .setVictor10()
            .build();
    }

    public Button getNumber(final Color base, final Color bg) {
        final String title = String.valueOf(contact.getSortIdx());
        return getAutoButton(title, null, base, bg);
    }

    public Button getRight(final Color base, final Color bg) {
        final String title = ">";
        final IntelUiCallback callback = new IntelCallbackBuilder()
            .addConfirmAction(new ChangeSortIdx(contact, 1))
            .addConfirmAction(new UpdateForItem(contact.getContactBoard()))
            .build();
        return getAutoButton(title, callback, base, bg);
    }

    public Button getShow(final Color base, final Color bg) {
        final String title = L10n.common("SHOW");
        final IntelUiCallback callback = new IntelCallbackBuilder()
            .addConfirmAction(new SelectItem(contact.getContactIntel()))
            .build();
        return getAutoButton(title, callback, base, bg);
    }

    public Button getCall(final Color base, final Color bg) {
        final String title = L10n.common("CALL");
        final IntelUiCallback callback = new IntelCallbackBuilder()
            .addConfirmAction(new CallContact(contact.getMarket(), contact.getPerson()))
            .addConfirmAction(new UpdateForItem(contact.getContactBoard()))
            .build();
        return new EnabledButton(getAutoButton(title, callback, base, bg), contact.isCallEnabled());
    }
}
