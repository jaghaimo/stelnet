package stelnet.board.contact2;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ContactModel {

    private final IntelInfoPlugin intelToUpdate;

    @Getter
    private final List<Contact> contacts = new ArrayList<>();

    private final FilterFactory factory = new FilterFactory();

    public void update(final List<IntelInfoPlugin> intelList) {
        contacts.clear();
        for (final IntelInfoPlugin intel : intelList) {
            final Contact contact = makeContact((ContactIntel) intel);
            contacts.add(contact);
        }
        Collections.sort(contacts);
    }

    private Contact makeContact(final ContactIntel intel) {
        final boolean hasActiveMissions = false;
        return new Contact(intel, hasActiveMissions);
    }

    public int getContactNumber() {
        return contacts.size();
    }
}
