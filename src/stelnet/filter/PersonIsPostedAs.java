package stelnet.filter;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.characters.PersonAPI;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class PersonIsPostedAs extends PersonFilter {

    private final String postId;

    @Override
    protected boolean acceptPerson(PersonAPI person) {
        return person.getPostId().equals(postId);
    }

    @Override
    public String toString() {
        PersonAPI person = Global.getSettings().createPerson();
        person.setPostId(postId);
        return person.getPost();
    }
}
