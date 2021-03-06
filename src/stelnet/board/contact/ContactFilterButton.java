package stelnet.board.contact;

import com.fs.starfarer.api.util.Misc;
import lombok.Getter;
import stelnet.filter.Filter;
import uilib.AreaCheckbox;
import uilib.UiConstants;
import uilib.property.Size;

@Getter
public class ContactFilterButton extends AreaCheckbox implements Comparable<ContactFilterButton> {

    private final String name;
    private final Filter filter;

    public ContactFilterButton(String translatedString, Filter filter) {
        super(new Size(150, UiConstants.DEFAULT_BUTTON_HEIGHT), Misc.ucFirst(translatedString), true, true);
        this.name = translatedString;
        this.filter = filter;
        setPadding(0);
    }

    @Override
    public int compareTo(ContactFilterButton o) {
        String self = name + getFilter();
        String other = o.getName() + o.getFilter();
        return self.compareTo(other);
    }
}
