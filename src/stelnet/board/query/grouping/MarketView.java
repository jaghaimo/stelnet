package stelnet.board.query.grouping;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.List;
import stelnet.board.query.ResultIntel;
import stelnet.board.query.ResultSet;
import stelnet.board.query.ResultView;
import uilib.Renderable;

public class MarketView extends ResultView {

    public MarketView(ResultIntel intel, ResultSet resultSet) {
        super(intel, resultSet);
    }

    @Override
    protected void addPeople(List<Renderable> elements, MarketAPI market, float width) {}

    @Override
    protected void addItems(List<Renderable> elements, MarketAPI market, float width) {}

    @Override
    protected void addShips(List<Renderable> elements, MarketAPI market, float width) {}
}
