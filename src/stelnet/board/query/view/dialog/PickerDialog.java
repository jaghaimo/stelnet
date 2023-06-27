package stelnet.board.query.view.dialog;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.combat.EngagementResultAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import stelnet.board.query.view.add.QueryFactory;

@RequiredArgsConstructor
@Setter
public abstract class PickerDialog implements InteractionDialogPlugin {

    protected final QueryFactory factory;
    protected IntelUIAPI ui;
    protected InteractionDialogAPI dialog;

    @Override
    public void init(final InteractionDialogAPI dialog) {
        this.dialog = dialog;
        show();
    }

    @Override
    public void optionSelected(final String optionText, final Object optionData) {}

    @Override
    public void optionMousedOver(final String optionText, final Object optionData) {}

    @Override
    public void advance(final float amount) {}

    @Override
    public void backFromEngagement(final EngagementResultAPI battleResult) {}

    @Override
    public Object getContext() {
        return null;
    }

    @Override
    public Map<String, MemoryAPI> getMemoryMap() {
        return null;
    }

    protected void dismiss(final IntelInfoPlugin plugin) {
        dialog.dismiss();
        if (plugin != null) {
            ui.recreateIntelUI();
            ui.updateUIForItem(plugin);
        }
    }

    protected abstract void show();
}
