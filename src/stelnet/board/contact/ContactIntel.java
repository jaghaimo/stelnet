package stelnet.board.contact;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import stelnet.BaseIntel;
import stelnet.CommonL10n;
import stelnet.IntelInfo;
import stelnet.board.storage.StorageL10n;
import stelnet.util.FactoryUtils;
import stelnet.util.L10n;
import stelnet.util.ModConstants;
import uilib.Heading;
import uilib.Renderable;
import uilib.RenderableIntelInfo;
import uilib.ShowCargo;
import uilib.ShowShips;
import uilib.Spacer;
import uilib.UiConstants;
import uilib.property.Size;

@Getter
public class ContactIntel extends BaseIntel {

    private final String tag = ModConstants.TAG_CONTACTS;
    private final TrackingCargoFleetData trackerData;

    public ContactIntel(MarketAPI market, TrackingCargoFleetData trackerData) {
        super(market.getFaction(), market.getPrimaryEntity());
        this.trackerData = trackerData;
    }

    public boolean hasContent() {
        return trackerData.hasAnyInCargo() || trackerData.hasAnyInFleet();
    }

    @Override
    public String getIcon() {
        return getFaction().getCrest();
    }

    @Override
    public boolean shouldRemoveIntel() {
        return !hasContent();
    }

    @Override
    protected RenderableIntelInfo getIntelInfo() {
        return new IntelInfo(
            getLocationNameWithSystem(),
            L10n.get(CommonL10n.INTEL_LOCATION),
            getNewContent(),
            L10n.get(CommonL10n.INTEL_FACTION),
            getFactionWithRel()
        );
    }

    @Override
    protected List<Renderable> getRenderableList(Size size) {
        List<Renderable> renderableList = new LinkedList<>();
        Color baseColor = getFactionForUIColors().getBaseUIColor();
        Color darkColor = getFactionForUIColors().getDarkUIColor();
        boolean hasCargo = trackerData.hasAnyInCargo();
        boolean hasFleet = trackerData.hasAnyInFleet();
        if (hasCargo) {
            CargoAPI cargo = FactoryUtils.createCargo(trackerData.getNewContentInCargo());
            renderableList.add(
                new Heading(L10n.get(StorageL10n.INTEL_HEADER_ITEMS, getLocationName()), baseColor, darkColor)
            );
            renderableList.add(new Spacer(UiConstants.DEFAULT_SPACER));
            renderableList.add(new ShowCargo(cargo, "", size));
        }
        if (hasCargo && hasFleet) {
            renderableList.add(new Spacer(2 * UiConstants.DEFAULT_SPACER));
        }
        if (hasFleet) {
            renderableList.add(
                new Heading(L10n.get(StorageL10n.INTEL_HEADER_SHIPS, getLocationName()), baseColor, darkColor)
            );
            renderableList.add(new Spacer(UiConstants.DEFAULT_SPACER));
            renderableList.add(new ShowShips(trackerData.getNewContentInFleet(), "", size));
        }
        renderableList.add(new Spacer(3 * UiConstants.DEFAULT_SPACER));
        renderableList.add(new ContactIntelDelete(size.getWidth(), this));
        return renderableList;
    }

    private String getNewContent() {
        int itemCount = trackerData.getNewContentInCargo().size();
        int shipCount = trackerData.getNewContentInFleet().size();
        return L10n.get(StorageL10n.INTEL_CONTENT, itemCount, shipCount);
    }
}
