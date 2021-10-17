package stelnet.filter.shiphullspec;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;

public class ShowInCodex implements ShipHullSpecFilter {

    @Override
    public boolean accept(ShipHullSpecAPI object) {
        return !object.hasTag("HIDE_IN_CODEX");
    }
}
