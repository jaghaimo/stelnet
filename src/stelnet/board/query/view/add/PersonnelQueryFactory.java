package stelnet.board.query.view.add;

import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.impl.campaign.ids.Personalities;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import stelnet.CommonL10n;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.provider.PeopleProvider;
import stelnet.board.query.provider.QueryProvider;
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
import uilib.Button;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.ShowPeople;
import uilib.property.Size;

public class PersonnelQueryFactory extends QueryFactory {

    private final PeopleProvider peopleProvider = new PeopleProvider(this);
    private final SkillProvider skillProvider = new SkillProvider();
    private final FilteringButton[] postType = createPostTypeButtons();
    private final FilteringButton[] level = createLevelButtons();
    private final FilteringButton[] personality = createPersonalityButtons();
    private final FilteringButton[] skill = createSkillButtons();

    public void setLevel(OfficerLevelButton active) {
        for (FilteringButton button : level) {
            button.setStateOn(active.equals(button));
        }
    }

    @Override
    public Set<Filter> getFilters(boolean forResults) {
        Set<Filter> filters = new LinkedHashSet<>();
        addToFilters(filters, postType, L10n.get(QueryL10n.PERSONNEL_POST_TYPES), true);
        addToFilters(filters, level, L10n.get(QueryL10n.PERSONNEL_MIN_LEVEL), hasOfficers());
        addToFilters(filters, personality, L10n.get(QueryL10n.PERSONNEL_PERSONALITY), hasOfficers());
        addToFilters(filters, skill, L10n.get(QueryL10n.PERSONNEL_SKILLS), hasOfficers());
        return filters;
    }

    @Override
    public RenderableComponent getPreview(Set<Filter> filters, Size size) {
        return new ShowPeople(peopleProvider.getMatching(filters), L10n.get(QueryL10n.NO_MATCHING_PEOPLE), size);
    }

    @Override
    public QueryProvider getProvider() {
        return peopleProvider;
    }

    @Override
    protected Button[] getFinalComponents() {
        return new Button[] { new FindMatchingButton(this, L10n.get(CommonL10n.PERSONNEL)) };
    }

    @Override
    protected List<Renderable> getQueryBuildingComponents() {
        List<Renderable> elements = new LinkedList<>();
        addLabeledGroup(elements, QueryL10n.PERSONNEL_POST_TYPES, postType, true);
        addSection(elements, QueryL10n.OFFICERS_AND_MERCENARIES, hasOfficers());
        addLabeledGroup(elements, QueryL10n.PERSONNEL_MIN_LEVEL, level, hasOfficers());
        addLabeledGroup(elements, QueryL10n.PERSONNEL_PERSONALITY, personality, hasOfficers());
        addLabeledGroup(elements, QueryL10n.PERSONNEL_SKILLS, skill, hasOfficers());
        return elements;
    }

    private OfficerLevelButton[] createLevelButtons() {
        List<OfficerLevelButton> levelButtons = new LinkedList<>();
        for (int i = 1; i <= SettingsUtils.getOfficerMaxLevel(); i++) {
            levelButtons.add(new OfficerLevelButton(this, String.valueOf(i), new PersonHasLevel(i)));
        }
        if (levelButtons.size() > 0) {
            levelButtons.get(0).setStateOn(true);
        }
        return levelButtons.toArray(new OfficerLevelButton[] {});
    }

    private FilteringButton[] createPersonalityButtons() {
        return new FilteringButton[] {
            new FilteringButton(L10n.get(CommonL10n.TIMID), new PersonHasPersonality(Personalities.TIMID)),
            new FilteringButton(L10n.get(CommonL10n.CAUTIOUS), new PersonHasPersonality(Personalities.CAUTIOUS)),
            new FilteringButton(L10n.get(CommonL10n.STEADY), new PersonHasPersonality(Personalities.STEADY)),
            new FilteringButton(L10n.get(CommonL10n.AGGRESSIVE), new PersonHasPersonality(Personalities.AGGRESSIVE)),
            new FilteringButton(L10n.get(CommonL10n.RECKLESS), new PersonHasPersonality(Personalities.RECKLESS)),
        };
    }

    private FilteringButton[] createPostTypeButtons() {
        return new FilteringButton[] {
            new FilteringButton(CommonL10n.ADMIN, new PersonIsPostedAs(Ranks.POST_FREELANCE_ADMIN)),
            new FilteringButton(CommonL10n.OFFICER, new PersonIsPostedAs(Ranks.POST_OFFICER_FOR_HIRE)),
            new FilteringButton(CommonL10n.MERCENARY, new PersonIsPostedAs(Ranks.POST_MERCENARY)),
        };
    }

    private FilteringButton[] createSkillButtons() {
        List<FilteringButton> skillButtons = new LinkedList<>();
        List<SkillSpecAPI> skills = skillProvider.getMatching(
            Arrays.<Filter>asList(new LogicalNot(new AnyHasTag("npc_only")), new SkillIsCombatOfficer())
        );
        for (SkillSpecAPI skill : skills) {
            skillButtons.add(new FilteringButton(skill.getName(), new PersonHasSkill(skill.getId())));
        }
        return skillButtons.toArray(new FilteringButton[] {});
    }

    private boolean hasOfficers() {
        return postType[1].isStateOn() || postType[2].isStateOn();
    }
}
