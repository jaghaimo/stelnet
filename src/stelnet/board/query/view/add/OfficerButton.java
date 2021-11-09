package stelnet.board.query.view.add;

import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import java.util.Arrays;
import lombok.Getter;
import stelnet.filter.Filter;
import stelnet.filter.LogicalNot;
import stelnet.filter.LogicalOr;
import stelnet.filter.PersonIsPostedAs;
import uilib.AreaCheckbox;
import uilib.property.Size;

public class OfficerButton extends AreaCheckbox implements FilteringButton {

    @Getter
    private final Filter filter;

    public OfficerButton(String translatedString, Filter filter) {
        super(new Size(0, 24), translatedString, true, false);
        this.filter = getOfficerFilter(filter);
    }

    public Filter getOfficerFilter(Filter filter) {
        Filter isOfficerOrMercenary = new LogicalOr(
            Arrays.<Filter>asList(
                new PersonIsPostedAs(Ranks.POST_MERCENARY),
                new PersonIsPostedAs(Ranks.POST_OFFICER_FOR_HIRE)
            )
        );
        return new LogicalOr(Arrays.<Filter>asList(new LogicalNot(isOfficerOrMercenary), filter));
    }
}
