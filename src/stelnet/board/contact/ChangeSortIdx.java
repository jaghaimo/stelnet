package stelnet.board.contact;

import com.fs.starfarer.api.ui.IntelUIAPI;
import lombok.RequiredArgsConstructor;
import uilib2.intel.IntelUiAction;

@RequiredArgsConstructor
public class ChangeSortIdx implements IntelUiAction {

    private final Contact contact;
    private final int direction;

    @Override
    public void act(final IntelUIAPI ui) {
        contact.changeSortIndex(direction);
    }
}
