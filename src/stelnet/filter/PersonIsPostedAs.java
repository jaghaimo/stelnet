package stelnet.filter;

import com.fs.starfarer.api.characters.PersonAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonIsPostedAs extends PersonFilter {

    private final String post;

    @Override
    protected boolean acceptPerson(PersonAPI person) {
        String postId = person.getPostId();
        return postId.equals(post);
    }
}
