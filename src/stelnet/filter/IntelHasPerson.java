package stelnet.filter;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.missions.hub.HubMission;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class IntelHasPerson extends IntelFilter {

    private final PersonAPI person;

    protected boolean acceptIntel(IntelInfoPlugin intel) {
        if (intel instanceof HubMission) {
            HubMission hubMission = (HubMission) intel;
            return hubMission.getPerson() == person;
        }
        return false;
    }
}
