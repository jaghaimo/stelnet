package stelnet.board.query.view.add;

import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import com.fs.starfarer.api.util.Misc;
import java.util.Arrays;
import lombok.Getter;
import stelnet.filter.Filter;
import stelnet.filter.LogicalNotFilter;
import stelnet.filter.LogicalOrFilter;
import stelnet.filter.PersonIsPostedAs;
import stelnet.util.L10n;
import uilib.AreaCheckbox;
import uilib.property.Size;

public class OfficerButton extends AreaCheckbox implements FilteringButton {

    @Getter
    private final Filter filter;

    public OfficerButton(String translationId, Filter filter) {
        super(new Size(0, 24), L10n.get(translationId), true, false, Misc.getButtonTextColor(), Misc.getGrayColor());
        this.filter = getOfficerFilter(filter);
    }

    public Filter getOfficerFilter(Filter filter) {
        Filter isOfficerOrMercenary = new LogicalOrFilter(
            Arrays.<Filter>asList(
                new PersonIsPostedAs(Ranks.POST_MERCENARY),
                new PersonIsPostedAs(Ranks.POST_OFFICER_FOR_HIRE)
            )
        );
        return new LogicalOrFilter(Arrays.<Filter>asList(new LogicalNotFilter(isOfficerOrMercenary), filter));
    }
}
