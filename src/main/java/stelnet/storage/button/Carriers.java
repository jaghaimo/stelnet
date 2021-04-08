package stelnet.storage.button;

import stelnet.filter.fleetmember.IsNotCarrier;

public class Carriers extends Button {

    public Carriers() {
        super("Carriers", new IsNotCarrier());
    }
}
