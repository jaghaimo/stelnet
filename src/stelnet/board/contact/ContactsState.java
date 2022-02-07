package stelnet.board.contact;

import com.fs.starfarer.api.campaign.PersonImportance;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import com.fs.starfarer.api.loading.ContactTagSpec;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import stelnet.filter.AnyHasTag;
import stelnet.filter.ContactIsOfImportance;
import uilib.Renderable;
import uilib.RenderableState;
import uilib.property.Size;

public class ContactsState implements RenderableState {

    private Map<MarketAPI, TrackingCargoFleetData> awaitingCollection;
    private transient Set<ContactFilterButton> contactTypeButtons;
    private transient Set<ContactFilterButton> importanceButtons;
    private transient ContactProvider provider;

    public ContactsState() {
        readResolve();
    }

    public int getContactNumber() {
        return ContactIntel.getCurrentContacts();
    }

    public Object readResolve() {
        if (awaitingCollection == null) {
            awaitingCollection = new LinkedHashMap<>();
        }
        if (provider == null) {
            provider = new ContactProvider();
        }
        createTypeButtons();
        createImportanceButtons();
        return this;
    }

    public void addTrackingData(MarketAPI market, CargoFleetData currentContent, CargoFleetData newContent) {
        TrackingCargoFleetData oldTrackingCargoFleetData = awaitingCollection.get(market);
        if (oldTrackingCargoFleetData == null) {
            TrackingCargoFleetData newTrackingCargoFleetData = new TrackingCargoFleetData(currentContent, newContent);
            awaitingCollection.put(market, newTrackingCargoFleetData);
        } else {
            oldTrackingCargoFleetData.add(newContent);
        }
    }

    @Override
    public List<Renderable> toRenderableList(Size size) {
        pruneAwaitingCollection();
        return (new ContactsView(contactTypeButtons, importanceButtons, awaitingCollection)).create(size);
    }

    private void createImportanceButtons() {
        importanceButtons = new LinkedHashSet<>();
        for (PersonImportance importance : provider.getAllPersonImportances()) {
            importanceButtons.add(
                new ContactFilterButton(importance.getDisplayName(), new ContactIsOfImportance(importance))
            );
        }
    }

    private void createTypeButtons() {
        contactTypeButtons = new TreeSet<>();
        for (ContactTagSpec type : provider.getAllMissionTypes()) {
            contactTypeButtons.add(new ContactFilterButton(type.getName(), new AnyHasTag(type.getTag())));
        }
    }

    private void pruneAwaitingCollection() {
        Set<MarketAPI> markets = new LinkedHashSet<>(awaitingCollection.keySet());
        for (MarketAPI market : markets) {
            removeIfNeeded(market);
        }
    }

    private void removeIfNeeded(MarketAPI market) {
        TrackingCargoFleetData trackingCargoFleetData = awaitingCollection.get(market);
        if (!trackingCargoFleetData.hasAny()) {
            awaitingCollection.remove(market);
        }
    }
}
