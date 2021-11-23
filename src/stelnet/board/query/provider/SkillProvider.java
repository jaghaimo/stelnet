package stelnet.board.query.provider;

import com.fs.starfarer.api.characters.SkillSpecAPI;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;
import stelnet.util.SettingsUtils;

public class SkillProvider {

    private transient List<SkillSpecAPI> allSkills;

    public List<SkillSpecAPI> getMatching(List<Filter> filter) {
        if (allSkills == null) {
            allSkills = getAllSkillSpecs();
            Collections.sort(allSkills, new SkillSorter());
        }
        List<SkillSpecAPI> skillsCopy = new LinkedList<SkillSpecAPI>(allSkills);
        CollectionUtils.reduce(skillsCopy, filter);
        return skillsCopy;
    }

    private List<SkillSpecAPI> getAllSkillSpecs() {
        List<String> skillIds = SettingsUtils.getAllSkillIds();
        List<SkillSpecAPI> skillSpecs = new LinkedList<>();
        for (String skillId : skillIds) {
            skillSpecs.add(SettingsUtils.getSkill(skillId));
        }
        return skillSpecs;
    }
}
