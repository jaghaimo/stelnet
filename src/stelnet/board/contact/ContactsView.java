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
    public List<Renderable> create(final Size size) {
        final float width = size.getWidth() - 168;
        final VerticalViewContainer contacts = new VerticalViewContainer(buildLeftPanel(size.getWidth() - 170));
        final CustomPanel panel = new ContactsPanel(width, contacts, needingPickup);
        panel.setSize(new Size(width, size.getHeight()));
        final HorizontalViewContainer container = new HorizontalViewContainer(
            panel,
            new Spacer(UiConstants.DEFAULT_SPACER - 4),
            new VerticalViewContainer(buildRightPanel())
        );
        container.setSize(size);
        return Collections.<Renderable>singletonList(container);
    }

    private List<Renderable> buildLeftPanel(final float width) {
        final List<Renderable> elements = new LinkedList<>();
        final List<ContactIntel> contacts = provider.getContacts(getSelectedFilters());
        for (final ContactIntel contact : contacts) {
            elements.add(new DisplayContact(contact, width));
        }
        if (elements.isEmpty()) {
            elements.add(new Paragraph(L10n.contacts("NONE"), width));
        }
        return elements;
    }

    private List<Renderable> buildRightPanel() {
        final List<Renderable> elements = new LinkedList<>();
        elements.add(new Heading(L10n.contacts("CONTACT_TYPE"), 165));
        elements.add(new Spacer(UiConstants.DEFAULT_SPACER));
        elements.addAll(missionTypeButtons);
        elements.add(new Spacer(UiConstants.DEFAULT_SPACER * 3));
        elements.add(new Heading(L10n.contacts("IMPORTANCE"), 165));
        elements.add(new Spacer(UiConstants.DEFAULT_SPACER));
        elements.addAll(importanceButtons);
        return elements;
    }

    private List<Filter> getSelectedFilters() {
        final List<Filter> selected = new LinkedList<>();
        selected.add(new LogicalOr(getSelectedFilters(missionTypeButtons), "contact_type"));
        selected.add(new LogicalOr(getSelectedFilters(importanceButtons), "importance"));
        return selected;
    }

    private List<Filter> getSelectedFilters(final Set<ContactFilterButton> buttons) {
        final List<Filter> selected = new LinkedList<>();
        for (final ContactFilterButton button : buttons) {
            if (button.isStateOn()) {
                selected.add(button.getFilter());
            }
        }
        return selected;
    }
}
