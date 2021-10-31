package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;

public abstract class CargoStackFilter extends Filter {

    @Override
    public boolean accept(Object object) {
        if (object instanceof CargoStackAPI) {
            return acceptCargoStack((CargoStackAPI) object);
        }
        return super.accept(object);
    }

    protected abstract boolean acceptCargoStack(CargoStackAPI cargoStack);
}
