package stelnet.board.query.view.add;

import java.util.List;
import uilib.AbstractRenderable;
import uilib.Heading;
import uilib.Renderable;
import uilib.VerticalViewContainer;
import uilib.property.Location;
import uilib.property.Size;

public class PreviewHelper {

    public void addPreview(List<Renderable> containers, AbstractRenderable cargo, Size size) {
        Heading heading = new Heading("Preview");
        heading.setSize(new Size(size.getWidth(), 25));
        heading.setLocation(Location.TOP_RIGHT);
        cargo.setSize(size.reduce(new Size(0, 25)));
        cargo.setLocation(Location.TOP_RIGHT);
        VerticalViewContainer verticalView = new VerticalViewContainer(heading, cargo);
        verticalView.setSize(size.reduce(new Size(0, 25)));
        verticalView.setLocation(Location.TOP_RIGHT);
        containers.add(verticalView);
    }
}
