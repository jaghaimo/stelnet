package stelnet.storage.button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.loading.WingRole;

public class ButtonManager {

    private Button displayModeButton;
    private List<Button> cargoTypeButtons;
    private List<Button> cargoWeaponButtons;
    private List<Button> cargoFighterWingsButtons;
    private List<Button> shipSizeButtons;
    private List<Button> shipTypeButtons;

    public ButtonManager() {
        cargoTypeButtons = Arrays.asList(new Commodities(), new Weapons(), new FighterWings(), new Others());
        cargoWeaponButtons = Arrays.<Button>asList(new WeaponMountSize(WeaponSize.SMALL),
                new WeaponMountSize(WeaponSize.MEDIUM), new WeaponMountSize(WeaponSize.LARGE));
        cargoFighterWingsButtons = Arrays.<Button>asList(new FighterWingRole(WingRole.BOMBER),
                new FighterWingRole(WingRole.FIGHTER), new FighterWingRole(WingRole.INTERCEPTOR));
        shipSizeButtons = Arrays.asList(new Frigates(), new Destroyers(), new Cruisers(), new Capitals());
        shipTypeButtons = Arrays.asList(new Warships(), new Carriers(), new Civilians());
    }

    public Button getDisplayModeButton() {
        if (displayModeButton == null) {
            displayModeButton = new DisplayGroup(true);
        }
        return displayModeButton;
    }

    public List<Button> getAllCargoButtons() {
        List<Button> cargoButtons = new ArrayList<>();
        cargoButtons.addAll(cargoTypeButtons);
        cargoButtons.addAll(cargoWeaponButtons);
        cargoButtons.addAll(cargoFighterWingsButtons);
        return cargoButtons;
    }

    public List<Button> getAllShipButtons() {
        List<Button> shipButtons = new ArrayList<>();
        shipButtons.addAll(shipSizeButtons);
        shipButtons.addAll(shipTypeButtons);
        return shipButtons;
    }

    public List<Button> getCargoTypeButtons() {
        return cargoTypeButtons;
    }

    public List<Button> getCargoWeaponButtons() {
        return cargoWeaponButtons;
    }

    public List<Button> getCargoFighterWingsButtons() {
        return cargoFighterWingsButtons;
    }

    public List<Button> getShipSizeButtons() {
        return shipSizeButtons;
    }

    public List<Button> getShipTypeButtons() {
        return shipTypeButtons;
    }

    public void setEnabledWeaponButtons(boolean isEnabled) {
        for (Button button : cargoWeaponButtons) {
            button.setEnabled(isEnabled);
        }
    }

    public void setEnabledFighterWingButtons(boolean isEnabled) {
        for (Button button : cargoFighterWingsButtons) {
            button.setEnabled(isEnabled);
        }
    }
}
