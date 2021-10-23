package stelnet.market;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryState {

    private QueryTab activeTab = QueryTab.LIST;
}
