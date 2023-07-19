package stelnet.filters.intel;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class IntelHasTag extends IntelFilter {

    private final String tag;

    @Override
    public boolean accept(final IntelInfoPlugin object) {
        final Set<String> tags = object.getIntelTags(null);
        if (tags == null) {
            return false;
        }
        return tags.contains(tag);
    }
}
