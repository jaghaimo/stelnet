package stelnet.board.query.provider;

import com.fs.starfarer.api.characters.SkillSpecAPI;
import java.util.Comparator;

public class SkillSorter implements Comparator<SkillSpecAPI> {

    @Override
    public int compare(final SkillSpecAPI o1, final SkillSpecAPI o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
