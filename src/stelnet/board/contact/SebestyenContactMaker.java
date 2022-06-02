package stelnet.board.contact;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PersonImportance;
import com.fs.starfarer.api.campaign.PlayerMarketTransaction;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.listeners.ColonyInteractionListener;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.People;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import java.util.List;
import stelnet.util.ConfigConstants;

/**
 * Creates contacts out of normal (e.g. Sebestyen) comm people.
 */
public class SebestyenContactMaker implements ColonyInteractionListener {

    public static void register() {
        if (ConfigConstants.CONTACTS_ADD_SEBESTYEN) {
            SebestyenContactMaker maker = new SebestyenContactMaker();
            Global.getSector().getListenerManager().addListener(maker, true);
        }
    }

    @Override
    public void reportPlayerOpenedMarket(MarketAPI market) {}

    @Override
    public void reportPlayerClosedMarket(MarketAPI market) {
        if (!needContact()) {
            return;
        }
        addStorage();
        addContact();
    }

    @Override
    public void reportPlayerOpenedMarketAndCargoUpdated(MarketAPI market) {}

    @Override
    public void reportPlayerMarketTransaction(PlayerMarketTransaction transaction) {}

    private boolean needContact() {
        boolean hasContact = getContactIntel() != null;
        if (hasContact) {
            return false;
        }
        if (!hasMetProvost()) {
            return false;
        }
        return getPerson() != null;
    }

    private void addContact() {
        PersonAPI person = getPerson();
        person.addTag(Tags.CONTACT_SCIENCE);
        person.setImportance(PersonImportance.VERY_HIGH);
        ContactIntel contact = new SebestyenContactIntel(person, person.getMarket());
        Global.getSector().getIntelManager().addIntel(contact);
    }

    private void addStorage() {
        PersonAPI person = getPerson();
        MarketAPI market = person.getMarket();
        if (!market.hasSubmarket(Submarkets.SUBMARKET_STORAGE)) {
            market.addSubmarket(Submarkets.SUBMARKET_STORAGE);
        }
    }

    private SebestyenContactIntel getContactIntel() {
        List<IntelInfoPlugin> plugins = Global.getSector().getIntelManager().getIntel(SebestyenContactIntel.class);
        if (plugins.isEmpty()) {
            return null;
        }
        return (SebestyenContactIntel) plugins.get(0);
    }

    private PersonAPI getPerson() {
        return Global.getSector().getImportantPeople().getPerson(People.SEBESTYEN);
    }

    private boolean hasMetProvost() {
        return Global.getSector().getMemoryWithoutUpdate().getBoolean("$metBaird");
    }
}
