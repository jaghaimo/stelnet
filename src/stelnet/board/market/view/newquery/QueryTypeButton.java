package stelnet.board.market.view.newquery;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;
import lombok.Getter;
import stelnet.util.L10n;
import uilib.AreaCheckbox;
import uilib.EventHandler;
import uilib.RenderableFactory;
import uilib.property.Size;

public class QueryTypeButton extends AreaCheckbox {

    @Getter
    private RenderableFactory nextFactory;

    public QueryTypeButton(final QueryTypeContainer factory, String translationId, RenderableFactory nextFactory) {
        super(new Size(0, 24), L10n.get(translationId), true, false, Misc.getButtonTextColor(), Misc.getGrayColor());
        this.nextFactory = nextFactory;
        final QueryTypeButton button = this;
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    factory.setQueryType(button);
                }
            }
        );
    }
}
