package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.filter.Filter;
import uilib.EventHandler;

public class FighterBaysButton extends FilteringButton {

    public FighterBaysButton(final ShipQueryFactory factory, String label, Filter filter) {
        super(label, filter);
        final FighterBaysButton button = this;
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    factory.setFighterBays(button);
                }
            }
        );
    }
}
