package stelnet.storage.button;

import stelnet.filter.cargostack.IsNotOther;

public class Others extends Button {

    public Others() {
        super("Other Items", new IsNotOther());
    }
}
