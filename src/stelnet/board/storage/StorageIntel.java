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
import stelnet.util.L10n;
import stelnet.util.ModConstants;
import stelnet.util.StelnetHelper;
import stelnet.widget.heading.MarketHeader;
import uilib.*;
import uilib.property.Size;

@Getter
public class StorageIntel extends IntelBasePlugin {

    private final SubmarketAPI storage;
    private final String tag = ModConstants.TAG_STORAGE;

    public StorageIntel(final SubmarketAPI storage) {
        super(storage.getMarket().getFaction(), storage.getMarket().getPrimaryEntity());
        this.storage = storage;
    }

    @Override
    public int hashCode() {
        final MarketAPI market = storage.getMarket();
        if (market != null) {
            return market.getId().hashCode();
        }
        return 0;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof StorageIntel) {
            final StorageIntel objIntel = (StorageIntel) obj;
            return getStorage().equals(objIntel.getStorage());
        }
        return super.equals(obj);
    }

    @Override
    public String getIcon() {
        return getFaction().getCrest();
    }

    @Override
    protected List<Renderable> getRenderableList(final Size size) {
        final MarketHeader marketHeader = new MarketHeader(storage.getMarket(), this);
        marketHeader.getPeekButton().setEnabled(false);
        marketHeader.getShowButton().setEnabled(false);
        final CargoAPI cargo = storage.getCargo().createCopy();
        final List<FleetMemberAPI> ships = storage.getCargo().getMothballedShips().getMembersListCopy();
        return Arrays.<Renderable>asList(
            marketHeader,
            new Spacer(UiConstants.DEFAULT_SPACER),
            new ShowCargo(cargo, L10n.common("ITEMS"), L10n.storage("INTEL_NO_ITEMS"), size),
            new Spacer(UiConstants.DEFAULT_SPACER * 2),
            new ShowShips(ships, L10n.common("SHIPS"), L10n.storage("INTEL_NO_SHIPS"), size)
        );
    }

    @Override
    protected RenderableIntelInfo getIntelInfo() {
        return new IntelRenderableInfo(
            getLocationNameWithSystem(),
            L10n.common("INTEL_LOCATION"),
            getStorageContent(),
            L10n.common("INTEL_FACTION"),
            getFactionWithRel()
        );
    }

    private String getStorageContent() {
        final CargoAPI cargo = storage.getCargo();
        final int itemCount = StelnetHelper.calculateItemQuantity(cargo.createCopy());
        final int shipCount = cargo.getMothballedShips().getMembersListCopy().size();
        return L10n.storage("INTEL_CONTENT", itemCount, shipCount);
    }
}
