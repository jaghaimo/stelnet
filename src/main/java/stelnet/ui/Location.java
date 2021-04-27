package stelnet.ui;

import com.fs.starfarer.api.ui.PositionAPI;

import lombok.Getter;

public enum Location {

    TOP_LEFT(1, 1) {
        @Override
        public void render(PositionAPI position, float x, float y) {
            position.inTL(x, y);
        }

    },
    TOP_RIGHT(-1, 1) {
        @Override
        public void render(PositionAPI position, float x, float y) {
            position.inTR(x, y);
        }
    },
    BOTTOM_LEFT(1, -1) {
        @Override
        public void render(PositionAPI position, float x, float y) {
            position.inBL(x, y);
        }

    },
    BOTTOM_RIGHT(-1, -1) {
        @Override
        public void render(PositionAPI position, float x, float y) {
            position.inBR(x, y);
        }
    };

    @Getter
    private int horizontalDirection;
    @Getter
    private int verticalDirection;

    private Location(int horizontalDirection, int verticalDirection) {
        this.horizontalDirection = horizontalDirection;
        this.verticalDirection = verticalDirection;
    }

    public void render(PositionAPI position, float x, float y) {
    }
}
