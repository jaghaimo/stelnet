package stelnet.market.panel;

import java.util.ArrayList;
import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.util.Misc;

public class ControlRow extends BoardRow {

    public final static String BUTTON_ADD = "HYPERNET_NEW_QUERY";
    public final static String BUTTON_DELETE_ALL = "HYPERNET_DELETE_ALL";
    public final static String BUTTON_REFRESH_ALL = "HYPERNET_REFRESH_ALL";
    public final static String BUTTON_TOGGLE_ALL = "HYPERNET_TOGGLE_ALL";

    private boolean isEnabled;

    public ControlRow(CustomPanelAPI panel, float width, boolean isEnabled) {
        super(panel, width);
        this.isEnabled = isEnabled;
    }

    @Override
    public float getHeight() {
        return super.getHeight() + 20f;
    }

    @Override
    protected List<BoardElement> getLeftElements() {
        List<BoardElement> elements = new ArrayList<>();
        elements.add(
                new ButtonElement(160f, 20f, "Add a New Query", BUTTON_ADD, true, Misc.getPositiveHighlightColor()));
        elements.add(new EmptyElement(0f, 20f));
        elements.add(new ButtonElement(160f, 20f, "Refresh All Queries", BUTTON_REFRESH_ALL, isEnabled,
                Misc.getButtonTextColor()));
        elements.add(new EmptyElement(0f, 20f));
        elements.add(new ButtonElement(160f, 20f, "Toggle All Queries", BUTTON_TOGGLE_ALL, isEnabled,
                Misc.getButtonTextColor()));

        return elements;
    }

    @Override
    protected List<BoardElement> getRightElements() {
        List<BoardElement> elements = new ArrayList<>();
        elements.add(new ButtonElement(100f, 20f, "Delete all", BUTTON_DELETE_ALL, isEnabled,
                Misc.getNegativeHighlightColor()));

        return elements;
    }
}
