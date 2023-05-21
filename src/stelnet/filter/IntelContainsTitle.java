package stelnet.filter;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class IntelContainsTitle extends IntelFilter {

    private final String title;

    protected boolean acceptIntel(IntelInfoPlugin intel) {
        String intelTitle = intel.getSmallDescriptionTitle();
        if (intelTitle == null) {
            return false;
        }
        return intelTitle.contains(title);
    }
}
