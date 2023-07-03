package stelnet.filters.intel;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.missions.hub.HubMission;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class IntelHasPerson extends IntelFilter {

    private final PersonAPI person;

    @Override
    public boolean accept(final IntelInfoPlugin object) {
        if (object instanceof HubMission) {
            final HubMission hubMission = (HubMission) object;
            return hubMission.getPerson() == person;
        }
        return false;
    }
}
