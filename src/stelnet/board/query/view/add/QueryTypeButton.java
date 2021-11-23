package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.IntelUIAPI;
import lombok.Getter;
import stelnet.util.L10n;
import uilib.EventHandler;

public class QueryTypeButton extends FilteringButton {

    @Getter
    private QueryFactory nextFactory;

    public QueryTypeButton(final AddQueryFactory factory, Enum<?> translationId, QueryFactory nextFactory) {
        super(L10n.get(translationId), null, false);
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
