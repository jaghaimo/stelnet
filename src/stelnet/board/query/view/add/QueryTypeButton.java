package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.IntelUIAPI;
import lombok.Getter;
import stelnet.board.query.view.FilteringButton;
import uilib.EventHandler;

public class QueryTypeButton extends FilteringButton {

    @Getter
    private final QueryFactory nextFactory;

    public QueryTypeButton(final AddQueryFactory factory, final String title, final QueryFactory nextFactory) {
        super(title, null);
        this.nextFactory = nextFactory;
        final QueryTypeButton button = this;
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(final IntelUIAPI ui) {
                    factory.setQueryType(button);
                }
            }
        );
    }
}
