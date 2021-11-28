package stelnet.board.contact;

import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import stelnet.filter.Filter;
import stelnet.filter.LogicalOr;
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

    @Override
    public List<Renderable> create(Size size) {
        return Collections.<Renderable>singletonList(
            new HorizontalViewContainer(
                new VerticalViewContainer(getContacts(size.getWidth() - 170)),
                new Spacer(UiConstants.DEFAULT_SPACER),
                new VerticalViewContainer(getButtons())
            )
        );
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

    private List<Renderable> getContacts(float width) {
        List<Renderable> elements = new LinkedList<>();
        List<ContactIntel> contacts = provider.getContacts(getSelectedFilters());
        for (ContactIntel contact : contacts) {
            elements.add(new DisplayContact(contact, width));
        }
        if (elements.isEmpty()) {
            elements.add(new Paragraph("No matching contacts found - change your filtering criteria.", width));
        }
        elements.add(0, new Spacer(UiConstants.DEFAULT_SPACER));
        elements.add(0, new Heading("Contact List", width));
        return elements;
    }

    private List<Renderable> getButtons() {
        List<Renderable> elements = new LinkedList<>();
        elements.add(new Heading("Contact Type", 165));
        elements.add(new Spacer(UiConstants.DEFAULT_SPACER));
        elements.addAll(missionTypeButtons);
        elements.add(new Spacer(UiConstants.DEFAULT_SPACER * 3));
        elements.add(new Heading("Importance", 165));
        elements.add(new Spacer(UiConstants.DEFAULT_SPACER));
        elements.addAll(importanceButtons);
        return elements;
    }
}
