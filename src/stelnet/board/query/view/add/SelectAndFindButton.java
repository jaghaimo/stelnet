package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.IntelUIAPI;
import org.lwjgl.input.Keyboard;
import stelnet.board.query.QueryL10n;
import stelnet.util.L10n;
import uilib.C2Button;
import uilib.EventHandler;
import uilib.property.Size;

public class SelectAndFindButton extends C2Button {

    public SelectAndFindButton(final QueryFactory factory) {
        super(new Size(0, 30), L10n.get(QueryL10n.FIND_SELECTED), true);
        overrideSize(30);
        setShortcut(Keyboard.KEY_S);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    // QueryState state = QueryBoard.getInstance(QueryBoard.class).getState();
                    // QueryManager manager = state.getQueryManager();
                    // QueryProvider provider = factory.getProvider();
                    // Set<Filter> filters = factory.getFilters();
                    // Query query = new Query(manager, provider, filters);
                    // manager.addQuery(query);
                    // state.setActiveTab(QueryBoardTab.LIST);
                }
            }
        );
    }
}
