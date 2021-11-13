package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;
import stelnet.board.query.QueryBoard;
import uilib.Button;
import uilib.EventHandler;
import uilib.property.Size;

public class DeleteAllButton extends Button {

    public DeleteAllButton() {
        super(new Size(300, 24), "Delete All", true, Misc.getNegativeHighlightColor());
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    QueryBoard queryBoard = QueryBoard.getInstance(QueryBoard.class);
                    queryBoard.getState().getQueryManger().deleteAll();
                }
            }
        );
    }
}
