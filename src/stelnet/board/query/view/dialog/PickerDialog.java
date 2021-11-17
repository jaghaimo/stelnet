package stelnet.board.query.view.dialog;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.combat.EngagementResultAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.view.add.QueryFactory;

@RequiredArgsConstructor
public abstract class PickerDialog implements InteractionDialogPlugin {

    protected final QueryFactory factory;
    protected final IntelUIAPI ui;
    protected InteractionDialogAPI dialog;

    @Override
    public void init(InteractionDialogAPI dialog) {
        this.dialog = dialog;
        show();
    }

    @Override
    public void optionSelected(String optionText, Object optionData) {}

    @Override
    public void optionMousedOver(String optionText, Object optionData) {}

    @Override
    public void advance(float amount) {}

    @Override
    public void backFromEngagement(EngagementResultAPI battleResult) {}

    @Override
    public Object getContext() {
        return null;
    }

    @Override
    public Map<String, MemoryAPI> getMemoryMap() {
        return null;
    }

    protected void dismiss() {
        dialog.dismiss();
        ui.recreateIntelUI();
    }

    protected abstract void show();
}
