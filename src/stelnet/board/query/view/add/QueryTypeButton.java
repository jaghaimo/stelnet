package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.IntelUIAPI;

import lombok.Getter;
import stelnet.util.L10n;
import uilib.AreaCheckbox;
import uilib.EventHandler;
import uilib.RenderableFactory;
import uilib.property.Size;

public class QueryTypeButton extends AreaCheckbox {

    @Getter
    private RenderableFactory nextFactory;

    public QueryTypeButton(final AddQueryFactory factory, Enum<?> translationId, RenderableFactory nextFactory) {
        super(new Size(0, 24), L10n.get(translationId), true, false);
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
