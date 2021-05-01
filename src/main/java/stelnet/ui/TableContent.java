package stelnet.ui;

import stelnet.commodity.data.RowDataElement;

import java.util.List;

public interface TableContent {

    public Object[] getHeaders(float width);

    public List<RowDataElement> getRows();
}
