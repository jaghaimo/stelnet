package stelnet.filter;

import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel.ContactState;
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
        ContactState state = intel.getState();
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
