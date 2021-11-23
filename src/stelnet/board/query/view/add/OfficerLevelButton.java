package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.filter.Filter;
import uilib.EventHandler;

public class OfficerLevelButton extends FilteringButton {

    public OfficerLevelButton(final PersonnelQueryFactory factory, String label, Filter filter) {
        super(label, filter, false);
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
