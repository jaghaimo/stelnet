package stelnet.board.contact;

import com.fs.starfarer.api.campaign.PersonImportance;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
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
        return provider.getSize();
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

    @Override
    public List<Renderable> toRenderableList(final Size size) {
        pruneAwaitingCollection();
        return (new ContactsView(contactTypeButtons, importanceButtons, awaitingCollection)).create(size);
    }

    private void createImportanceButtons() {
        importanceButtons = new LinkedHashSet<>();
        for (final PersonImportance importance : provider.getAllPersonImportances()) {
            importanceButtons.add(
                new ContactFilterButton(importance.getDisplayName(), new ContactIsOfImportance(importance))
            );
        }
    }

    private void createTypeButtons() {
        contactTypeButtons = new TreeSet<>();
        for (final ContactTagSpec type : provider.getAllMissionTypes()) {
            contactTypeButtons.add(new ContactFilterButton(type.getName(), new AnyHasTag(type.getTag())));
        }
    }

    private void pruneAwaitingCollection() {
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
}
