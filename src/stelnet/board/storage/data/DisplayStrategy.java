package stelnet.board.storage.data;

import java.util.List;
import stelnet.board.storage.FilterManager;

public interface DisplayStrategy {
    public List<SubmarketData> getData(FilterManager filterManager);
}
