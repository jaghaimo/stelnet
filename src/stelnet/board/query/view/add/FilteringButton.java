package stelnet.board.query.view.add;

import stelnet.filter.Filter;

public interface FilteringButton {
    public Filter getFilter();

    public boolean isStateOn();
}
