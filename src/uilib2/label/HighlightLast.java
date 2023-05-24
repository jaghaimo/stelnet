package uilib2.label;

import com.fs.starfarer.api.ui.LabelAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HighlightLast implements Highlight {

    private final String highlight;

    @Override
    public void highlight(LabelAPI label) {
        label.highlightLast(highlight);
    }
}
