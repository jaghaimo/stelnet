package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class CargoStackIsStack extends CargoStackFilter {

    private final CargoStackAPI stack;

    @Override
    protected boolean acceptCargoStack(CargoStackAPI cargoStack) {
        // naive comparison but simplest implementation
        return stack.getDisplayName().equals(cargoStack.getDisplayName());
    }

    @Override
    public String toString() {
        return stack.getDisplayName();
    }
}
