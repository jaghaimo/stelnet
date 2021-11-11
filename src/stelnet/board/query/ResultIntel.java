package stelnet.board.query;

import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import stelnet.BaseIntel;
import stelnet.IntelInfo;
import stelnet.util.SectorUtils;
import stelnet.util.TagConstants;
import uilib.Renderable;
import uilib.Table;
import uilib.property.Size;

@Getter
public class ResultIntel extends BaseIntel {

    private final QueryManager queryManager;
    private final ResultSet resultSet;

    @Getter
    private final String tag = TagConstants.MARKET;

    public ResultIntel(QueryManager queryManager, ResultSet resultSet) {
        super(SectorUtils.getPlayerFaction(), resultSet.getSystemToken());
        this.queryManager = queryManager;
        this.resultSet = resultSet;
    }

    @Override
    protected IntelInfo getIntelInfo() {
        return new IntelInfo(
            resultSet.getSystemName(),
            "Matching results", // todo l10n
            String.valueOf(resultSet.getResultNumber()),
            "Matching markets", // todo l10n
            String.valueOf(resultSet.getMarketNumber())
        );
    }

    @Override
    protected List<Renderable> getRenderables(Size size) {
        List<Renderable> renderables = new LinkedList<>();
        renderables.add(new Table("Results", size.getWidth(), 0, resultSet));
        return renderables;
    }

    @Override
    public boolean hasLargeDescription() {
        return true;
    }

    @Override
    public boolean hasSmallDescription() {
        return false;
    }
}
