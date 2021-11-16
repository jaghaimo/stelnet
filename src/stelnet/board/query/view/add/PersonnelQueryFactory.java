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
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.ShowPeople;
import uilib.property.Size;

public class PersonnelQueryFactory extends QueryFactory {

    private final PeopleProvider peopleProvider = new PeopleProvider(this);
    private final SkillProvider skillProvider = new SkillProvider();
    private final PersonnelButton[] postType = createPostTypeButtons();
    private final OfficerButton[] level = createLevelButtons();
    private final OfficerButton[] personality = createPersonalityButtons();
    private final OfficerButton[] skill = createSkillButtons();

    public void setLevel(OfficerLevelButton active) {
        for (OfficerButton button : level) {
            button.setStateOn(active.equals(button));
        }
    }

    @Override
    public RenderableComponent getPreview(Size size) {
        Set<Filter> filters = getFilters();
        return new ShowPeople(peopleProvider.getMatching(filters), L10n.get(QueryL10n.NO_MATCHING_PEOPLE), size);
    }

    @Override
    protected Set<Filter> getFilters() {
        Set<Filter> filters = new LinkedHashSet<>();
        addToFilters(filters, postType, L10n.get(QueryL10n.PERSONNEL_POST_TYPES), true);
        addToFilters(filters, level, L10n.get(QueryL10n.PERSONNEL_MIN_LEVEL), false);
        addToFilters(filters, personality, L10n.get(QueryL10n.PERSONNEL_PERSONALITY), false);
        addToFilters(filters, skill, L10n.get(QueryL10n.PERSONNEL_SKILLS), false);
        return filters;
    }

    @Override
    protected List<Renderable> getQueryBuildingComponents() {
        List<Renderable> elements = new LinkedList<>();
        addLabeledGroup(elements, QueryL10n.PERSONNEL_POST_TYPES, Arrays.<Renderable>asList(postType));
        addSection(elements, QueryL10n.OFFICERS_AND_MERCENARIES);
        addLabeledGroup(elements, QueryL10n.PERSONNEL_MIN_LEVEL, Arrays.<Renderable>asList(level));
        addLabeledGroup(elements, QueryL10n.PERSONNEL_PERSONALITY, Arrays.<Renderable>asList(personality));
        addLabeledGroup(elements, QueryL10n.PERSONNEL_SKILLS, Arrays.<Renderable>asList(skill));
        return elements;
    }

    @Override
    protected QueryProvider getProvider() {
        return peopleProvider;
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

    private OfficerButton[] createPersonalityButtons() {
        return new OfficerButton[] {
            new OfficerButton(L10n.get(CommonL10n.TIMID), new PersonHasPersonality(Personalities.TIMID)),
            new OfficerButton(L10n.get(CommonL10n.CAUTIOUS), new PersonHasPersonality(Personalities.CAUTIOUS)),
            new OfficerButton(L10n.get(CommonL10n.STEADY), new PersonHasPersonality(Personalities.STEADY)),
            new OfficerButton(L10n.get(CommonL10n.AGGRESSIVE), new PersonHasPersonality(Personalities.AGGRESSIVE)),
            new OfficerButton(L10n.get(CommonL10n.RECKLESS), new PersonHasPersonality(Personalities.RECKLESS)),
        };
    }

    private PersonnelButton[] createPostTypeButtons() {
        return new PersonnelButton[] {
            new PersonnelButton(CommonL10n.ADMIN, new PersonIsPostedAs(Ranks.POST_FREELANCE_ADMIN)),
            new PersonnelButton(CommonL10n.OFFICER, new PersonIsPostedAs(Ranks.POST_OFFICER_FOR_HIRE)),
            new PersonnelButton(CommonL10n.MERCENARY, new PersonIsPostedAs(Ranks.POST_MERCENARY)),
        };
    }

    private OfficerButton[] createSkillButtons() {
        List<OfficerButton> skillButtons = new LinkedList<>();
        List<SkillSpecAPI> skills = skillProvider.getMatching(
            Arrays.<Filter>asList(new LogicalNot(new AnyHasTag("npc_only")), new SkillIsCombatOfficer())
        );
        for (SkillSpecAPI skill : skills) {
            skillButtons.add(new OfficerButton(skill.getName(), new PersonHasSkill(skill.getId())));
        }
        return skillButtons.toArray(new OfficerButton[] {});
    }
}
