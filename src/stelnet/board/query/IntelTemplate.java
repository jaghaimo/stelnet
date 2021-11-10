package stelnet.board.query;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.List;
import lombok.Getter;
import stelnet.BaseIntel;
import stelnet.IntelInfo;
import stelnet.board.query.provider.QueryProvider;
import stelnet.util.TagConstants;
import uilib.Renderable;
import uilib.property.Size;

public class IntelTemplate extends BaseIntel {

    @Getter
    private final IntelInfo intelInfo;

    private final QueryProvider queryProvider;

    @Getter
    private final String tag = TagConstants.MARKET;

    public IntelTemplate(IntelInfo intelInfo, QueryProvider queryProvider, MarketAPI market) {
        super(market.getFaction(), market.getPrimaryEntity());
        this.intelInfo = intelInfo;
        this.queryProvider = queryProvider;
    }

    @Override
    protected List<Renderable> getRenderables(Size size) {
        return queryProvider.getRenderables(size);
    }
}
