package stelnet.board.contact;

import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import java.util.Collections;
import java.util.List;
import uilib.Renderable;
import uilib.RenderableState;
import uilib.property.Size;

public class ContactsState implements RenderableState {

    public int getContactNumber() {
        return ContactIntel.getCurrentContacts();
    }

    @Override
    public List<Renderable> toRenderableList(Size size) {
        return Collections.emptyList();
    }
}
