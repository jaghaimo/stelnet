package stelnet.board.query.view.list;

import com.fs.starfarer.api.campaign.econ.SubmarketSpecAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import lombok.Setter;
import stelnet.board.query.QueryManager;
import uilib.AreaCheckbox;
import uilib.UiConstants;
import uilib.property.Size;

@Setter
public class SubmarketButton extends AreaCheckbox {

    private QueryManager manager;
    private String submarket;

    public SubmarketButton(QueryManager manager, SubmarketSpecAPI submarket, float width) {
        super(
            new Size(width, UiConstants.DEFAULT_BUTTON_HEIGHT),
            submarket.getName().replace("\n", " "),
            true,
            manager.getActiveSubmarkets().contains(submarket.getId())
        );
        setPadding(1);
        setManager(manager);
        setSubmarket(submarket.getId());
        if (getTitle().trim().isEmpty()) {
            setTitle("Military Market");
        }
    }

    @Override
    public void onConfirm(IntelUIAPI ui) {
        super.onConfirm(ui);
        if (isStateOn()) {
            manager.getActiveSubmarkets().add(submarket);
        } else {
            manager.getActiveSubmarkets().remove(submarket);
        }
    }
}
