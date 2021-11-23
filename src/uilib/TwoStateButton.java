package uilib;

public interface TwoStateButton extends Renderable {
    public boolean isStateOn();

    public void setStateOn(boolean isStateOn);

    public void toggle();
}
