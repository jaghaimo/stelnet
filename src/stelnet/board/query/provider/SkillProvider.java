package stelnet.board.query.provider;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;
import stelnet.util.Excluder;

public class SkillProvider {

    private static transient List<SkillSpecAPI> allSkills;

    public static void resetCache() {
        allSkills = null;
    }

    public List<SkillSpecAPI> getMatching(final List<Filter> filter) {
        if (allSkills == null) {
            allSkills = getAllSkillSpecs();
            Collections.sort(allSkills, new SkillSorter());
        }
        final List<SkillSpecAPI> skillsCopy = new LinkedList<SkillSpecAPI>(allSkills);
        CollectionUtils.reduce(skillsCopy, filter);
        return skillsCopy;
    }

    private List<SkillSpecAPI> getAllSkillSpecs() {
        final List<String> skillIds = Global.getSettings().getSkillIds();
        final List<SkillSpecAPI> skillSpecs = new LinkedList<>();
        for (final String skillId : skillIds) {
            skillSpecs.add(Global.getSettings().getSkillSpec(skillId));
        }
        CollectionUtils.reduce(skillSpecs, Excluder.getSkillFilter());
        return skillSpecs;
    }
}
