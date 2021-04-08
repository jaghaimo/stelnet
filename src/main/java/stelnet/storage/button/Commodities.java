package stelnet.storage.button;

import stelnet.filter.cargostack.IsNotCommodity;

public class Commodities extends Button {

    public Commodities() {
        super("Commodities", new IsNotCommodity());
    }
}
