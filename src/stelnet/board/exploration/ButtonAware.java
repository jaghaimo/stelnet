package stelnet.board.exploration;

import java.awt.Color;
import stelnet.util.MemoryHelper.IdAware;

public interface ButtonAware extends IdAware {
    public String getTitle();

    public String getCheckedKey();

    public Color getColor();

    public String getEnabledKey();
}
