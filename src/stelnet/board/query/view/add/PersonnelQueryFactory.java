package stelnet.board.query.view.add;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import stelnet.CommonL10n;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.provider.PeopleProvider;
import stelnet.board.query.view.ButtonGroup;
import stelnet.board.query.view.FilteringButton;
import stelnet.board.query.view.SectionHeader;
import stelnet.filter.Filter;
import stelnet.util.L10n;
import uilib.Button;
import uilib.Renderable;

public class PersonnelQueryFactory extends QueryFactory {

    @Getter
    private transient PeopleProvider provider = new PeopleProvider();

    private final FilteringButton[] postType = PersonnelButtonUtils.getPostTypeButtons();
    private final FilteringButton[] personality = PersonnelButtonUtils.getPersonalityButtons();
    private final FilteringButton[] skill = PersonnelButtonUtils.getSkillButtons();

    public Object readResolve() {
        provider = new PeopleProvider();
        return this;
    }

    @Override
    public Set<Filter> getFilters() {
        Set<Filter> filters = new LinkedHashSet<>();
        addSelectedOrAll(filters, postType, L10n.get(QueryL10n.PERSONNEL_POST_TYPES));
        addSelectedOrNone(filters, personality, L10n.get(QueryL10n.PERSONNEL_PERSONALITY), hasOfficers());
        addSelectedOrNone(filters, skill, L10n.get(QueryL10n.PERSONNEL_SKILLS), hasOfficers());
        return filters;
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
