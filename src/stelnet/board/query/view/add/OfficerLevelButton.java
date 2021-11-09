package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.filter.PersonFilter;
import uilib.EventHandler;

public class OfficerLevelButton extends OfficerButton {

    public OfficerLevelButton(final PersonnelQueryFactory factory, String label, PersonFilter filter) {
        super(label, filter);
        final OfficerLevelButton button = this;
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    factory.setLevel(button);
                }
            }
        );
    }
}