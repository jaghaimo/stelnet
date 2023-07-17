package stelnet.board.contact;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import com.fs.starfarer.api.impl.campaign.missions.hub.HubMission;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import stelnet.util.StelnetHelper;
import uilib2.Drawable;
import uilib2.intel.DrawableIntelInfo;
import uilib2.intel.SmallIntel;

@Getter
public class ContactBoard extends SmallIntel {

    private final transient String icon = StelnetHelper.getSpriteName("contacts");
    private final transient String mainTag = Tags.INTEL_CONTACTS;
    private final transient IntelSortTier sortTier = IntelSortTier.TIER_0;
    private final transient ContactModel model = new ContactModel(this);
    private final transient ContactView view = new ContactView(model);

    public Object readResolve() {
        return new ContactBoard();
    }

    protected List<Drawable> getDrawableList(final float width, final float height) {
        return view.getDrawables(width, height);
    }

    @Override
    protected DrawableIntelInfo getIntelInfo() {
        model.updateContacts(getAllContactIntel());
        model.updateMissions(getAllMissionIntel());
        model.pruneAwaitingCollection();
        return view.getIntelInfo();
    }

    private List<IntelInfoPlugin> getAllContactIntel() {
        return new ArrayList<>(Global.getSector().getIntelManager().getIntel(ContactIntel.class));
    }

    private List<IntelInfoPlugin> getAllMissionIntel() {
        return new ArrayList<>(Global.getSector().getIntelManager().getIntel(HubMission.class));
    }
}
