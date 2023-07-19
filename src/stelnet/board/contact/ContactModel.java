package stelnet.board.contact;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import java.util.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import stelnet.board.contact.fleetdata.CargoFleetData;
import stelnet.board.contact.fleetdata.TrackingCargoFleetData;
import stelnet.filters.FilterHelper;
import stelnet.filters.intel.IntelHasPerson;
import stelnet.filters.intel.IntelIsActive;
import stelnet.settings.BooleanSettings;
import stelnet.settings.Modules;
import stelnet.util.MemoryManager;
import stelnet.util.ModConstants;

@RequiredArgsConstructor
public class ContactModel {

    private final IntelInfoPlugin contactBoard;

    @Getter
    private final List<Contact> contacts = new ArrayList<>();

    private final List<IntelInfoPlugin> missions = new ArrayList<>();

    @Getter
    private final Map<MarketAPI, TrackingCargoFleetData> awaitingCollection = new HashMap<>();

    public void addTrackingData(
        final MarketAPI market,
        final CargoFleetData currentContent,
        final CargoFleetData newContent
    ) {
        final TrackingCargoFleetData oldTrackingCargoFleetData = awaitingCollection.get(market);
        if (oldTrackingCargoFleetData == null) {
            final TrackingCargoFleetData newTrackingCargoFleetData = new TrackingCargoFleetData(
                currentContent,
                newContent
            );
            awaitingCollection.put(market, newTrackingCargoFleetData);
        } else {
            oldTrackingCargoFleetData.add(newContent);
        }
    }

    public void updateContacts(final List<IntelInfoPlugin> contactList) {
        contacts.clear();
        for (final IntelInfoPlugin intel : contactList) {
            final Contact contact = makeContact((ContactIntel) intel);
            contacts.add(contact);
        }
        Collections.sort(contacts);
    }

    private Contact makeContact(final ContactIntel contactIntel) {
        final Contact contact = new Contact(contactBoard, contactIntel);
        if (contact.isValid()) {
            contact.setCallEnabled(isCallEnabled(contact.getPerson(), contact.getMarket()));
        }
        return contact;
    }

    private boolean isCallEnabled(final PersonAPI person, final MarketAPI market) {
        final boolean wouldBeHidden = Modules.CONTACTS.isHidden();
        final boolean hasMissions = hasActiveMission(person) && BooleanSettings.CONTACTS_MISSIONLESS.get();
        final boolean hasSubmarket = market.hasSubmarket(Submarkets.SUBMARKET_STORAGE);
        final boolean isCalling = MemoryManager.getInstance().getBoolean(ModConstants.MEMORY_IS_CALLING);
        return !wouldBeHidden && !hasMissions && hasSubmarket && !isCalling;
    }

    private boolean hasActiveMission(final PersonAPI person) {
        final List<IntelInfoPlugin> localMissions = new ArrayList<>(missions);
        FilterHelper.reduce(localMissions, new IntelHasPerson(person));
        FilterHelper.reduce(localMissions, new IntelIsActive());
        return !localMissions.isEmpty();
    }

    public void updateMissions(final List<IntelInfoPlugin> missionList) {
        missions.clear();
        missions.addAll(missionList);
    }

    public void pruneAwaitingCollection() {
        final Set<MarketAPI> markets = new LinkedHashSet<>(awaitingCollection.keySet());
        for (final MarketAPI market : markets) {
            removeIfNeeded(market);
        }
    }

    private void removeIfNeeded(final MarketAPI market) {
        final TrackingCargoFleetData trackingCargoFleetData = awaitingCollection.get(market);
        if (!trackingCargoFleetData.hasAny()) {
            awaitingCollection.remove(market);
        }
    }

    public int getContactNumber() {
        return contacts.size();
    }
}
