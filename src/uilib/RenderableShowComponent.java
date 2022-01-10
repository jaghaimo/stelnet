package uilib;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class RenderableShowComponent extends RenderableComponent {

    private int maxElements;
}
