package stelnet.board.commodity;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.RelationshipAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import stelnet.BaseIntel;
import stelnet.CommonL10n;
import stelnet.IntelInfo;
import stelnet.board.commodity.price.Price;
import stelnet.board.commodity.view.DeleteIntel;
import stelnet.util.L10n;
import stelnet.util.ModConstants;
import stelnet.widget.heading.MarketHeader;
import uilib.Image;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.Spacer;
import uilib.property.Size;

@Getter
public class CommodityIntel extends BaseIntel {

    private final String action;
    private final CommoditySpecAPI commodity;
    private final MarketAPI market;
    private final Price priceProvider;
    private final float price;
    private final String tag = ModConstants.TAG_COMMODITY;

    public CommodityIntel(String action, CommoditySpecAPI commodity, MarketAPI market, Price priceProvider) {
        super(market.getFaction(), market.getPrimaryEntity());
        this.action = action;
        this.commodity = commodity;
        this.market = market;
        this.priceProvider = priceProvider;
        this.price = priceProvider.getPriceAmount(market);
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
        return Math.abs(price - priceProvider.getPriceAmount(market)) > 1;
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
        MarketHeader marketHeader = new MarketHeader(market, this);
        marketHeader.getShowButton().setEnabled(false);
        FactionAPI faction = market.getFaction();
        List<Renderable> elements = new ArrayList<>();
        elements.add(marketHeader);
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
            float currentPrice = priceProvider.getPriceAmount(market);
            String priceChangeText = L10n.get(
                CommodityL10n.PRICE_CHANGED,
                Misc.getDGSCredits(price),
                Misc.getDGSCredits(currentPrice)
            );
            Paragraph priceChangeRenderable = new Paragraph(priceChangeText, width);
            priceChangeRenderable.setHighlightStrings(Misc.getDGSCredits(price), Misc.getDGSCredits(currentPrice));
            priceChangeRenderable.setHighlightColors(Misc.getHighlightColor(), Misc.getHighlightColor());
            elements.add(priceChangeRenderable);
            elements.add(new Spacer(10f));
        }
    }

    private void addRelationship(List<Renderable> elements, float width) {
        FactionAPI faction = market.getFaction();
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
