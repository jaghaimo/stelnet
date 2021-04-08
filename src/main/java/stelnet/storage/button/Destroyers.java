package stelnet.storage.button;

import stelnet.filter.fleetmember.IsNotDestroyer;

public class Destroyers extends Button {

    public Destroyers() {
        super("Destroyers", new IsNotDestroyer());
    }
}
