package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;
import lombok.Getter;
import stelnet.filter.person.PersonFilter;
import stelnet.util.L10n;
import uilib.AreaCheckbox;
import uilib.EventHandler;
import uilib.property.Size;

public class LevelButton extends AreaCheckbox {

    @Getter
    private final PersonFilter filter;

    public LevelButton(final PersonnelButtonFactory factory, String translationId, PersonFilter filter) {
        super(new Size(30, 24), L10n.get(translationId), true, false, Misc.getButtonTextColor(), Misc.getGrayColor());
        this.filter = filter;
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
