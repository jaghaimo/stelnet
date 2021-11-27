package stelnet.filter;

import com.fs.starfarer.api.campaign.PersonImportance;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ContactIsOfImportance extends Filter {

    private final PersonImportance importance;

    @Override
    public boolean accept(Object object) {
        if (object instanceof ContactIntel) {
            return acceptContact((ContactIntel) object);
        }
        return super.accept(object);
    }

    public boolean acceptContact(ContactIntel intel) {
        return intel.getPerson().getImportance() == importance;
    }
}
