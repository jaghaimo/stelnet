package stelnet.board.contact.sebestyen;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PersonImportance;
import com.fs.starfarer.api.campaign.PlayerMarketTransaction;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.listeners.ColonyInteractionListener;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.People;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import java.util.List;
import stelnet.settings.BooleanSettings;

/**
 * Creates a ContactIntel for Sebestyen.
 */
public class SebestyenContactMaker implements ColonyInteractionListener {

    public static void register() {
        if (BooleanSettings.CONTACTS_SEBESTYEN.get()) {
            final SebestyenContactMaker maker = new SebestyenContactMaker();
            Global.getSector().getListenerManager().addListener(maker, true);
        }
    }

    @Override
    public void reportPlayerOpenedMarket(final MarketAPI market) {}

    @Override
    public void reportPlayerClosedMarket(final MarketAPI market) {
        if (!needContact()) {
            return;
        }
        if (!fixMarket()) {
            return;
        }
        addContact();
    }

    @Override
    public void reportPlayerOpenedMarketAndCargoUpdated(final MarketAPI market) {}

    @Override
    public void reportPlayerMarketTransaction(final PlayerMarketTransaction transaction) {}

    private boolean needContact() {
        final boolean hasContact = getContactIntel() != null;
        if (hasContact) {
            return false;
        }
        if (!hasMetProvost()) {
            return false;
        }
        return getPerson() != null;
    }

    /**
     * Change Galatia "market" to be contact-worthy.
     */
    private boolean fixMarket() {
        final MarketAPI market = getGalatia();
        if (market == null) {
            return false;
        }
        // Stelnet needs a submarket for remote call functionality.
        if (!market.hasSubmarket(Submarkets.SUBMARKET_STORAGE)) {
            market.addSubmarket(Submarkets.SUBMARKET_STORAGE);
        }
        // Contacts refuse to occupy decivilized locations.
        if (market.hasCondition(Conditions.DECIVILIZED)) {
            market.removeCondition(Conditions.DECIVILIZED);
        }
        return true;
    }

    private void addContact() {
        final PersonAPI person = getPerson();
        final MarketAPI market = getGalatia();
        fixPerson(person, market);
        final ContactIntel contact = new SebestyenContactIntel(person, market);
        Global.getSector().getIntelManager().addIntel(contact);
    }

    private SebestyenContactIntel getContactIntel() {
        final List<IntelInfoPlugin> plugins = Global
            .getSector()
            .getIntelManager()
            .getIntel(SebestyenContactIntel.class);
        if (plugins.isEmpty()) {
            return null;
        }
        return (SebestyenContactIntel) plugins.get(0);
    }

    private boolean hasMetProvost() {
        final String MET_BAIRD_MEM_KEY = "$metBaird";
        return Global.getSector().getPlayerMemoryWithoutUpdate().getBoolean(MET_BAIRD_MEM_KEY);
    }

    private PersonAPI getPerson() {
        return Global.getSector().getImportantPeople().getPerson(People.SEBESTYEN);
    }

    private MarketAPI getGalatia() {
        final SectorEntityToken token = Global.getSector().getEntityById("station_galatia_academy");
        if (token == null) {
            return null;
        }
        return token.getMarket();
    }

    /**
     * Make Sebestyen a more "solid" person.
     */
    private void fixPerson(final PersonAPI person, final MarketAPI market) {
        person.setMarket(market);
        person.addTag(Tags.CONTACT_SCIENCE);
        person.setImportance(PersonImportance.VERY_HIGH);
    }
}
