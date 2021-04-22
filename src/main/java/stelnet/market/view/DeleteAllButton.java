package stelnet.market.view;

import java.util.List;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.market.IntelQuery;
import stelnet.ui.Button;
import stelnet.ui.SimpleCallback;
import stelnet.ui.Size;

public class DeleteAllButton extends Button {

    public DeleteAllButton(final List<IntelQuery> queries) {
        super(new Size(120, 24), "Delete All", !queries.isEmpty(), Misc.getNegativeHighlightColor());
        setCallback(new SimpleCallback() {

            @Override
            public void confirm(IntelUIAPI ui) {
                for (IntelQuery query : queries) {
                    query.disable();
                }
                queries.clear();
            }

            @Override
            public boolean hasPrompt() {
                return true;
            }

            @Override
            public void prompt(TooltipMakerAPI tooltipMaker) {
                tooltipMaker.addPara("Are you sure you want to delete ALL intel queries?", Misc.getTextColor(), 0f);
            }
        });
    }
}
