package stelnet.market.view;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.util.Misc;

import stelnet.market.IntelQuery;
import stelnet.ui.Paragraph;
import stelnet.ui.property.Location;
import stelnet.ui.property.Position;

public class IntelCount extends Paragraph {

    public IntelCount(IntelQuery query) {
        super(query.getResultCount(), 100);
        setLocation(Location.TOP_RIGHT);
        setOffset(new Position(10, -18));
        setAlignment(Alignment.TMID);
        if (!query.isEnabled()) {
            setColor(Misc.getGrayColor());
        }
    }
}
