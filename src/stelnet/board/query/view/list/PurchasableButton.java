package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.CommonL10n;
import stelnet.board.query.Query;
import stelnet.util.L10n;
import uilib.EventHandler;

public class PurchasableButton extends ControlButton {

    public PurchasableButton(final Query query) {
        super(L10n.get(CommonL10n.PURCHASABLE), L10n.get(CommonL10n.ALL), true, query.isPurchasable());
        setCutStyle(CutStyle.C2_MENU);
        scaleButton(query);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    query.setPurchasable(!query.isPurchasable());
                    query.refresh();
                }
            }
        );
    }
}
