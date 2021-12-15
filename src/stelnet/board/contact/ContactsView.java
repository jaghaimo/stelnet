package stelnet.board.contact;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import stelnet.filter.Filter;
import stelnet.filter.LogicalOr;
import stelnet.util.L10n;
import uilib.CustomPanel;
import uilib.Heading;
import uilib.HorizontalViewContainer;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.Spacer;
import uilib.UiConstants;
import uilib.VerticalViewContainer;
import uilib.property.Size;

@RequiredArgsConstructor
public class ContactsView implements RenderableFactory {

    private final ContactProvider provider = new ContactProvider();
    private final Set<ContactFilterButton> missionTypeButtons;
    private final Set<ContactFilterButton> importanceButtons;
    private final Map<MarketAPI, TrackingCargoFleetData> needingPickup;

    @Override
    public List<Renderable> create(Size size) {
        VerticalViewContainer contacts = new VerticalViewContainer(buildLeftPanel(size.getWidth() - 170));
        CustomPanel panel = new ContactsPanel(contacts, needingPickup);
        panel.setSize(new Size(size.getWidth() - 168, size.getHeight()));
        HorizontalViewContainer container = new HorizontalViewContainer(
            panel,
            new Spacer(UiConstants.DEFAULT_SPACER - 4),
            new VerticalViewContainer(buildRightPanel())
        );
        container.setSize(size);
        return Collections.<Renderable>singletonList(container);
    }

    private List<Renderable> buildLeftPanel(float width) {
        List<Renderable> elements = new LinkedList<>();
        addContacts(elements, width);
        return elements;
    }

    private List<Renderable> buildRightPanel() {
        List<Renderable> elements = new LinkedList<>();
        elements.add(new Heading(L10n.get(ContactsL10n.CONTACT_TYPE), 165));
        elements.add(new Spacer(UiConstants.DEFAULT_SPACER));
        elements.addAll(missionTypeButtons);
        elements.add(new Spacer(UiConstants.DEFAULT_SPACER * 3));
        elements.add(new Heading(L10n.get(ContactsL10n.IMPORTANCE), 165));
        elements.add(new Spacer(UiConstants.DEFAULT_SPACER));
        elements.addAll(importanceButtons);
        return elements;
    }

    private void addContacts(List<Renderable> elements, float width) {
        List<ContactIntel> contacts = provider.getContacts(getSelectedFilters());
        for (ContactIntel contact : contacts) {
            elements.add(new DisplayContact(contact, width));
        }
        if (elements.isEmpty()) {
            elements.add(new Paragraph(L10n.get(ContactsL10n.NONE), width));
        }
    }

    private List<Filter> getSelectedFilters() {
        List<Filter> selected = new LinkedList<>();
        selected.add(new LogicalOr(getSelectedFilters(missionTypeButtons), "contact_type"));
        selected.add(new LogicalOr(getSelectedFilters(importanceButtons), "importance"));
        return selected;
    }

    private List<Filter> getSelectedFilters(Set<ContactFilterButton> buttons) {
        List<Filter> selected = new LinkedList<>();
        for (ContactFilterButton button : buttons) {
            if (button.isStateOn()) {
                selected.add(button.getFilter());
            }
        }
        return selected;
    }
}
