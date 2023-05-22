package stelnet.board.exploration.factory;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.board.exploration.EnumButton;
import stelnet.board.exploration.ExplorationButton;
import stelnet.board.exploration.ExplorationL10n;
import stelnet.board.exploration.FactionButton;
import uilib2.Drawable;

@RequiredArgsConstructor
public class ButtonFactory {

    private final IntelInfoPlugin intel;
    private final float width;

    public void addTypes(List<Drawable> drawables, List<ExplorationL10n> types, String memoryKeyEnabled) {
        boolean withShift = false;
        for (ExplorationL10n type : types) {
            withShift = addConditionalButton(drawables, get(type, withShift), withShift, memoryKeyEnabled);
        }
    }

    public void addFactions(List<Drawable> drawables, List<FactionAPI> factions, String memoryKeyEnabled) {
        boolean withShift = false;
        for (FactionAPI faction : factions) {
            withShift = addConditionalButton(drawables, get(faction, withShift), withShift, memoryKeyEnabled);
        }
    }

    private ExplorationButton get(ExplorationL10n buttonType, boolean withShift) {
        return new EnumButton(buttonType, intel, width, withShift);
    }

    private ExplorationButton get(FactionAPI faction, boolean withShift) {
        return new FactionButton(faction, intel, width, withShift);
    }

    private boolean addConditionalButton(
        List<Drawable> drawables,
        ExplorationButton button,
        boolean withShift,
        String memoryKeyEnabled
    ) {
        if (memoryKeyEnabled != null) {
            button.setMemoryKeyEnabledOverwrite(memoryKeyEnabled);
        }
        drawables.add(button);
        return !withShift;
    }
}
