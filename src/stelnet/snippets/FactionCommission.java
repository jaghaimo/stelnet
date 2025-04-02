package stelnet.snippets;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.RepLevel;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.intel.FactionCommissionIntel;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lunalib.lunaDebug.LunaSnippet;
import lunalib.lunaDebug.SnippetBuilder;
import stelnet.util.L10n;
import stelnet.util.ModConstants;

public class FactionCommission extends LunaSnippet {

    private final String FACTION_ID = "factionId";

    @Override
    public String getDescription() {
        return L10n.get(SnippetsL10n.COMMISSION_DESCRIPTION);
    }

    @Override
    public String getModId() {
        return ModConstants.STELNET_ID;
    }

    @Override
    public String getName() {
        return L10n.get(SnippetsL10n.COMMISSION_NAME);
    }

    @Override
    public List<String> getTags() {
        return Arrays.asList(SnippetTags.Player.toString());
    }

    @Override
    public void addParameters(SnippetBuilder builder) {
        builder.addStringParameter("faction_id", FACTION_ID);
    }

    @Override
    public void execute(Map<String, Object> parameters, TooltipMakerAPI output) {
        String factionId = (String) parameters.get(FACTION_ID);
        FactionAPI faction = Global.getSector().getFaction(factionId);
        if (faction == null) {
            output.addPara(L10n.get(SnippetsL10n.COMMISSION_FACTION_MISSING, factionId), 0);
            return;
        }
        FactionAPI commissionedFaction = Misc.getCommissionFaction();
        if (commissionedFaction != null) {
            output.addPara(L10n.get(SnippetsL10n.COMMISSION_ONGOING, commissionedFaction.getDisplayName()), 0);
            return;
        }
        output.addPara(L10n.get(SnippetsL10n.COMMISSION_ADDED, faction.getDisplayName()), 0);
        FactionCommissionIntel intel = new FactionCommissionIntel(faction);
        faction.setRelationship(Factions.PLAYER, RepLevel.FAVORABLE);
        intel.missionAccepted();
        intel.makeRepChanges(null);
    }
}
