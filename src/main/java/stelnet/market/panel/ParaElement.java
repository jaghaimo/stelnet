package stelnet.market.panel;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.market.IntelQuery;

public class ParaElement extends BoardElement {

    private boolean isActive;
    private String text;

    public ParaElement(float width, float height, IntelQuery query) {
        super(width, height);
        this.isActive = query.isEnabled() && !query.isStale();
        this.text = query.getDescription();
    }

    public ParaElement(float width, float height, boolean isActive, String description) {
        super(width, height);
        this.isActive = isActive;
        this.text = description;
    }

    @Override
    public void render(TooltipMakerAPI inner) {
        if (isActive) {
            inner.addPara(text, 8f);
        } else {
            inner.addPara(text, Misc.getGrayColor(), 8f);
        }
    }
}
