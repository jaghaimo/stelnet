package stelnet.board.market.view;

import lombok.Getter;

@Getter
public class QueryTypeFactory {

    private final QueryTypeButton[] buttons;

    public QueryTypeFactory() {
        buttons =
            new QueryTypeButton[] {
                new QueryTypeButton(this, "Personnel"),
                new QueryTypeButton(this, "Weapons"),
                new QueryTypeButton(this, "Fighters"),
                new QueryTypeButton(this, "Modspecs"),
                new QueryTypeButton(this, "Ships"),
            };
    }

    public void setStateOn(QueryTypeButton active) {
        for (QueryTypeButton button : buttons) {
            button.setStateOn(false);
            if (active.equals(button)) {
                button.setStateOn(true);
            }
        }
    }
}
