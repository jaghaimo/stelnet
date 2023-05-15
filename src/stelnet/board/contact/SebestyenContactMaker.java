package stelnet.board.contact;

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
import stelnet.util.ModSettings;

/**
 * Creates a ContactIntel for Sebestyen.
 */
public class SebestyenContactMaker implements ColonyInteractionListener {

    private final String MET_BAIRD_MEM_KEY = "$metBaird";

    public static void register() {
        if (ModSettings.has(ModSettings.SEBESTYEN)) {
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
        if (!fixMarket()) {
            return;
        }
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
        MarketAPI market = getGalatia();
        fixPerson(person, market);
        ContactIntel contact = new SebestyenContactIntel(person, market);
        Global.getSector().getIntelManager().addIntel(contact);
    }

    private boolean fixMarket() {
        MarketAPI market = getGalatia();
        if (market == null) {
            return false;
        }
        if (!market.hasSubmarket(Submarkets.SUBMARKET_STORAGE)) {
            market.addSubmarket(Submarkets.SUBMARKET_STORAGE);
            market.removeCondition(Conditions.DECIVILIZED);
        }
        return true;
    }

    private void fixPerson(PersonAPI person, MarketAPI market) {
        person.setMarket(market);
        person.addTag(Tags.CONTACT_SCIENCE);
        person.setImportance(PersonImportance.VERY_HIGH);
    }

    private SebestyenContactIntel getContactIntel() {
        List<IntelInfoPlugin> plugins = Global.getSector().getIntelManager().getIntel(SebestyenContactIntel.class);
        if (plugins.isEmpty()) {
            return null;
        }
        return (SebestyenContactIntel) plugins.get(0);
    }

    private MarketAPI getGalatia() {
        SectorEntityToken token = Global.getSector().getEntityById("station_galatia_academy");
        if (token == null) {
            return null;
        }
        return (MarketAPI) token.getMarket();
    }

    private PersonAPI getPerson() {
        return Global.getSector().getImportantPeople().getPerson(People.SEBESTYEN);
    }

    private boolean hasMetProvost() {
        return Global.getSector().getMemoryWithoutUpdate().getBoolean(MET_BAIRD_MEM_KEY);
    }
}
