package stelnet.ui;

import java.util.List;

public interface TableContent {

    public Object[] getHeaders(float width);

    public List<TableContentRow> getRows();
}
