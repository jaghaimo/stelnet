package stelnet.market.view;

import java.util.List;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.market.IntelQuery;
import stelnet.ui.Button;
import stelnet.ui.SimpleCallback;
import stelnet.ui.Size;

public class DeleteOneButton extends Button {

    public DeleteOneButton(final List<IntelQuery> queries, final int number) {
        super(new Size(120, 24), "Delete", true, Misc.getNegativeHighlightColor());
        setCallback(new SimpleCallback() {

            @Override
            public void confirm(IntelUIAPI ui) {
                queries.get(number).disable();
                queries.remove(number);
            }
        });
    }
}
