package stelnet.market.view;

import com.fs.starfarer.api.util.Misc;

import stelnet.market.IntelQuery;
import stelnet.ui.Location;
import stelnet.ui.Paragraph;
import stelnet.ui.Position;

public class IntelCount extends Paragraph {

    public IntelCount(IntelQuery query) {
        super(query.getResultCount(), 80);
        setLocation(Location.TOP_RIGHT);
        setOffset(new Position(10, -18));
        if (!query.isActive()) {
            setColor(Misc.getGrayColor());
        }
    }
}
