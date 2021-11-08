package stelnet.filter;

import com.fs.starfarer.api.combat.FighterWingAPI;
import com.fs.starfarer.api.loading.WingRole;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WingIsRole extends Filter {

    private final WingRole wingRole;

    protected boolean acceptWeapon(FighterWingAPI wing) {
        return wing.getRole().equals(wingRole);
    }
}
