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
import stelnet.util.ModConstants;
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

    private final String tag = ModConstants.TAG_COMMODITY;

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
    protected List<Renderable> getRenderableList(Size size) {
        float width = size.getWidth();
        FactionAPI faction = marketWrapper.getFaction();
        List<Renderable> elements = new ArrayList<>();
        elements.add(new Heading(marketWrapper.getName(), faction.getBaseUIColor(), faction.getDarkUIColor()));
        elements.add(new Image(faction.getLogo(), width, 128));
        elements.add(new Spacer(10f));
        addPriceChange(elements, width);
        addRelationship(elements, width);
        elements.add(new Spacer(30f));
        elements.add(new DeleteIntel(size.getWidth(), this));
        return elements;
    }

    private void addPriceChange(List<Renderable> elements, float width) {
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
            elements.add(priceChangeRenderable);
            elements.add(new Spacer(10f));
        }
    }

    private void addRelationship(List<Renderable> elements, float width) {
        FactionAPI faction = marketWrapper.getFaction();
        RelationshipAPI relationship = faction.getRelToPlayer();
        String translatedRep = relationship.getLevel().getDisplayName().toLowerCase();
        Paragraph relationshipRenderable = new Paragraph(
            L10n.get(CommonL10n.INTEL_OWNER_RELATIONSHIP, translatedRep),
            width
        );
        relationshipRenderable.setHighlightStrings(translatedRep);
        relationshipRenderable.setHighlightColors(relationship.getRelColor());
        elements.add(relationshipRenderable);
    }

    private String getTitle() {
        return L10n.get(CommodityL10n.INTEL_TITLE, action, commodity.getName(), Misc.getDGSCredits(price));
    }
}
