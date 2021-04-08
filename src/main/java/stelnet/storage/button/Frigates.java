package stelnet.storage.button;

import stelnet.filter.fleetmember.IsNotFrigate;

public class Frigates extends Button {

    public Frigates() {
        super("Frigates", new IsNotFrigate());
    }
}
