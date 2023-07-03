package stelnet.filters.intel;

import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class IntelLocationHasMemory extends IntelFilter {

    private final String memoryKey;

    @Override
    public boolean accept(final IntelInfoPlugin object) {
        final SectorEntityToken token = object.getMapLocation(null);
        if (token == null) {
            return false;
        }
        return token.getMemoryWithoutUpdate().getBoolean(memoryKey);
    }
}
