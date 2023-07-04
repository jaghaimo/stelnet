package stelnet.board.contact;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import com.fs.starfarer.api.loading.ContactTagSpec;
import java.io.IOException;
import java.util.*;
import lombok.extern.log4j.Log4j;
import org.json.JSONException;
import org.json.JSONObject;
import stelnet.filter.ContactIsValid;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;

@Log4j
public class ContactProvider {

    public int getSize() {
        final List<Filter> filters = new LinkedList<>();
        return getContacts(filters).size();
    }

    public List<ContactIntel> getContacts(final List<Filter> filters) {
        final List<ContactIntel> contacts = new LinkedList<>();
        final List<IntelInfoPlugin> plugins = Global.getSector().getIntelManager().getIntel(ContactIntel.class);
        for (final IntelInfoPlugin plugin : plugins) {
            contacts.add(0, (ContactIntel) plugin);
        }
        filters.add(new ContactIsValid());
        CollectionUtils.reduce(contacts, filters);
        return contacts;
    }

    public Set<ContactTagSpec> getAllMissionTypes() {
        final Set<ContactTagSpec> contactSpecs = new LinkedHashSet<>();
        final JSONObject types;
        try {
            types = Global.getSettings().getMergedJSONForMod("data/config/contact_tag_data.json", "");
            @SuppressWarnings("unchecked")
            final Iterator<String> iterator = types.keys();
            while (iterator.hasNext()) {
                try {
                    final String tag = iterator.next();
                    final JSONObject object = types.getJSONObject(tag);
                    contactSpecs.add(new ContactTagSpec(tag, null, object));
                } catch (final JSONException e) {
                    log.error("Failed reading JSON content", e);
                }
            }
        } catch (final IOException | JSONException e) {
            log.error("Failed reading merged JSON file", e);
        }

        return contactSpecs;
    }
}
