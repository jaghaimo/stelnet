package stelnet.board.contact;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;

public class SebestyenContactIntel extends ContactIntel {

    public SebestyenContactIntel(PersonAPI person, MarketAPI market) {
        super(person, market);
    }

    @Override
    public void doPeriodicCheck() {}
}
