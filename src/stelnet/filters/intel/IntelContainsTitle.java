package stelnet.filters.intel;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class IntelContainsTitle extends IntelFilter {

    private final String title;

    @Override
    public boolean accept(final IntelInfoPlugin object) {
        final String intelTitle = object.getSmallDescriptionTitle();
        if (intelTitle == null) {
            return false;
        }
        return intelTitle.contains(title);
    }
}
