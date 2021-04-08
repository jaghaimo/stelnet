package stelnet.storage.button;

import stelnet.filter.fleetmember.IsNotWarship;

public class Warships extends Button {

    public Warships() {
        super("Warships", new IsNotWarship());
    }
}
