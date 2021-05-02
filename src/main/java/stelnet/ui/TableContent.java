package stelnet.ui;

import java.util.List;

import stelnet.commodity.data.RowDataElement;

public interface TableContent {

    public Object[] getHeaders(float width);

    public List<RowDataElement> getRows();
}
