package stelnet.filter;

import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class IntelContainsTitle extends IntelFilter {

    private final String title;

    protected boolean acceptIntel(BaseIntelPlugin intel) {
        String intelTitle = intel.getSmallDescriptionTitle();
        if (intelTitle == null) {
            return false;
        }
        return intelTitle.contains(title);
    }
}
