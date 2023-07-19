package stelnet.filters.intel;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class IntelIsHidden extends IntelFilter {

    @Override
    public boolean accept(final IntelInfoPlugin object) {
        return object.isHidden();
    }
}
