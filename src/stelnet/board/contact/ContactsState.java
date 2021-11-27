package stelnet.board.contact;

import com.fs.starfarer.api.campaign.PersonImportance;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import java.util.List;
import stelnet.filter.AnyHasTag;
import uilib.Renderable;
import uilib.RenderableState;
import uilib.property.Size;

public class ContactsState implements RenderableState {

    private final ContactFilterButton[] importanceButtons = createImportanceButtons();
    private final ContactFilterButton[] contactTypeButtons = createTypeButtons();

    public int getContactNumber() {
        return ContactIntel.getCurrentContacts();
    }

    @Override
    public List<Renderable> toRenderableList(Size size) {
        return (new ContactsView(importanceButtons, contactTypeButtons)).create(size);
    }

    public ContactFilterButton[] createImportanceButtons() {
        return new ContactFilterButton[] {
            new ContactFilterButton(PersonImportance.VERY_HIGH.getDisplayName(), null),
            new ContactFilterButton(PersonImportance.HIGH.getDisplayName(), null),
            new ContactFilterButton(PersonImportance.MEDIUM.getDisplayName(), null),
            new ContactFilterButton(PersonImportance.LOW.getDisplayName(), null),
            new ContactFilterButton(PersonImportance.VERY_LOW.getDisplayName(), null),
        };
    }

    public ContactFilterButton[] createTypeButtons() {
        return new ContactFilterButton[] {
            new ContactFilterButton(Tags.CONTACT_MILITARY, new AnyHasTag(Tags.CONTACT_MILITARY)),
            new ContactFilterButton(Tags.CONTACT_PATHER, new AnyHasTag(Tags.CONTACT_PATHER)),
            new ContactFilterButton(Tags.CONTACT_SCIENCE, new AnyHasTag(Tags.CONTACT_SCIENCE)),
            new ContactFilterButton(Tags.CONTACT_TRADE, new AnyHasTag(Tags.CONTACT_TRADE)),
            new ContactFilterButton(Tags.CONTACT_UNDERWORLD, new AnyHasTag(Tags.CONTACT_UNDERWORLD)),
        };
    }
}
