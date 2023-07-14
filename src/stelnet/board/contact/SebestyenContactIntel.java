package stelnet.board.contact;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import com.fs.starfarer.api.ui.IntelUIAPI;

/**
 * Dummy class to override some checks and actions on Sebestyen.
 */
public class SebestyenContactIntel extends ContactIntel {

    public SebestyenContactIntel(final PersonAPI person, final MarketAPI market) {
        super(person, market);
    }

    @Override
    public void doPeriodicCheck() {}

    @Override
    public void buttonPressConfirmed(final Object buttonId, final IntelUIAPI ui) {}

    @Override
    public void storyActionConfirmed(final Object buttonId, final IntelUIAPI ui) {}

    @Override
    public boolean doesButtonHaveConfirmDialog(final Object buttonId) {
        return false;
    }
}
