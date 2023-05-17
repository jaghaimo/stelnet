package stelnet.board.exploration;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionAPI;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import stelnet.filter.FactionIsRaiding;
import stelnet.util.CollectionUtils;
import uilib.Renderable;
import uilib.RenderableState;
import uilib.Spacer;
import uilib.UiConstants;
import uilib.property.Size;

public class ExplorationState implements RenderableState {

    private Map<String, Boolean> typeMap = new LinkedHashMap<>();
    private Map<String, Boolean> factionMap = new LinkedHashMap<>();
    private Map<String, Boolean> memoryBankMap = new LinkedHashMap<>();

    private void setDefaults(Map<String, Boolean> collection, ExplorationButton[] buttons, Boolean defaultValue) {
        for (ExplorationButton button : buttons) {
            setDefault(collection, button.getKey(), defaultValue);
        }
    }

    // private void setDefaults(Map<String, Boolean> collection, String[] keys, Boolean defaultValue) {
    //     for (String key : keys) {
    //         setDefault(collection, key, defaultValue);
    //     }
    // }

    private void setDefault(Map<String, Boolean> collection, String key, Boolean defaultValue) {
        if (!collection.containsKey(key)) {
            collection.put(key, defaultValue);
        }
    }

    private void addTypes(List<Renderable> renderables, Size size) {
        ExplorationButton[] buttons = {
            new TranslatableButton(ExplorationL10n.TYPE_BASES, size),
            new TranslatableButton(ExplorationL10n.TYPE_MEMORY_BANKS, size),
            new TranslatableButton(ExplorationL10n.TYPE_MISSIONS, size),
            new TranslatableButton(ExplorationL10n.TYPE_OTHER, size),
        };
        setDefaults(typeMap, buttons, true);
        renderables.addAll(Arrays.asList(buttons));
        addSpacer(renderables);
    }

    private void addFactions(List<Renderable> renderables, Size size) {
        List<FactionAPI> factions = Global.getSector().getAllFactions();
        CollectionUtils.reduce(factions, new FactionIsRaiding());
        for (FactionAPI faction : factions) {
            setDefault(factionMap, faction.getId(), true);
            // TODO: add buttons
        }
        addSpacer(renderables);
    }

    private void addMissions(List<Renderable> renderables, Size size) {}

    private void addSpacer(List<Renderable> renderables) {
        renderables.add(new Spacer(UiConstants.DEFAULT_SPACER));
    }

    @Override
    public List<Renderable> toRenderableList(Size size) {
        Size buttonSize = new Size(size.getWidth(), UiConstants.DEFAULT_BUTTON_HEIGHT);
        List<Renderable> renderables = new LinkedList<>();
        addTypes(renderables, buttonSize);
        addFactions(renderables, buttonSize);
        addMissions(renderables, buttonSize);
        return renderables;
        // return Arrays.<Renderable>asList(
        //     new Heading("Type"),
        //     new Checkbox("Bases", buttonSize),
        //     new Checkbox("Memory Banks", buttonSize),
        //     new Checkbox("Missions", buttonSize),
        //     new Checkbox("Others", buttonSize),
        //     new Spacer(UiConstants.DEFAULT_SPACER),
        //     new Heading("Base"),
        //     new Checkbox("Luddic Path", buttonSize),
        //     new Checkbox("Pirates", buttonSize),
        //     new Spacer(UiConstants.DEFAULT_SPACER),
        //     new Heading("Memory Bank"),
        //     new Checkbox("Blueprint", buttonSize),
        //     new Checkbox("Debris Field", buttonSize),
        //     new Checkbox("Equipment Cache", buttonSize),
        //     new Checkbox("Habitable World", buttonSize),
        //     new Checkbox("Orbital Habitat", buttonSize),
        //     new Checkbox("Research Station", buttonSize),
        //     new Checkbox("Resource Location", buttonSize),
        //     new Checkbox("Ruins Location", buttonSize),
        //     new Checkbox("Supply Cache", buttonSize),
        //     new Checkbox("Survey Data", buttonSize)
        // );
    }
}
