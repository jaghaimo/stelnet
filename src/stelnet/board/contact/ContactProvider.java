package stelnet.board.contact;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PersonImportance;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import com.fs.starfarer.api.loading.ContactTagSpec;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.extern.log4j.Log4j;
import org.json.JSONException;
import org.json.JSONObject;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;

@Log4j
public class ContactProvider {

    public int getSize() {
        return ContactIntel.getCurrentContacts();
    }

    public List<ContactIntel> getContacts(List<Filter> filters) {
        List<ContactIntel> contacts = new LinkedList<>();
        List<IntelInfoPlugin> plugins = Global.getSector().getIntelManager().getIntel(ContactIntel.class);
        for (IntelInfoPlugin plugin : plugins) {
            contacts.add(0, (ContactIntel) plugin);
        }
        CollectionUtils.reduce(contacts, filters);
        return contacts;
    }

    public Set<ContactTagSpec> getAllMissionTypes() {
        Set<ContactTagSpec> contactSpecs = new LinkedHashSet<>();
        JSONObject types;
        try {
            types = Global.getSettings().getMergedJSONForMod("data/config/contact_tag_data.json", "");
            @SuppressWarnings("unchecked")
            Iterator<String> iterator = types.keys();
            while (iterator.hasNext()) {
                try {
                    String tag = iterator.next();
                    JSONObject object = types.getJSONObject(tag);
                    contactSpecs.add(new ContactTagSpec(tag, null, object));
                } catch (JSONException e) {
                    log.error("Failed reading JSON content", e);
                }
            }
        } catch (IOException | JSONException e) {
            log.error("Failed reading merged JSON file", e);
        }

        return contactSpecs;
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
