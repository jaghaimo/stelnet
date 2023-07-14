package stelnet.board.contact2;

import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import java.util.HashSet;
import java.util.Set;

public class ActionFactory {

    public Set<ContactActions> getVanillaActions(final ContactIntel.ContactState state) {
        final Set<ContactActions> actions = new HashSet<>(3);
        switch (state) {
            case POTENTIAL:
                actions.add(ContactActions.DEVELOP);
                break;
            case SUSPENDED:
                actions.add(ContactActions.RESUME);
                break;
            case PRIORITY:
            case NON_PRIORITY:
                actions.add(ContactActions.PRIORITY);
                actions.add(ContactActions.SUSPEND);
                break;
        }
        return actions;
    }

    public Set<ContactActions> getStelnetActions(final ContactIntel.ContactState state) {
        final Set<ContactActions> actions = new HashSet<>(2);
        switch (state) {
            case PRIORITY:
            case NON_PRIORITY:
                actions.add(ContactActions.CALL);
                actions.add(ContactActions.SHOW);
        }
        return actions;
    }
}
