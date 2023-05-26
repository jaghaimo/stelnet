package stelnet.filter;

import com.fs.starfarer.api.campaign.FactionAPI;
import java.util.List;
import lombok.EqualsAndHashCode;
import stelnet.util.Includer;

@EqualsAndHashCode(callSuper = false)
public final class FactionIsAdditional extends FactionFilter {

    private final List<String> additionalFactions;

    public FactionIsAdditional() {
        additionalFactions = Includer.getAdditionalFactions();
    }

    @Override
    protected boolean acceptFaction(FactionAPI faction) {
        return additionalFactions.contains(faction.getId());
    }
}
