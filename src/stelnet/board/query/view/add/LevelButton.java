package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.filter.PersonFilter;
import uilib.EventHandler;

public class LevelButton extends OfficerButton {

    public LevelButton(final PersonnelQueryFactory factory, String translationId, PersonFilter filter) {
        super(translationId, filter);
        final LevelButton button = this;
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
