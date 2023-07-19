package stelnet.filters.intel;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class IntelIsClass extends IntelFilter {

    private final Class<?> classType;

    @Override
    public boolean accept(final IntelInfoPlugin object) {
        return classType.isInstance(object);
    }
}
