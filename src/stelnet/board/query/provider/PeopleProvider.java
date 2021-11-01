package stelnet.board.query.provider;

import com.fs.starfarer.api.characters.PersonAPI;
import java.util.Collections;
import java.util.List;
import stelnet.filter.Filter;

public class PeopleProvider {

    public List<PersonAPI> getPeople() {
        return getPeople(Collections.<Filter>emptyList());
    }

    public List<PersonAPI> getPeople(List<Filter> filters) {
        return Collections.emptyList();
    }
}
