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

    private transient ContactProvider provider;
    private transient Set<ContactFilterButton> contactTypeButtons;
    private transient Set<ContactFilterButton> importanceButtons;
    private Map<MarketAPI, TrackingCargoFleetData> needingPickup;

    public ContactsState() {
        readResolve();
    }

    public int getContactNumber() {
        return ContactIntel.getCurrentContacts();
    }

    public Object readResolve() {
        if (provider == null) {
            provider = new ContactProvider();
        }
        createTypeButtons();
        createImportanceButtons();
        if (needingPickup == null) {
            needingPickup = new LinkedHashMap<>();
        }
        return this;
    }

    public void addTrackingData(MarketAPI market, CargoFleetData currentContent, CargoFleetData newContent) {
        TrackingCargoFleetData oldTrackingCargoFleetData = needingPickup.get(market);
        if (oldTrackingCargoFleetData == null) {
            TrackingCargoFleetData newTrackingCargoFleetData = new TrackingCargoFleetData(currentContent, newContent);
            needingPickup.put(market, newTrackingCargoFleetData);
        } else {
            oldTrackingCargoFleetData.add(newContent);
        }
    }

    @Override
    public List<Renderable> toRenderableList(Size size) {
        pruneNeedingPickup();
        return (new ContactsView(contactTypeButtons, importanceButtons, needingPickup)).create(size);
    }

    private void createImportanceButtons() {
        if (importanceButtons == null) {
            importanceButtons = new LinkedHashSet<>();
        }
        importanceButtons.clear();
        for (PersonImportance importance : provider.getAllPersonImportances()) {
            importanceButtons.add(
                new ContactFilterButton(importance.getDisplayName(), new ContactIsOfImportance(importance))
            );
        }
    }

    private void createTypeButtons() {
        if (contactTypeButtons == null) {
            contactTypeButtons = new TreeSet<>();
        }
        contactTypeButtons.clear();
        for (ContactTagSpec type : provider.getAllMissionTypes()) {
            contactTypeButtons.add(new ContactFilterButton(type.getName(), new AnyHasTag(type.getTag())));
        }
    }

    private void pruneNeedingPickup() {
        Set<MarketAPI> markets = new LinkedHashSet<>(needingPickup.keySet());
        for (MarketAPI market : markets) {
            pruneIfNeeded(market);
        }
    }

    private void pruneIfNeeded(MarketAPI market) {
        TrackingCargoFleetData trackingCargoFleetData = needingPickup.get(market);
        if (!trackingCargoFleetData.hasAny()) {
            needingPickup.remove(market);
        }
    }
}
