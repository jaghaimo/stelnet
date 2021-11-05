package stelnet.board.commodity;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.characters.RelationshipAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import stelnet.BaseIntel;
import stelnet.CommonL10n;
import stelnet.IntelInfo;
import stelnet.board.commodity.market.MarketApiWrapper;
import stelnet.board.commodity.market.price.Price;
import stelnet.board.commodity.view.button.DeleteIntel;
import stelnet.util.L10n;
import stelnet.util.TagConstants;
import uilib.Heading;
import uilib.Image;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.Spacer;
import uilib.property.Size;

@Getter
public class CommodityIntel extends BaseIntel {

    private final String action;
    private final CommoditySpecAPI commodity;
    private final MarketApiWrapper marketWrapper;
    private final Price priceProvider;
    private final float price;

    public CommodityIntel(
        String action,
        CommoditySpecAPI commodity,
        MarketApiWrapper marketWrapper,
        Price priceProvider
    ) {
        super(marketWrapper.getFaction(), marketWrapper.getPrimaryEntity());
        this.action = action;
        this.commodity = commodity;
        this.marketWrapper = marketWrapper;
        this.priceProvider = priceProvider;
        this.price = marketWrapper.getPriceAmount();
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
        return Math.abs(price - marketWrapper.getPriceAmount()) > 1;
    }

    public String getCommodityId() {
        return commodity.getId();
    }

    @Override
    protected IntelInfo getIntelInfo() {
        return new IntelInfo(
            getTitle(),
            L10n.get(CommonL10n.INTEL_LOCATION),
            getLocationNameWithSystem(),
            L10n.get(CommonL10n.INTEL_FACTION),
            getFactionWithRel()
        );
    }

    @Override
    protected List<Renderable> getRenderables(Size size) {
        float width = size.getWidth();
        FactionAPI faction = marketWrapper.getFaction();
        List<Renderable> renderables = new ArrayList<>();
        renderables.add(new Heading(marketWrapper.getName(), faction.getBaseUIColor(), faction.getDarkUIColor()));
        renderables.add(new Image(faction.getLogo(), width, 128));
        renderables.add(new Spacer(10f));
        addPriceChange(renderables, width);
        addRelationship(renderables, width);
        renderables.add(new Spacer(30f));
        renderables.add(new DeleteIntel(size.getWidth(), this));
        return renderables;
    }

    @Override
    protected String getTag() {
        return TagConstants.COMMODITY;
    }

    private void addPriceChange(List<Renderable> renderables, float width) {
        if (isEnding()) {
            String priceChangeText = L10n.get(
                CommodityL10n.PRICE_CHANGED,
                Misc.getDGSCredits(price),
                Misc.getDGSCredits(marketWrapper.getPriceAmount())
            );
            Paragraph priceChangeRenderable = new Paragraph(priceChangeText, width);
            priceChangeRenderable.setHighlightStrings(
                Misc.getDGSCredits(price),
                Misc.getDGSCredits(marketWrapper.getPriceAmount())
            );
            priceChangeRenderable.setHighlightColors(Misc.getHighlightColor(), Misc.getHighlightColor());
            renderables.add(priceChangeRenderable);
            renderables.add(new Spacer(10f));
        }
    }

    private void addRelationship(List<Renderable> renderables, float width) {
        FactionAPI faction = marketWrapper.getFaction();
        RelationshipAPI relationship = faction.getRelToPlayer();
        String translatedRep = L10n.get(relationship.getLevel());
        Paragraph relationshipRenderable = new Paragraph(
            L10n.get(CommonL10n.INTEL_OWNER_RELATIONSHIP, translatedRep),
            width
        );
        relationshipRenderable.setHighlightStrings(translatedRep);
        relationshipRenderable.setHighlightColors(relationship.getRelColor());
        renderables.add(relationshipRenderable);
    }

    private String getTitle() {
        return L10n.get(CommodityL10n.INTEL_TITLE, action, commodity.getName(), Misc.getDGSCredits(price));
    }
}
