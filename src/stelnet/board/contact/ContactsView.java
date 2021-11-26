package stelnet.board.contact;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import stelnet.util.IntelUtils;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.VerticalViewContainer;
import uilib.property.Size;

public class ContactsView implements RenderableFactory {

    @Override
    public List<Renderable> create(Size size) {
        List<Renderable> elements = new LinkedList<>();
        List<ContactIntel> contacts = getAllContacts();
        for (ContactIntel contact : contacts) {
            elements.add(new DisplayContact(contact, size.getWidth()));
        }
        return Collections.<Renderable>singletonList(new VerticalViewContainer(elements));
    }

    private List<ContactIntel> getAllContacts() {
        List<ContactIntel> contacts = new LinkedList<>();
        List<IntelInfoPlugin> plugins = IntelUtils.getAll(ContactIntel.class);
        for (IntelInfoPlugin plugin : plugins) {
            contacts.add(0, (ContactIntel) plugin);
        }
        return contacts;
    }
}
