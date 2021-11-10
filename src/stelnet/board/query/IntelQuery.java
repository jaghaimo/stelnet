package stelnet.board.query;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Delegate;
import stelnet.board.query.provider.QueryProvider;
import stelnet.filter.Filter;
import stelnet.util.IntelUtils;

@Getter
@RequiredArgsConstructor
public class IntelQuery {

    private final QueryProvider queryProvider;
    private final Filter filter;

    @Delegate
    private List<BaseIntelPlugin> intelList = new LinkedList<>();

    @Setter
    private boolean isEnabled = true;

    public void create() {
        // TODO: Implement this
    }

    public void disable() {
        changeState(false);
    }

    public void enable() {
        changeState(true);
    }

    public void refresh() {
        disable();
        clear();
        create();
    }

    public void toggle() {
        changeState(!isEnabled);
    }

    private void changeState(boolean state) {
        if (isEnabled == state) {
            return;
        }
        for (BaseIntelPlugin intel : intelList) {
            change(state, intel);
        }
        setEnabled(state);
    }

    private void change(boolean state, IntelInfoPlugin intel) {
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
