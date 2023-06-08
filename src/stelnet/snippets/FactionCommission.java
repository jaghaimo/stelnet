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
import stelnet.util.ModConstants;

public class FactionCommission extends LunaSnippet {

    private final String FACTION_ID = "factionId";

    @Override
    public String getDescription() {
        return "Makes player commissioned to a selected faction. Forces FAVOURABLE reputation with said faction.";
    }

    @Override
    public String getModId() {
        return ModConstants.STELNET_ID;
    }

    @Override
    public String getName() {
        return "Commision to a faction";
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
            output.addPara("Could not find faction with id " + factionId, 0);
            return;
        }
        FactionAPI commissionedFaction = Misc.getCommissionFaction();
        if (commissionedFaction != null) {
            output.addPara("Player is already comissioned to " + commissionedFaction.getDisplayName(), 0);
            return;
        }
        output.addPara("Player is now commissioned with " + faction.getDisplayName(), 0);
        FactionCommissionIntel intel = new FactionCommissionIntel(faction);
        faction.setRelationship(Factions.PLAYER, RepLevel.FAVORABLE);
        intel.missionAccepted();
        intel.makeRepChanges(null);
    }
}
