package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.Fonts;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.Query;
import stelnet.board.query.QueryL10n;
import stelnet.util.ColorUtils;
import stelnet.util.L10n;
import uilib.RenderableComponent;
import uilib.property.Size;

@RequiredArgsConstructor
public class QueryInfo extends RenderableComponent {

    private final Query query;

    public QueryInfo(float width, Query query) {
        this(query);
        setSize(new Size(width, 10));
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        tooltip.setParaFont(Fonts.VICTOR_10);
        tooltip.setParaFontColor(ColorUtils.buttonHighlightColor());
        if (!query.isEnabled()) {
            tooltip.setParaFontColor(Misc.scaleAlpha(Misc.getDarkPlayerColor(), 0.4f));
        }
        tooltip.addPara(L10n.get(QueryL10n.QUERY_INFO, query.getCreatedAt(), query.getResultNumber()), 0);
    }
}
