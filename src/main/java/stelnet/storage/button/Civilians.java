package stelnet.storage.button;

import stelnet.filter.fleetmember.IsNotCivilian;

public class Civilians extends Button {

    public Civilians() {
        super("Civilians", new IsNotCivilian());
    }
}
