package stelnet.storage.data;

import java.util.List;

import stelnet.storage.FilterManager;

public interface DataProvider {

    public List<StorageData> getData(FilterManager filterManager);
}
