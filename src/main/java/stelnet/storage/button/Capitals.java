package stelnet.storage.button;

import stelnet.filter.fleetmember.IsNotCapital;

public class Capitals extends Button {

    public Capitals() {
        super("Capitals", new IsNotCapital());
    }
}
