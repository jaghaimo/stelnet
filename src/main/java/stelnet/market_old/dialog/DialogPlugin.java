package stelnet.market_old.dialog;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.campaign.OptionPanelAPI;
import com.fs.starfarer.api.campaign.TextPanelAPI;
import com.fs.starfarer.api.campaign.VisualPanelAPI;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.combat.EngagementResultAPI;
import com.fs.starfarer.api.util.Misc;

import org.lwjgl.input.Keyboard;

import lombok.Getter;
import stelnet.helper.KeyboardHelper;
import stelnet.market_old.dialog.handler.DialogHandler;
import stelnet.market_old.filter.MutableFilterManager;
import stelnet.market_old.intel.IntelQuery;
import stelnet.market_old.intel.provider.IntelProvider;

@Getter
public class DialogPlugin implements InteractionDialogPlugin {

    private final MutableFilterManager filterManager;
    private final List<IntelQuery> queries;

    private DialogOption lastOption;
    private InteractionDialogAPI dialog;
    private OptionPanelAPI options;
    private TextPanelAPI textPanel;
    private VisualPanelAPI visual;

    public DialogPlugin(List<IntelQuery> q) {
        filterManager = new MutableFilterManager();
        queries = q;
    }

    @Override
    public void advance(float amount) {
    }

    @Override
    public void backFromEngagement(EngagementResultAPI result) {
    }

    @Override
    public Object getContext() {
        return null;
    }

    @Override
    public Map<String, MemoryAPI> getMemoryMap() {
        return null;
    }

    @Override
    public void init(InteractionDialogAPI d) {
        dialog = d;
        options = d.getOptionPanel();
        textPanel = d.getTextPanel();
        visual = d.getVisualPanel();
        visual.showImagePortion("stelnet", "dialog", 640, 400, 0, 0, 480, 300);

        addTitle("Market Search");
        textPanel.addPara("Welcome to Stellar Networks!");
        showMenu();
    }

    @Override
    public void optionMousedOver(String text, Object optionData) {
    }

    @Override
    public void optionSelected(String text, Object optionData) {
        if (optionData == null) {
            return;
        }

        DialogOption option = (DialogOption) optionData;
        DialogHandler handler = DialogHandlerFactory.getHandler(option, lastOption);
        lastOption = handler.handle(this);
    }

    public void addNewQuery(IntelProvider provider) {
        queries.add(new IntelQuery(provider, filterManager));
    }

    public void addOptions(DialogOption... intelOptions) {
        options.clearOptions();
        for (DialogOption option : intelOptions) {
            options.addOption(option.getName(), option);
        }
    }

    public void addText(String update) {
        textPanel.addPara(update);
    }

    public void addTitle(String title) {
        Color colorHighlight = Misc.getHighlightColor();
        textPanel.addPara(title, colorHighlight);
    }

    public void dismiss() {
        dialog.dismiss();
        KeyboardHelper.send(KeyEvent.VK_E);
    }

    public void setEscShortcut(DialogOption option) {
        options.setShortcut(option, Keyboard.KEY_ESCAPE, false, false, false, false);
    }

    public void showMenu() {
        lastOption = DialogOption.INIT;
        addOptions(DialogOption.STAFF, DialogOption.CARGO, DialogOption.SHIP, DialogOption.EXIT);
        setEscShortcut(DialogOption.EXIT);
    }
}
