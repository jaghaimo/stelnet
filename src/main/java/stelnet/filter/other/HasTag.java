package stelnet.filter.other;

import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.characters.PersonAPI;

import stelnet.filter.Filter;

public class HasTag implements Filter<CommDirectoryEntryAPI> {

    private final String tag;

    public HasTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean accept(CommDirectoryEntryAPI object) {
        PersonAPI person = (PersonAPI) object.getEntryData();
        return person.getTags().contains(tag);
    }
}
