package stelnet.l10n;

public class IntelBundle extends Bundle {

    public IntelBundle() {
        super(IntelBundle.class.getName());
    }

    public String faction() {
        return format("faction");
    }

    public String location() {
        return format("location");
    }
}
