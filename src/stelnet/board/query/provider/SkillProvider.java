package stelnet.board.query.provider;

import com.fs.starfarer.api.characters.SkillSpecAPI;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;
import stelnet.util.SettingsUtils;

public class SkillProvider implements Comparator<SkillSpecAPI> {

    public List<SkillSpecAPI> getSkills(Filter filter) {
        List<SkillSpecAPI> skills = getAllSkillSpecs();
        CollectionUtils.reduce(skills, filter);
        Collections.sort(skills, this);
        return skills;
    }

    private List<SkillSpecAPI> getAllSkillSpecs() {
        List<String> skillIds = SettingsUtils.getAllSkillIds();
        List<SkillSpecAPI> skillSpecs = new LinkedList<>();
        for (String skillId : skillIds) {
            skillSpecs.add(SettingsUtils.getSkill(skillId));
        }
        return skillSpecs;
    }

    @Override
    public int compare(SkillSpecAPI o1, SkillSpecAPI o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
