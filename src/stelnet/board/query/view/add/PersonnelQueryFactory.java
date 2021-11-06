package stelnet.board.query.view.add;

import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.impl.campaign.ids.Personalities;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import com.fs.starfarer.api.ui.Alignment;
import java.util.LinkedList;
import java.util.List;
import stelnet.CommonL10n;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.provider.PeopleProvider;
import stelnet.board.query.provider.SkillProvider;
import stelnet.filter.Filter;
import stelnet.filter.LogicalOrFilter;
import stelnet.filter.PersonHasLevel;
import stelnet.filter.PersonHasPersonality;
import stelnet.filter.PersonHasSkill;
import stelnet.filter.PersonIsPostedAs;
import stelnet.filter.SkillIsCombatOfficer;
import stelnet.util.L10n;
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
    private final OfficerButton[] personality;
    private final OfficerButton[] skill;

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
        beginSection(elements, QueryL10n.OFFICERS_AND_MERCENARIES);
        addOfficerLevels(elements);
        addOfficerPersonalities(elements);
        addOfficerSkills(elements);
        endSection(elements);
        VerticalViewContainer container = new VerticalViewContainer(elements);
        return container;
    }

    @Override
    protected RenderableComponent getPreviewContent(Size size) {
        List<Filter> filters = getFilters();
        return new People(new PeopleProvider().getPeople(filters), "No matching people found.", size);
    }

    private void addOfficerLevels(List<Renderable> containers) {
        HorizontalViewContainer container = new HorizontalViewContainer(
            new Paragraph("Minimal level", sizeHelper.getTextWidth(), 4, Alignment.RMID),
            new DynamicGroup(sizeHelper.getGroupWidth(), level)
        );
        containers.add(container);
    }

    private void addOfficerPersonalities(List<Renderable> containers) {
        containers.add(
            new HorizontalViewContainer(
                new Paragraph("Personality", sizeHelper.getTextWidth(), 4, Alignment.RMID),
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

    private void addOfficerSkills(List<Renderable> containers) {
        containers.add(
            new HorizontalViewContainer(
                new Paragraph("Skills", sizeHelper.getTextWidth(), 4, Alignment.RMID),
                new DynamicGroup(sizeHelper.getGroupWidth(), skill)
            )
        );
    }

    private List<Filter> getFilters() {
        List<Filter> filters = new LinkedList<>();
        filters.add(new LogicalOrFilter(getFilters(postType)));
        filters.add(new LogicalOrFilter(getFilters(level)));
        filters.add(new LogicalOrFilter(getFilters(personality)));
        filters.add(new LogicalOrFilter(getFilters(skill)));
        return filters;
    }

    private List<Filter> getFilters(FilteringButton buttons[]) {
        List<Filter> allFilters = new LinkedList<>();
        List<Filter> selectedFilters = new LinkedList<>();
        for (FilteringButton button : buttons) {
            allFilters.add(button.getFilter());
            if (button.isStateOn()) {
                selectedFilters.add(button.getFilter());
            }
        }
        return selectedFilters.isEmpty() ? allFilters : selectedFilters;
    }

    private LevelButton[] getLevelButtons() {
        List<LevelButton> levelButtons = new LinkedList<>();
        for (int i = 1; i <= SettingsUtils.getOfficerMaxLevel(); i++) {
            levelButtons.add(new LevelButton(this, String.valueOf(i), new PersonHasLevel(i)));
        }
        if (levelButtons.size() > 0) {
            levelButtons.get(0).setStateOn(true);
        }
        return levelButtons.toArray(new LevelButton[] {});
    }

    private OfficerButton[] getPersonalityButtons() {
        return new OfficerButton[] {
            new OfficerButton(L10n.get(CommonL10n.TIMID), new PersonHasPersonality(Personalities.TIMID)),
            new OfficerButton(L10n.get(CommonL10n.CAUTIOUS), new PersonHasPersonality(Personalities.CAUTIOUS)),
            new OfficerButton(L10n.get(CommonL10n.STEADY), new PersonHasPersonality(Personalities.STEADY)),
            new OfficerButton(L10n.get(CommonL10n.AGGRESSIVE), new PersonHasPersonality(Personalities.AGGRESSIVE)),
            new OfficerButton(L10n.get(CommonL10n.RECKLESS), new PersonHasPersonality(Personalities.RECKLESS)),
        };
    }

    private PostTypeButton[] getPostTypeButtons() {
        return new PostTypeButton[] {
            new PostTypeButton(CommonL10n.ADMIN, new PersonIsPostedAs(Ranks.POST_FREELANCE_ADMIN)),
            new PostTypeButton(CommonL10n.OFFICER, new PersonIsPostedAs(Ranks.POST_OFFICER_FOR_HIRE)),
            new PostTypeButton(CommonL10n.MERCENARY, new PersonIsPostedAs(Ranks.POST_MERCENARY)),
            new PostTypeButton(CommonL10n.AGENT, new PersonIsPostedAs(Ranks.POST_AGENT)),
        };
    }

    private OfficerButton[] getSkillButtons() {
        List<OfficerButton> skillButtons = new LinkedList<>();
        List<SkillSpecAPI> skills = (new SkillProvider()).getSkills(new SkillIsCombatOfficer());
        for (SkillSpecAPI skill : skills) {
            skillButtons.add(new OfficerButton(skill.getName(), new PersonHasSkill(skill.getId())));
        }
        return skillButtons.toArray(new OfficerButton[] {});
    }
}
