package stelnet.board.query.view.add;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import stelnet.board.query.provider.PeopleQueryProvider;
import stelnet.board.query.view.ButtonGroup;
import stelnet.board.query.view.FilteringButton;
import stelnet.board.query.view.SectionHeader;
import stelnet.filter.Filter;
import stelnet.util.L10n;
import uilib.Button;
import uilib.Renderable;

public class PersonnelQueryFactory extends QueryFactory {

    @Getter
    private transient PeopleQueryProvider provider = new PeopleQueryProvider();

    private final FilteringButton[] postType = PersonnelButtonUtils.getPostTypeButtons();
    private final FilteringButton[] personality = PersonnelButtonUtils.getPersonalityButtons();
    private final FilteringButton[] skill = PersonnelButtonUtils.getSkillButtons();

    public Object readResolve() {
        provider = new PeopleQueryProvider();
        return this;
    }

    @Override
    public Set<Filter> getFilters() {
        final Set<Filter> filters = new LinkedHashSet<>();
        addSelectedOrAll(filters, postType, L10n.query("PERSONNEL_POST_TYPES"));
        addSelectedOrNone(filters, personality, L10n.query("PERSONNEL_PERSONALITY"), hasOfficers());
        addSelectedOrNone(filters, skill, L10n.query("PERSONNEL_SKILLS"), hasOfficers());
        return filters;
    }

    @Override
    protected Button[] getFinalComponents() {
        return new Button[] { new FindMatchingButton(this, L10n.common("PERSONNEL")) };
    }

    @Override
    protected List<Renderable> getQueryBuildingComponents() {
        final List<Renderable> elements = new LinkedList<>();
        elements.add(new ButtonGroup(sizeHelper, L10n.query("PERSONNEL_POST_TYPES"), postType, true));
        elements.add(
            new SectionHeader(sizeHelper.getGroupAndTextWidth(), L10n.query("OFFICERS_AND_MERCENARIES"), hasOfficers())
        );
        elements.add(new ButtonGroup(sizeHelper, L10n.query("PERSONNEL_PERSONALITY"), personality, hasOfficers()));
        elements.add(new ButtonGroup(sizeHelper, L10n.query("PERSONNEL_SKILLS"), skill, hasOfficers()));
        return elements;
    }

    private boolean hasOfficers() {
        final boolean hasAdmin = postType[0].isStateOn();
        final boolean hasOfficerOrMerc = postType[1].isStateOn() || postType[2].isStateOn();
        return hasOfficerOrMerc || (!hasAdmin && !hasOfficerOrMerc);
    }
}
