package stelnet.filter;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class IntelIsClass extends IntelFilter {

    private final Class<?> classType;

    protected boolean acceptIntel(IntelInfoPlugin intel) {
        return classType.isInstance(intel);
    }
}
