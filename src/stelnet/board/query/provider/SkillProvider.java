package stelnet.board.query.provider;

import com.fs.starfarer.api.characters.SkillSpecAPI;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import stelnet.filter.skillspec.SkillSpecFilter;
import stelnet.util.CollectionReducer;
import stelnet.util.Settings;

public class SkillProvider {

    public List<SkillSpecAPI> getSkills() {
        return getSkills(Collections.<SkillSpecFilter>emptyList());
    }

    public List<SkillSpecAPI> getSkills(SkillSpecFilter filter) {
        return getSkills(Collections.singletonList(filter));
    }

    public List<SkillSpecAPI> getSkills(List<SkillSpecFilter> filters) {
        List<SkillSpecAPI> skills = getAllSkillSpecs();
        CollectionReducer.reduce(skills, filters);
        Collections.sort(
            skills,
            new Comparator<SkillSpecAPI>() {
                @Override
                public int compare(SkillSpecAPI o1, SkillSpecAPI o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            }
        );
        return skills;
    }

    private List<SkillSpecAPI> getAllSkillSpecs() {
        List<String> skillIds = Settings.getAllSkillIds();
        List<SkillSpecAPI> skillSpecs = new LinkedList<>();
        for (String skillId : skillIds) {
            skillSpecs.add(Settings.getSkill(skillId));
        }
        return skillSpecs;
    }
}
