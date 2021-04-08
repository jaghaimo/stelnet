package stelnet.commodity.ui;

import java.util.List;

public interface TableContent {

    public Object[] getHeaders(float width);

    public List<Object[]> getRows();

    public int getSize();
}
