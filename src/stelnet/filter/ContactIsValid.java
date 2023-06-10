package stelnet.filter;

import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel.ContactState;
import com.fs.starfarer.api.impl.campaign.missions.hub.BaseMissionHub;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public final class ContactIsValid extends Filter {

    @Override
    public boolean accept(Object object) {
        if (object instanceof ContactIntel) {
            return acceptContact((ContactIntel) object);
        }
        return super.accept(object);
    }

    public boolean acceptContact(ContactIntel intel) {
        PersonAPI person = intel.getPerson();
        if (acceptFromMemoryFlag(person)) {
            return true;
        }
        ContactState state = intel.getState();
        return acceptFromState(state);
    }

    private boolean acceptFromMemoryFlag(PersonAPI person) {
        return person.getMemoryWithoutUpdate().is(BaseMissionHub.CONTACT_SUSPENDED, false);
    }

    private boolean acceptFromState(ContactState state) {
        switch (state) {
            case LOST_CONTACT:
            case LOST_CONTACT_DECIV:
            case POTENTIAL:
            case SUSPENDED:
                return false;
            default:
                return true;
        }
    }
}
