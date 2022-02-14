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
import uilib.Button;
import uilib.Image;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.Spacer;
import uilib.property.Size;

@RequiredArgsConstructor
public class CommodityIntelViewFactory implements RenderableFactory {

    private final MarketAPI market;
    private final CommodityIntel intel;

    @Override
    public List<Renderable> create(Size size) {
        float width = size.getWidth();
        List<Renderable> elements = new ArrayList<>();
        addMarketHeader(elements, width);
        addRelationship(elements, width);
        addDelete(elements, width);
        return elements;
    }

    private void addMarketHeader(List<Renderable> elements, float width) {
        MarketHeader marketHeader = new MarketHeader(market, intel);
        marketHeader.getShowButton().setEnabled(false);
        FactionAPI faction = market.getFaction();
        elements.add(marketHeader);
        elements.add(new Image(faction.getLogo(), width, 128));
        elements.add(new Spacer(10f));
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

    private void addDelete(List<Renderable> elements, float width) {
        FactionAPI faction = market.getFaction();
        Button delete = new DeleteIntel(width, intel);
        delete.setTextColor(faction.getBaseUIColor());
        delete.setBackgroundColor(faction.getDarkUIColor());
        elements.add(new Spacer(30f));
        elements.add(delete);
    }
}
