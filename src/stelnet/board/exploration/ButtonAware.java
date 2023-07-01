package stelnet.board.exploration;

import stelnet.util.MemoryHelper.IdAware;

public interface ButtonAware extends IdAware {
    public String getTitle();

    public String getCheckedKey();

    public String getEnabledKey();
}
