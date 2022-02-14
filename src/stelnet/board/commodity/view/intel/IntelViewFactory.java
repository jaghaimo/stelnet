package stelnet.board.commodity.view.intel;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.RelationshipAPI;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.CommonL10n;
import stelnet.board.commodity.CommodityIntel;
import stelnet.board.commodity.view.board.DeleteIntel;
import stelnet.util.L10n;
import stelnet.widget.heading.MarketHeader;
import uilib.Image;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.Spacer;
import uilib.property.Size;

@RequiredArgsConstructor
public class IntelViewFactory implements RenderableFactory {

    private final MarketAPI market;
    private final CommodityIntel intel;

    @Override
    public List<Renderable> create(Size size) {
        float width = size.getWidth();
        MarketHeader marketHeader = new MarketHeader(market, intel);
        marketHeader.getShowButton().setEnabled(false);
        FactionAPI faction = market.getFaction();
        List<Renderable> elements = new ArrayList<>();
        elements.add(marketHeader);
        elements.add(new Image(faction.getLogo(), width, 128));
        elements.add(new Spacer(10f));
        // addPriceChange(elements, width);
        addRelationship(elements, width);
        elements.add(new Spacer(30f));
        elements.add(new DeleteIntel(size.getWidth(), intel));
        return elements;
    }

    // private void addPriceChange(List<Renderable> elements, float width) {
    //     if (isEnding()) {
    //         float currentPrice = priceProvider.getPriceAmount(market);
    //         String priceChangeText = L10n.get(
    //             CommodityL10n.PRICE_CHANGED,
    //             Misc.getDGSCredits(price),
    //             Misc.getDGSCredits(currentPrice)
    //         );
    //         Paragraph priceChangeRenderable = new Paragraph(priceChangeText, width);
    //         priceChangeRenderable.setHighlightStrings(Misc.getDGSCredits(price), Misc.getDGSCredits(currentPrice));
    //         priceChangeRenderable.setHighlightColors(Misc.getHighlightColor(), Misc.getHighlightColor());
    //         elements.add(priceChangeRenderable);
    //         elements.add(new Spacer(10f));
    //     }
    // }

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
}
