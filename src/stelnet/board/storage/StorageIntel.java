package stelnet.board.storage;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import stelnet.board.IntelBasePlugin;
import stelnet.board.IntelRenderableInfo;
import stelnet.util.CommonL10n;
import stelnet.util.L10n;
import stelnet.util.ModConstants;
import stelnet.util.StelnetHelper;
import stelnet.widget.heading.MarketHeader;
import uilib.Renderable;
import uilib.RenderableIntelInfo;
import uilib.ShowCargo;
import uilib.ShowShips;
import uilib.Spacer;
import uilib.UiConstants;
import uilib.property.Size;

@Getter
public class StorageIntel extends IntelBasePlugin {

    private final SubmarketAPI storage;
    private final String tag = ModConstants.TAG_STORAGE;

    public StorageIntel(SubmarketAPI storage) {
        super(storage.getMarket().getFaction(), storage.getMarket().getPrimaryEntity());
        this.storage = storage;
    }

    @Override
    public int hashCode() {
        MarketAPI market = storage.getMarket();
        if (market != null) {
            return market.getId().hashCode();
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StorageIntel) {
            StorageIntel objIntel = (StorageIntel) obj;
            return getStorage().equals(objIntel.getStorage());
        }
        return super.equals(obj);
    }

    @Override
    public String getIcon() {
        return getFaction().getCrest();
    }

    @Override
    protected RenderableIntelInfo getIntelInfo() {
        return new IntelRenderableInfo(
            getLocationNameWithSystem(),
            L10n.get(CommonL10n.INTEL_LOCATION),
            getStorageContent(),
            L10n.get(CommonL10n.INTEL_FACTION),
            getFactionWithRel()
        );
    }

    @Override
    protected List<Renderable> getRenderableList(Size size) {
        MarketHeader marketHeader = new MarketHeader(storage.getMarket(), this);
        marketHeader.getPeekButton().setEnabled(false);
        marketHeader.getShowButton().setEnabled(false);
        CargoAPI cargo = storage.getCargo().createCopy();
        List<FleetMemberAPI> ships = storage.getCargo().getMothballedShips().getMembersListCopy();
        return Arrays.<Renderable>asList(
            marketHeader,
            new Spacer(UiConstants.DEFAULT_SPACER),
            new ShowCargo(cargo, L10n.get(CommonL10n.ITEMS), L10n.get(StorageL10n.INTEL_NO_ITEMS), size),
            new Spacer(UiConstants.DEFAULT_SPACER * 2),
            new ShowShips(ships, L10n.get(CommonL10n.SHIPS), L10n.get(StorageL10n.INTEL_NO_SHIPS), size)
        );
    }

    private String getStorageContent() {
        CargoAPI cargo = storage.getCargo();
        int itemCount = StelnetHelper.calculateItemQuantity(cargo.createCopy());
        int shipCount = cargo.getMothballedShips().getMembersListCopy().size();
        return L10n.get(StorageL10n.INTEL_CONTENT, itemCount, shipCount);
    }
}
