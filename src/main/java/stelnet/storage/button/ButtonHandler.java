package stelnet.storage.button;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stelnet.storage.StorageBoard;

public interface ButtonHandler {

    public void handle(StorageBoard board, IntelUIAPI ui);
}
