package stelnet.board.query.view.add;

import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.impl.campaign.ids.Personalities;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.provider.SkillProvider;
import stelnet.board.query.view.FilteringButton;
import stelnet.filter.AnyHasTag;
import stelnet.filter.Filter;
import stelnet.filter.LogicalNot;
import stelnet.filter.PersonHasPersonality;
import stelnet.filter.PersonHasSkill;
import stelnet.filter.PersonIsPostedAs;
import stelnet.filter.SkillIsCombatOfficer;
import stelnet.util.L10n;

@RequiredArgsConstructor
public enum PersonnelButtonUtils {
    PERSONALITY_TIMID(Personalities.TIMID),
    PERSONALITY_CAUTIOUS(Personalities.CAUTIOUS),
    PERSONALITY_STEADY(Personalities.STEADY),
    PERSONALITY_AGGRESSIVE(Personalities.AGGRESSIVE),
    PERSONALITY_RECKLESS(Personalities.RECKLESS),
    POST_ADMIN("ADMIN", Ranks.POST_FREELANCE_ADMIN),
    POST_OFFICER("OFFICER", Ranks.POST_FREELANCE_ADMIN),
    POST_MERCENARY("MERCENARY", Ranks.POST_FREELANCE_ADMIN);

    public static FilteringButton[] getPersonalityButtons() {
        return new FilteringButton[] {
            PERSONALITY_TIMID.getButton(),
            PERSONALITY_CAUTIOUS.getButton(),
            PERSONALITY_STEADY.getButton(),
            PERSONALITY_AGGRESSIVE.getButton(),
            PERSONALITY_RECKLESS.getButton(),
        };
    }

    public static FilteringButton[] getPostTypeButtons() {
        return new FilteringButton[] { POST_ADMIN.getButton(), POST_OFFICER.getButton(), POST_MERCENARY.getButton() };
    }

    public static FilteringButton[] getSkillButtons() {
        final SkillProvider provider = new SkillProvider();
        final List<FilteringButton> skillButtons = new LinkedList<>();
        final List<SkillSpecAPI> skills = provider.getMatching(
            Arrays.<Filter>asList(new LogicalNot(new AnyHasTag("npc_only")), new SkillIsCombatOfficer())
        );
        for (final SkillSpecAPI skill : skills) {
            skillButtons.add(new FilteringButton(skill.getName(), new PersonHasSkill(skill.getId())));
        }
        return skillButtons.toArray(new FilteringButton[] {});
    }

    private final String title;

    private final Filter filter;

    private PersonnelButtonUtils(final String personality) {
        this(L10n.common(personality), new PersonHasPersonality(personality));
    }

    private PersonnelButtonUtils(final String key, final String post) {
        this(L10n.common(key), new PersonIsPostedAs(post));
    }

    public FilteringButton getButton() {
        return new FilteringButton(title, filter);
    }
}
