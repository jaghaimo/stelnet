package stelnet.board.exploration;

import java.awt.*;
import stelnet.util.MemoryManager.IdAware;

public interface ButtonAware extends IdAware {
    String getTitle();

    String getCheckedKey();

    Color getColor();

    String getEnabledKey();
}
