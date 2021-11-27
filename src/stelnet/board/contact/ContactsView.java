package stelnet.board.contact;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.util.IntelUtils;
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

    private final FilteringButton[] importanceButtons;
    private final FilteringButton[] missionTypeButtons;

    @Override
    public List<Renderable> create(Size size) {
        return Collections.<Renderable>singletonList(
            new HorizontalViewContainer(
                new VerticalViewContainer(getContacts(size.getWidth() - 180)),
                new Spacer(UiConstants.DEFAULT_SPACER * 2),
                new VerticalViewContainer(getButtons())
            )
        );
    }

    private List<Renderable> getContacts(float size) {
        List<Renderable> elements = new LinkedList<>();
        List<ContactIntel> contacts = getAllContacts();
        for (ContactIntel contact : contacts) {
            elements.add(new DisplayContact(contact, size));
        }
        return elements;
    }

    private List<Renderable> getButtons() {
        List<Renderable> elements = new LinkedList<>();
        elements.add(new Paragraph("Importance", 150));
        elements.addAll(Arrays.<Renderable>asList(importanceButtons));
        elements.add(new Spacer(UiConstants.DEFAULT_SPACER));
        elements.add(new Paragraph("Type", 150));
        elements.addAll(Arrays.<Renderable>asList(missionTypeButtons));
        return elements;
    }

    private List<ContactIntel> getAllContacts() {
        List<ContactIntel> contacts = new LinkedList<>();
        List<IntelInfoPlugin> plugins = IntelUtils.getAll(ContactIntel.class);
        for (IntelInfoPlugin plugin : plugins) {
            contacts.add(0, (ContactIntel) plugin);
        }
        return contacts;
    }
}
