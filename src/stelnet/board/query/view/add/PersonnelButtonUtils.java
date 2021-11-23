package stelnet.board.query.view.add;

import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.impl.campaign.ids.Personalities;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import stelnet.CommonL10n;
import stelnet.board.query.provider.SkillProvider;
import stelnet.filter.AnyHasTag;
import stelnet.filter.Filter;
import stelnet.filter.LogicalNot;
import stelnet.filter.PersonHasLevel;
import stelnet.filter.PersonHasPersonality;
import stelnet.filter.PersonHasSkill;
import stelnet.filter.PersonIsPostedAs;
import stelnet.filter.SkillIsCombatOfficer;
import stelnet.util.L10n;
import stelnet.util.SettingsUtils;

public class PersonnelButtonUtils {

    public static OfficerLevelButton[] getLevelButtons(PersonnelQueryFactory factory) {
        List<OfficerLevelButton> levelButtons = new LinkedList<>();
        for (int i = 1; i <= SettingsUtils.getOfficerMaxLevel(); i++) {
            levelButtons.add(new OfficerLevelButton(factory, String.valueOf(i), new PersonHasLevel(i)));
        }
        if (levelButtons.size() > 0) {
            levelButtons.get(0).setStateOn(true);
        }
        return levelButtons.toArray(new OfficerLevelButton[] {});
    }

    public static FilteringButton[] getPersonalityButtons() {
        return new FilteringButton[] {
            new FilteringButton(L10n.get(CommonL10n.TIMID), new PersonHasPersonality(Personalities.TIMID)),
            new FilteringButton(L10n.get(CommonL10n.CAUTIOUS), new PersonHasPersonality(Personalities.CAUTIOUS)),
            new FilteringButton(L10n.get(CommonL10n.STEADY), new PersonHasPersonality(Personalities.STEADY)),
            new FilteringButton(L10n.get(CommonL10n.AGGRESSIVE), new PersonHasPersonality(Personalities.AGGRESSIVE)),
            new FilteringButton(L10n.get(CommonL10n.RECKLESS), new PersonHasPersonality(Personalities.RECKLESS)),
        };
    }

    public static FilteringButton[] getPostTypeButtons() {
        return new FilteringButton[] {
            new FilteringButton(CommonL10n.ADMIN, new PersonIsPostedAs(Ranks.POST_FREELANCE_ADMIN)),
            new FilteringButton(CommonL10n.OFFICER, new PersonIsPostedAs(Ranks.POST_OFFICER_FOR_HIRE)),
            new FilteringButton(CommonL10n.MERCENARY, new PersonIsPostedAs(Ranks.POST_MERCENARY)),
        };
    }

    public static FilteringButton[] getSkillButtons(SkillProvider provider) {
        List<FilteringButton> skillButtons = new LinkedList<>();
        List<SkillSpecAPI> skills = provider.getMatching(
            Arrays.<Filter>asList(new LogicalNot(new AnyHasTag("npc_only")), new SkillIsCombatOfficer())
        );
        for (SkillSpecAPI skill : skills) {
            skillButtons.add(new FilteringButton(skill.getName(), new PersonHasSkill(skill.getId())));
        }
        return skillButtons.toArray(new FilteringButton[] {});
    }
}
