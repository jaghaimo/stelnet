package stelnet.market.view;

import java.util.List;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.market.IntelQuery;
import stelnet.ui.Button;
import stelnet.ui.SimpleCallback;
import stelnet.ui.Size;

public class RefreshAllButton extends Button {

    public RefreshAllButton(final List<IntelQuery> queries) {
        super(new Size(120, 24), "Refresh All", !queries.isEmpty(), Misc.getButtonTextColor());
        setCallback(new SimpleCallback() {
            @Override
            public void confirm(IntelUIAPI ui) {
                for (IntelQuery query : queries) {
                    query.refresh();
                }
            }
        });
    }
}
