package stelnet.board.exploration;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import stelnet.filter.Filter;
import stelnet.util.MemoryHelper;
import uilib2.intel.IntelUiAction;

@Log4j
@RequiredArgsConstructor
public class AddFilterAction implements IntelUiAction {

    private final Set<Filter> filters;
    private final String memoryKey;
    private final Filter filter;

    public AddFilterAction(Set<Filter> filters, Enum<?> key, Filter filter, String memoryKeyPrefix) {
        this(filters, MemoryHelper.key(memoryKeyPrefix, key, "Checked"), filter);
    }

    public AddFilterAction(Set<Filter> filters, FactionAPI faction, Filter filter, String memoryKeyPrefix) {
        this(filters, MemoryHelper.key(memoryKeyPrefix, faction, "Checked"), filter);
    }

    @Override
    public void act(IntelUIAPI ui) {
        boolean wantsFilter = MemoryHelper.getBoolean(memoryKey, true);
        if (wantsFilter) {
            log.debug("Adding filter for memory key: " + memoryKey);
            filters.add(filter);
        }
    }
}
