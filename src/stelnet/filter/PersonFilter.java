package stelnet.filter;

import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.characters.PersonAPI;

public abstract class PersonFilter extends Filter {

    @Override
    public boolean accept(Object object) {
        if (object instanceof CommDirectoryEntryAPI) {
            return acceptCommDirectoryEntry((CommDirectoryEntryAPI) object);
        }
        if (object instanceof PersonAPI) {
            return acceptPerson((PersonAPI) object);
        }
        return super.accept(object);
    }

    protected boolean acceptCommDirectoryEntry(CommDirectoryEntryAPI entry) {
        PersonAPI person = (PersonAPI) entry.getEntryData();
        return acceptPerson(person);
    }

    protected abstract boolean acceptPerson(PersonAPI person);
}
