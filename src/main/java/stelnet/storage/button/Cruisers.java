package stelnet.storage.button;

import stelnet.filter.fleetmember.IsNotCruiser;

public class Cruisers extends Button {

    public Cruisers() {
        super("Cruisers", new IsNotCruiser());
    }
}
