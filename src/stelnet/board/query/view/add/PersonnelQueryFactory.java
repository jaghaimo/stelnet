package stelnet.board.query.view.add;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import stelnet.CommonL10n;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.provider.PeopleProvider;
import stelnet.filter.Filter;
import stelnet.util.L10n;
import uilib.Button;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.ShowPeople;
import uilib.property.Size;

public class PersonnelQueryFactory extends QueryFactory {

    @Getter
    private final PeopleProvider provider = new PeopleProvider(this);

    private final FilteringButton[] postType = PersonnelButtonUtils.getPostTypeButtons();
    private final FilteringButton[] level = PersonnelButtonUtils.getLevelButtons(this);
    private final FilteringButton[] personality = PersonnelButtonUtils.getPersonalityButtons();
    private final FilteringButton[] skill = PersonnelButtonUtils.getSkillButtons();

    public void setLevel(OfficerLevelButton active) {
        for (FilteringButton button : level) {
            button.setStateOn(active.equals(button));
        }
    }

    @Override
    public Set<Filter> getFilters(boolean forResults) {
        Set<Filter> filters = new LinkedHashSet<>();
        addSelectedOrAll(filters, postType, L10n.get(QueryL10n.PERSONNEL_POST_TYPES));
        addSelectedOrNone(filters, level, L10n.get(QueryL10n.PERSONNEL_MIN_LEVEL), hasOfficers());
        addSelectedOrNone(filters, personality, L10n.get(QueryL10n.PERSONNEL_PERSONALITY), hasOfficers());
        addSelectedOrNone(filters, skill, L10n.get(QueryL10n.PERSONNEL_SKILLS), hasOfficers());
        return filters;
    }

    @Override
    public RenderableComponent getPreview(Set<Filter> filters, Size size) {
        return new ShowPeople(provider.getMatching(filters), L10n.get(QueryL10n.NO_MATCHING_PEOPLE), size);
    }

    @Override
    protected Button[] getFinalComponents() {
        return new Button[] { new FindMatchingButton(this, L10n.get(CommonL10n.PERSONNEL)) };
    }

    @Override
    protected List<Renderable> getQueryBuildingComponents() {
        List<Renderable> elements = new LinkedList<>();
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.PERSONNEL_POST_TYPES, postType, true));
        elements.add(
            new SectionHeader(sizeHelper.getGroupAndTextWidth(), QueryL10n.OFFICERS_AND_MERCENARIES, hasOfficers())
        );
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.PERSONNEL_MIN_LEVEL, level, hasOfficers()));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.PERSONNEL_PERSONALITY, personality, hasOfficers()));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.PERSONNEL_SKILLS, skill, hasOfficers()));
        return elements;
    }

    private boolean hasOfficers() {
        boolean hasAdmin = postType[0].isStateOn();
        boolean hasOfficerOrMerc = postType[1].isStateOn() || postType[2].isStateOn();
        return hasOfficerOrMerc || (!hasAdmin && !hasOfficerOrMerc);
    }
}
