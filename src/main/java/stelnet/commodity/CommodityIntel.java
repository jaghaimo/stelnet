package stelnet.commodity;

import java.awt.Color;
import java.util.List;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.characters.RelationshipAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import lombok.Getter;
import stelnet.BaseIntel;
import stelnet.IntelInfo;
import stelnet.commodity.market.MarketApiWrapper;
import stelnet.commodity.market.price.Price;
import stelnet.ui.Renderable;
import stelnet.ui.Size;

@Getter
public class CommodityIntel extends BaseIntel {

    public final static String TAG = "stelnetCommodity";

    private final String action;
    private final CommoditySpecAPI commodity;
    private final MarketApiWrapper market;
    private final IntelTracker tracker;
    private final Price priceProvider;
    private final float price;

    public CommodityIntel(String action, CommoditySpecAPI commodity, MarketApiWrapper market, IntelTracker tracker,
            Price priceProvider) {
        super(market.getFaction(), market.getPrimaryEntity());
        this.action = action;
        this.commodity = commodity;
        this.market = market;
        this.tracker = tracker;
        this.priceProvider = priceProvider;
        this.price = market.getPriceAmount();
    }

    @Override
    public void buttonPressConfirmed(Object buttonId, IntelUIAPI ui) {
        // TODO remove once using Renderable
        delete();
        ui.recreateIntelUI();
    }

    @Override
    public void createConfirmationPrompt(Object buttonId, TooltipMakerAPI prompt) {
        // TODO remove once using Renderable
        if (buttonId == BUTTON_DELETE) {
            prompt.addPara("Are you sure you want to delete intel for this entry?", Misc.getTextColor(), 0f);
        }
    }

    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        Color bulletColor = getBulletColorForMode(mode);
        info.addPara(getTitle(), getTitleColor(mode), 0f);
        info.beginGridFlipped(300f, 1, Misc.getTextColor(), 80f, 10f);
        info.addToGrid(0, 0, market.getName(), "Location", bulletColor);
        info.addToGrid(0, 1, market.getDisplayName(), "Faction", bulletColor);
        info.addToGrid(0, 2, market.getStarSystem(), "System", bulletColor);
        info.addGrid(3f);
    }

    @Override
    public void createSmallDescription(TooltipMakerAPI info, float width, float height) {
        // TODO remove once using Renderable
        FactionAPI faction = market.getFaction();
        RelationshipAPI relationship = faction.getRelToPlayer();
        String reputation = relationship.getLevel().getDisplayName();
        info.addSectionHeading(market.getName(), faction.getBaseUIColor(), faction.getDarkUIColor(), Alignment.MID, 5f);
        info.addImage(faction.getLogo(), width, 128, 10f);
        if (isEnding()) {
            info.addPara("The original price of %s has changed to %s.", 5f, Misc.getTextColor(),
                    Misc.getHighlightColor(), Misc.getDGSCredits(price), Misc.getDGSCredits(market.getPriceAmount()));
        }
        info.addPara("The owner of this market is " + reputation.toLowerCase() + " towards you.", 10f,
                Misc.getTextColor(), relationship.getRelColor(), reputation.toLowerCase());
        info.addPara("", 20f);
        addDeleteButton(info, width, "Delete entry");
    }

    @Override
    public String getIcon() {
        return commodity.getIconName();
    }

    @Override
    public IntelSortTier getSortTier() {
        return IntelSortTier.TIER_1;
    }

    @Override
    public String getSortString() {
        return getTitle();
    }

    @Override
    public boolean isEnding() {
        return Math.abs(price - market.getPriceAmount()) > 1;
    }

    public void delete() {
        tracker.remove(this);
    }

    public String getCommodityId() {
        return commodity.getId();
    }

    @Override
    protected IntelInfo getIntelInfo() {
        return new IntelInfo(getTitle(), "Location", getLocationNameWithSystem(), "Faction", getFactionWithRel());
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected List<Renderable> getRenderables(Size size) {
        // TODO Auto-generated method stub
        return super.getRenderables(size);
    }

    private String getTitle() {
        return String.format("%s %s for %s", action, commodity.getName(), Misc.getDGSCredits(price));
    }
}
