package stelnet.board.contact;

import com.fs.starfarer.api.campaign.CoreUITabId;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.CommonL10n;
import stelnet.util.L10n;
import stelnet.util.SectorUtils;
import uilib.Button;
import uilib.EventHandler;
import uilib.UiConstants;
import uilib.property.Size;

public class ShowContactButton extends Button {

    public ShowContactButton(final ContactIntel intel, FactionAPI faction) {
        super(
            new Size(UiConstants.AUTO_WIDTH, UiConstants.DEFAULT_ROW_HEIGHT),
            L10n.get(CommonL10n.SHOW),
            true,
            faction.getBrightUIColor(),
            faction.getDarkUIColor()
        );
        setCutStyle(CutStyle.C2_MENU);
        setPadding(0);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    SectorUtils.getCampaignUI().showCoreUITab(CoreUITabId.INTEL, intel);
                }
            }
        );
    }
}
