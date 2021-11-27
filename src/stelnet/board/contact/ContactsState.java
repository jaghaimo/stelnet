package stelnet.board.contact;

import com.fs.starfarer.api.campaign.PersonImportance;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import java.util.List;
import uilib.Renderable;
import uilib.RenderableState;
import uilib.property.Size;

public class ContactsState implements RenderableState {

    private final FilteringButton[] importanceButtons = createImportanceButtons();
    private final FilteringButton[] contactTypeButtons = createTypeButtons();

    public int getContactNumber() {
        return ContactIntel.getCurrentContacts();
    }

    @Override
    public List<Renderable> toRenderableList(Size size) {
        return (new ContactsView(importanceButtons, contactTypeButtons)).create(size);
    }

    public FilteringButton[] createImportanceButtons() {
        return new FilteringButton[] {
            new FilteringButton(PersonImportance.VERY_HIGH.getDisplayName(), null),
            new FilteringButton(PersonImportance.HIGH.getDisplayName(), null),
            new FilteringButton(PersonImportance.MEDIUM.getDisplayName(), null),
            new FilteringButton(PersonImportance.LOW.getDisplayName(), null),
            new FilteringButton(PersonImportance.VERY_LOW.getDisplayName(), null),
        };
    }

    public FilteringButton[] createTypeButtons() {
        return new FilteringButton[] {
            new FilteringButton(Tags.CONTACT_MILITARY, null),
            new FilteringButton(Tags.CONTACT_PATHER, null),
            new FilteringButton(Tags.CONTACT_SCIENCE, null),
            new FilteringButton(Tags.CONTACT_TRADE, null),
            new FilteringButton(Tags.CONTACT_UNDERWORLD, null),
        };
    }
}
