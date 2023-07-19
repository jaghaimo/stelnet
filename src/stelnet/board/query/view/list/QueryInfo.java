package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.Fonts;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.Query;
import stelnet.util.L10n;
import uilib.ColorHelper;
import uilib.RenderableComponent;
import uilib.property.Size;

@RequiredArgsConstructor
public class QueryInfo extends RenderableComponent {

    private final Query query;

    public QueryInfo(final float width, final Query query) {
        this(query);
        setSize(new Size(width, 10));
    }

    @Override
    public void render(final TooltipMakerAPI tooltip) {
        tooltip.setParaFont(Fonts.VICTOR_10);
        tooltip.setParaFontColor(ColorHelper.buttonHighlightColor());
        if (!query.isEnabled()) {
            tooltip.setParaFontColor(Misc.scaleAlpha(Misc.getDarkPlayerColor(), 0.4f));
        }
        tooltip.addPara(L10n.query("QUERY_INFO", query.getCreatedAt(), query.getResultNumber()), 0);
    }
}
