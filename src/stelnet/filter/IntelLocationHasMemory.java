package stelnet.filter;

import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class IntelLocationHasMemory extends IntelFilter {

    private final String memoryKey;

    protected boolean acceptIntel(IntelInfoPlugin intel) {
        SectorEntityToken token = intel.getMapLocation(null);
        if (token == null) {
            return false;
        }
        return token.getMemoryWithoutUpdate().getBoolean(memoryKey);
    }
}
