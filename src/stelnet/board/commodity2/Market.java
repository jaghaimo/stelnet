package stelnet.board.commodity2;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import lombok.RequiredArgsConstructor;
import uilib2.Drawable;

@RequiredArgsConstructor
public class Market implements Drawable {

    private final MarketAPI market;

    @Override
    public UIComponentAPI draw(final TooltipMakerAPI tooltip) {
        return null;
    }
}
