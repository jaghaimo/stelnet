package stelnet.board.contact;

import com.fs.starfarer.api.campaign.PersonImportance;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import com.fs.starfarer.api.loading.ContactTagSpec;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import stelnet.filter.AnyHasTag;
import stelnet.filter.ContactIsOfImportance;
import uilib.Renderable;
import uilib.RenderableState;
import uilib.property.Size;

public class ContactsState implements RenderableState {

    private final ContactProvider provider = new ContactProvider();
    private final Set<ContactFilterButton> contactTypeButtons = new TreeSet<>();
    private final Set<ContactFilterButton> importanceButtons = new LinkedHashSet<>();

    public ContactsState() {
        createImportanceButtons();
        createTypeButtons();
    }

    public int getContactNumber() {
        return ContactIntel.getCurrentContacts();
    }

    @Override
    public List<Renderable> toRenderableList(Size size) {
        return (new ContactsView(contactTypeButtons, importanceButtons)).create(size);
    }

    public void createImportanceButtons() {
        importanceButtons.clear();
        for (PersonImportance importance : provider.getAllPersonImportances()) {
            importanceButtons.add(
                new ContactFilterButton(importance.getDisplayName(), new ContactIsOfImportance(importance))
            );
        }
    }

    public void createTypeButtons() {
        contactTypeButtons.clear();
        for (ContactTagSpec type : provider.getAllMissionTypes()) {
            contactTypeButtons.add(new ContactFilterButton(type.getName(), new AnyHasTag(type.getTag())));
        }
    }
}
