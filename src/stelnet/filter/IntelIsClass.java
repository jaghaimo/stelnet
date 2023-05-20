package stelnet.filter;

import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class IntelIsClass extends IntelFilter {

    private final Class<?> classType;

    protected boolean acceptIntel(BaseIntelPlugin intel) {
        return classType.isInstance(intel);
    }
}
