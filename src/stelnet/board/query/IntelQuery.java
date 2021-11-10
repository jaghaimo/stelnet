package stelnet.board.query;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import stelnet.board.query.provider.QueryProvider;
import stelnet.filter.Filter;
import stelnet.util.IntelUtils;

@Getter
@RequiredArgsConstructor
public class IntelQuery {

    private final QueryProvider queryProvider;
    private final List<Filter> filters;
    private List<IntelInfoPlugin> intelList = new LinkedList<>();

    @Setter
    private boolean isEnabled = false;

    public void create() {
        intelList.addAll(queryProvider.getIntel(filters));
        enable();
    }

    public void disable() {
        changeState(false);
    }

    public void enable() {
        changeState(true);
    }

    public void refresh() {
        disable();
        intelList.clear();
        create();
    }

    public void toggle() {
        changeState(!isEnabled);
    }

    private void changeState(boolean state) {
        if (isEnabled == state) {
            return;
        }
        for (IntelInfoPlugin intel : intelList) {
            changeStateIfNeeded(state, intel);
        }
        setEnabled(state);
    }

    private void changeStateIfNeeded(boolean state, IntelInfoPlugin intel) {
        if (IntelUtils.has(intel) == state) {
            return;
        }
        if (state) {
            IntelUtils.add(intel, true);
        } else {
            IntelUtils.remove(intel);
        }
    }
}
