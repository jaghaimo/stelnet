package stelnet.board.query.view.add;

import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.impl.campaign.ids.Personalities;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import com.fs.starfarer.api.ui.Alignment;
import java.util.LinkedList;
import java.util.List;
import stelnet.board.query.provider.PeopleProvider;
import stelnet.board.query.provider.SkillProvider;
import stelnet.filter.Filter;
import stelnet.filter.LogicalOrFilter;
import stelnet.filter.PersonHasPersonality;
import stelnet.filter.PersonHasSkill;
import stelnet.filter.PersonIsPostedAs;
import stelnet.filter.SkillIsCombatOfficer;
import stelnet.util.SettingsUtils;
import uilib.DynamicGroup;
import uilib.HorizontalViewContainer;
import uilib.Paragraph;
import uilib.People;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.VerticalViewContainer;
import uilib.property.Size;

public class PersonnelQueryFactory extends PreviewableQueryFactory {

    private final PostTypeButton[] postType;
    private final LevelButton[] level;
    private final PersonalityButton[] personality;
    private final SkillButton[] skill;

    public PersonnelQueryFactory() {
        postType = getPostTypeButtons();
        level = getLevelButtons();
        personality = getPersonalityButtons();
        skill = getSkillButtons();
    }

    public void setLevel(LevelButton active) {
        for (LevelButton button : level) {
            button.setStateOn(active.equals(button));
        }
    }

    @Override
    protected Renderable getContainer() {
        List<Renderable> elements = new LinkedList<>();
        addPostTypes(elements);
        addLevels(elements);
        addPersonalities(elements);
        addSkills(elements);
        VerticalViewContainer container = new VerticalViewContainer(elements);
        return container;
    }

    @Override
    protected RenderableComponent getPreviewContent(Size size) {
        List<Filter> filters = getFilters();
        return new People(new PeopleProvider().getPeople(filters), "No matching people found.", size);
    }

    private void addLevels(List<Renderable> containers) {
        HorizontalViewContainer container = new HorizontalViewContainer(
            new Paragraph("Minimal level", sizeHelper.getTextWidth(), 4, Alignment.RMID),
            new DynamicGroup(sizeHelper.getGroupWidth(), level)
        );
        containers.add(container);
    }

    private void addPersonalities(List<Renderable> containers) {
        containers.add(
            new HorizontalViewContainer(
                new Paragraph("Personality of the officer", sizeHelper.getTextWidth(), 4, Alignment.RMID),
                new DynamicGroup(sizeHelper.getGroupWidth(), personality)
            )
        );
    }

    private void addPostTypes(List<Renderable> containers) {
        containers.add(
            new HorizontalViewContainer(
                new Paragraph("Type of personnel", sizeHelper.getTextWidth(), 4, Alignment.RMID),
                new DynamicGroup(sizeHelper.getGroupWidth(), postType)
            )
        );
    }

    private void addSkills(List<Renderable> containers) {
        containers.add(
            new HorizontalViewContainer(
                new Paragraph("Skills of the officer", sizeHelper.getTextWidth(), 4, Alignment.RMID),
                new DynamicGroup(sizeHelper.getGroupWidth(), skill)
            )
        );
    }

    private List<Filter> getFilters() {
        List<Filter> filters = new LinkedList<>();
        filters.add(new LogicalOrFilter(getPostTypeFilters()));
        return filters;
    }

    private LevelButton[] getLevelButtons() {
        List<LevelButton> levelButtons = new LinkedList<>();
        for (int i = 1; i <= SettingsUtils.getOfficerMaxLevel(); i++) {
            levelButtons.add(new LevelButton(this, String.valueOf(i), null));
        }
        if (levelButtons.size() > 0) {
            levelButtons.get(0).setStateOn(true);
        }
        return levelButtons.toArray(new LevelButton[] {});
    }

    private PersonalityButton[] getPersonalityButtons() {
        return new PersonalityButton[] {
            new PersonalityButton("officerTimid", new PersonHasPersonality(Personalities.TIMID)),
            new PersonalityButton("officerCautious", new PersonHasPersonality(Personalities.CAUTIOUS)),
            new PersonalityButton("officerSteady", new PersonHasPersonality(Personalities.STEADY)),
            new PersonalityButton("officerAggressive", new PersonHasPersonality(Personalities.AGGRESSIVE)),
            new PersonalityButton("officerReckless", new PersonHasPersonality(Personalities.RECKLESS)),
        };
    }

    private PostTypeButton[] getPostTypeButtons() {
        return new PostTypeButton[] {
            new PostTypeButton("typeAdmin", new PersonIsPostedAs(Ranks.POST_FREELANCE_ADMIN)),
            new PostTypeButton("typeOfficer", new PersonIsPostedAs(Ranks.POST_OFFICER_FOR_HIRE)),
            new PostTypeButton("typeMercenary", new PersonIsPostedAs(Ranks.POST_MERCENARY)),
            new PostTypeButton("typeAgent", new PersonIsPostedAs(Ranks.POST_AGENT)),
        };
    }

    private List<Filter> getPostTypeFilters() {
        List<Filter> allFilters = new LinkedList<>();
        List<Filter> selectedFilters = new LinkedList<>();
        for (PostTypeButton type : postType) {
            allFilters.add(type.getFilter());
            if (type.isStateOn()) {
                selectedFilters.add(type.getFilter());
            }
        }
        return selectedFilters.isEmpty() ? allFilters : selectedFilters;
    }

    private SkillButton[] getSkillButtons() {
        List<SkillButton> skillButtons = new LinkedList<>();
        List<SkillSpecAPI> skills = (new SkillProvider()).getSkills(new SkillIsCombatOfficer());
        for (SkillSpecAPI skill : skills) {
            skillButtons.add(new SkillButton(skill.getName(), new PersonHasSkill(skill.getId())));
        }
        return skillButtons.toArray(new SkillButton[] {});
    }
}
