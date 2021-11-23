package uilib;

import java.util.List;

public interface TableContent {
    public Object[] getHeaders(float width);

    public List<? extends TableContentRow> getRows();
}
