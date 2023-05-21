package stelnet.board.exploration;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import java.util.List;
import lombok.RequiredArgsConstructor;
import uilib2.Drawable;

@RequiredArgsConstructor
public class ButtonFactory {

    private final IntelInfoPlugin intel;
    private final float width;

    public void addAll(List<Drawable> drawables, ExplorationL10n[] buttonTypes, String memoryKeyEnabled) {
        boolean withShift = false;
        for (ExplorationL10n buttonType : buttonTypes) {
            withShift = addConditionalButton(drawables, get(buttonType, withShift), withShift, memoryKeyEnabled);
        }
    }

    public void addAll(List<Drawable> drawables, List<FactionAPI> factions, String memoryKeyEnabled) {
        boolean withShift = false;
        for (FactionAPI faction : factions) {
            withShift = addConditionalButton(drawables, get(faction, withShift), withShift, memoryKeyEnabled);
        }
    }

    public ExplorationButton get(ExplorationL10n buttonType, boolean withShift) {
        return new EnumButton(buttonType, intel, width, withShift);
    }

    public ExplorationButton get(FactionAPI faction, boolean withShift) {
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
