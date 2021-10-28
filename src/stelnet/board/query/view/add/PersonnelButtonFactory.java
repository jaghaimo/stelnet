package stelnet.board.query.view.add;

import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.ui.Alignment;
import java.util.LinkedList;
import java.util.List;
import stelnet.board.query.provider.SkillProvider;
import stelnet.filter.market.person.IsPostedAs;
import stelnet.filter.skillspec.IsCombatOfficer;
import stelnet.util.Settings;
import uilib.DynamicGroup;
import uilib.HorizontalViewContainer;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.property.Size;

public class PersonnelButtonFactory implements RenderableFactory {

    private final PostTypeButton[] postType;
    private final LevelButton[] level;
    private final PersonalityButton[] personality;
    private final SkillButton[] skill;

    public PersonnelButtonFactory() {
        postType = getPostTypeButtons();
        level = getLevelButtons();
        personality = getPersonalityButtons();
        skill = getSkillButtons();
    }

    @Override
    public List<Renderable> create(Size size) {
        float width = size.getWidth();
        float textWidth = Math.max(width / 4, 200);
        float groupWidth = width - textWidth;
        List<Renderable> containers = new LinkedList<>();
        addPostTypes(containers, textWidth, groupWidth);
        addLevels(containers, textWidth, groupWidth);
        addPersonalities(containers, textWidth, groupWidth);
        addSkills(containers, textWidth, groupWidth);
        return containers;
    }

    public void setLevel(LevelButton active) {
        for (LevelButton button : level) {
            button.setStateOn(false);
            if (active.equals(button)) {
                button.setStateOn(true);
            }
        }
    }

    private void addLevels(List<Renderable> containers, float textWidth, float groupWidth) {
        if (level.length == 0) {
            return;
        }
        containers.add(
            new HorizontalViewContainer(
                new Paragraph("What should be the minimal level?", textWidth, 4, Alignment.RMID),
                new DynamicGroup(groupWidth, level)
            )
        );
    }

    private void addPersonalities(List<Renderable> containers, float textWidth, float groupWidth) {
        if (personality.length == 0) {
            return;
        }
        containers.add(
            new HorizontalViewContainer(
                new Paragraph("What personality should the officer have?", textWidth, 4, Alignment.RMID),
                new DynamicGroup(groupWidth, personality)
            )
        );
    }

    private void addPostTypes(List<Renderable> containers, float textWidth, float groupWidth) {
        if (postType.length == 0) {
            return;
        }
        containers.add(
            new HorizontalViewContainer(
                new Paragraph("What type of personnel are you after?", textWidth, 4, Alignment.RMID),
                new DynamicGroup(groupWidth, postType)
            )
        );
    }

    private void addSkills(List<Renderable> containers, float textWidth, float groupWidth) {
        if (skill.length == 0) {
            return;
        }
        containers.add(
            new HorizontalViewContainer(
                new Paragraph("What skills should the officer have?", textWidth, 4, Alignment.RMID),
                new DynamicGroup(groupWidth, skill)
            )
        );
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
            new PersonalityButton(this, "officerTimid", null),
            new PersonalityButton(this, "officerCautious", null),
            new PersonalityButton(this, "officerSteady", null),
            new PersonalityButton(this, "officerAggressive", null),
            new PersonalityButton(this, "officerReckless", null),
        };
    }

    private PostTypeButton[] getPostTypeButtons() {
        return new PostTypeButton[] {
            new PostTypeButton(this, "typeAdministrator", IsPostedAs.admin()),
            new PostTypeButton(this, "typeOfficer", IsPostedAs.officer()),
            new PostTypeButton(this, "typeMercenary", IsPostedAs.mercenary()),
            new PostTypeButton(this, "typeAgent", IsPostedAs.agent()),
        };
    }

    private SkillButton[] getSkillButtons() {
        List<SkillButton> skillButtons = new LinkedList<>();
        List<SkillSpecAPI> skills = (new SkillProvider()).getSkills(new IsCombatOfficer());
        for (SkillSpecAPI skill : skills) {
            skillButtons.add(new SkillButton(this, skill.getName(), null));
        }
        return skillButtons.toArray(new SkillButton[] {});
    }
}
