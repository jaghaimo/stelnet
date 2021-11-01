package stelnet.board.query.view.add;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.impl.campaign.ids.Personalities;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import com.fs.starfarer.api.ui.Alignment;

import stelnet.board.query.provider.PeopleProvider;
import stelnet.board.query.provider.SkillProvider;
import stelnet.filter.PersonHasPersonality;
import stelnet.filter.PersonHasSkill;
import stelnet.filter.PersonIsPostedAs;
import stelnet.filter.SkillIsCombatOfficer;
import stelnet.util.Settings;
import uilib.DynamicGroup;
import uilib.HorizontalViewContainer;
import uilib.Paragraph;
import uilib.People;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.VerticalViewContainer;
import uilib.property.Size;

public class PersonnelQueryFactory implements RenderableFactory {

    private SizeHelper sizeHelper;
    private final PreviewHelper previewHelper = new PreviewHelper();
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

    @Override
    public List<Renderable> create(Size size) {
        sizeHelper = new SizeHelper(size);
        Renderable container = getContainer();
        Renderable preview = getPreview(new Size(sizeHelper.getPreviewWidth(), sizeHelper.getHeight()));
        return Collections.<Renderable>singletonList(new HorizontalViewContainer(container, preview));
    }

    public void setLevel(LevelButton active) {
        for (LevelButton button : level) {
            button.setStateOn(false);
            if (active.equals(button)) {
                button.setStateOn(true);
            }
        }
    }

    private void addLevels(List<Renderable> containers) {
        if (level.length == 0) {
            return;
        }
        HorizontalViewContainer container = new HorizontalViewContainer(
            new Paragraph("Minimal level", sizeHelper.getTextWidth(), 4, Alignment.RMID),
            new DynamicGroup(sizeHelper.getGroupWidth(), level)
        );
        containers.add(container);
    }

    private void addPersonalities(List<Renderable> containers) {
        if (personality.length == 0) {
            return;
        }
        containers.add(
            new HorizontalViewContainer(
                new Paragraph("Personality of the officer", sizeHelper.getTextWidth(), 4, Alignment.RMID),
                new DynamicGroup(sizeHelper.getGroupWidth(), personality)
            )
        );
    }

    private void addPostTypes(List<Renderable> containers) {
        if (postType.length == 0) {
            return;
        }
        containers.add(
            new HorizontalViewContainer(
                new Paragraph("Type of personnel", sizeHelper.getTextWidth(), 4, Alignment.RMID),
                new DynamicGroup(sizeHelper.getGroupWidth(), postType)
            )
        );
    }

    private void addSkills(List<Renderable> containers) {
        if (skill.length == 0) {
            return;
        }
        containers.add(
            new HorizontalViewContainer(
                new Paragraph("Skills of the officer", sizeHelper.getTextWidth(), 4, Alignment.RMID),
                new DynamicGroup(sizeHelper.getGroupWidth(), skill)
            )
        );
    }

    private Renderable getContainer() {
        List<Renderable> elements = new LinkedList<>();
        addPostTypes(elements);
        addLevels(elements);
        addPersonalities(elements);
        addSkills(elements);
        VerticalViewContainer container = new VerticalViewContainer(elements);
        return container;
    }

    private Renderable getPreview(Size size) {
        People people = new People(new PeopleProvider().getPeople(), "No matching people found.", size);
        return previewHelper.getPreview(people, size);
    }

    private LevelButton[] getLevelButtons() {
        List<LevelButton> levelButtons = new LinkedList<>();
        for (int i = 1; i <= Settings.getOfficerMaxLevel(); i++) {
            levelButtons.add(new LevelButton(this, String.valueOf(i), null));
        }
        if (levelButtons.size() > 0) {
            levelButtons.get(0).setStateOn(true);
        }
        return levelButtons.toArray(new LevelButton[] {});
    }

    private PersonalityButton[] getPersonalityButtons() {
        return new PersonalityButton[] {
            new PersonalityButton(this, "officerTimid", new PersonHasPersonality(Personalities.TIMID)),
            new PersonalityButton(this, "officerCautious", new PersonHasPersonality(Personalities.CAUTIOUS)),
            new PersonalityButton(this, "officerSteady", new PersonHasPersonality(Personalities.STEADY)),
            new PersonalityButton(this, "officerAggressive", new PersonHasPersonality(Personalities.AGGRESSIVE)),
            new PersonalityButton(this, "officerReckless", new PersonHasPersonality(Personalities.RECKLESS)),
        };
    }

    private PostTypeButton[] getPostTypeButtons() {
        return new PostTypeButton[] {
            new PostTypeButton(this, "typeAdmin", new PersonIsPostedAs(Ranks.POST_FREELANCE_ADMIN)),
            new PostTypeButton(this, "typeOfficer", new PersonIsPostedAs(Ranks.POST_OFFICER_FOR_HIRE)),
            new PostTypeButton(this, "typeMercenary", new PersonIsPostedAs(Ranks.POST_MERCENARY)),
            new PostTypeButton(this, "typeAgent", new PersonIsPostedAs(Ranks.POST_AGENT)),
        };
    }

    private SkillButton[] getSkillButtons() {
        List<SkillButton> skillButtons = new LinkedList<>();
        List<SkillSpecAPI> skills = (new SkillProvider()).getSkills(new SkillIsCombatOfficer());
        for (SkillSpecAPI skill : skills) {
            skillButtons.add(new SkillButton(this, skill.getName(), new PersonHasSkill(skill.getId())));
        }
        return skillButtons.toArray(new SkillButton[] {});
    }
}
