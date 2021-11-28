package stelnet.board.contact;

import com.fs.starfarer.api.campaign.PersonImportance;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;
import stelnet.util.IntelUtils;

public class ContactProvider {

    public List<ContactIntel> getContacts(List<Filter> filters) {
        List<ContactIntel> contacts = new LinkedList<>();
        List<IntelInfoPlugin> plugins = IntelUtils.getAll(ContactIntel.class);
        for (IntelInfoPlugin plugin : plugins) {
            contacts.add(0, (ContactIntel) plugin);
        }
        CollectionUtils.reduce(contacts, filters);
        return contacts;
    }

    public List<String> getAllMissionTypes() {
        return null;
    }

    public List<PersonImportance> getAllPersonImportances() {
        return Arrays.asList(
            PersonImportance.VERY_HIGH,
            PersonImportance.HIGH,
            PersonImportance.MEDIUM,
            PersonImportance.LOW,
            PersonImportance.VERY_LOW
        );
    }
}
