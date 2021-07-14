package stelnet.market_old.view;

import com.fs.starfarer.api.util.Misc;

import stelnet.market_old.intel.IntelQuery;
import stelnet.ui.Paragraph;
import stelnet.ui.property.Position;

public class IntelDescription extends Paragraph {

    public IntelDescription(IntelQuery query, float width) {
        super(query.getDescription(), width);
        setOffset(new Position(0, 2));
        if (!query.isEnabled()) {
            setColor(Misc.getGrayColor());
        }
    }
}
