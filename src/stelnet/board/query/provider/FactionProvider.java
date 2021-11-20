package stelnet.board.query.provider;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.loading.FighterWingSpecAPI;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import com.fs.starfarer.api.loading.WeaponSpecAPI;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import stelnet.filter.FactionIsShownFilter;
import stelnet.util.CollectionUtils;

public class FactionProvider {

    private transient List<FactionAPI> factions;

    public List<FighterWingSpecAPI> getAllFighters() {
        List<FighterWingSpecAPI> fighterWingSpecs = new LinkedList<>();
        for (String fighterId : getAllFighterIds()) {
            fighterWingSpecs.add(Global.getSettings().getFighterWingSpec(fighterId));
        }
        return fighterWingSpecs;
    }

    public List<HullModSpecAPI> getAllHullMods() {
        List<HullModSpecAPI> hullMods = new LinkedList<>();
        for (String hullModId : getAllHullModIds()) {
            hullMods.add(Global.getSettings().getHullModSpec(hullModId));
        }
        return hullMods;
    }

    public List<ShipHullSpecAPI> getAllShips() {
        List<ShipHullSpecAPI> ships = new LinkedList<>();
        for (String shipId : getAllShipIds()) {
            ships.add(Global.getSettings().getHullSpec(shipId));
        }
        return ships;
    }

    public List<WeaponSpecAPI> getAllWeapons() {
        List<WeaponSpecAPI> weapons = new LinkedList<>();
        for (String weaponId : getAllWeaponIds()) {
            weapons.add(Global.getSettings().getWeaponSpec(weaponId));
        }
        return weapons;
    }

    private Set<String> getAllFighterIds() {
        Set<String> fighters = new HashSet<>();
        for (FactionAPI faction : getFactionIds()) {
            fighters.addAll(faction.getKnownFighters());
        }
        return fighters;
    }

    private Set<String> getAllHullModIds() {
        Set<String> hullMods = new HashSet<>();
        for (FactionAPI faction : getFactionIds()) {
            hullMods.addAll(faction.getKnownHullMods());
        }
        return hullMods;
    }

    private Set<String> getAllShipIds() {
        Set<String> ships = new HashSet<>();
        for (FactionAPI faction : getFactionIds()) {
            ships.addAll(faction.getKnownShips());
        }
        return ships;
    }

    private Set<String> getAllWeaponIds() {
        Set<String> weapons = new HashSet<>();
        for (FactionAPI faction : getFactionIds()) {
            weapons.addAll(faction.getKnownWeapons());
        }
        return weapons;
    }

    private List<FactionAPI> getFactionIds() {
        if (factions == null) {
            factions = Global.getSector().getAllFactions();
            CollectionUtils.reduce(factions, new FactionIsShownFilter());
        }
        return factions;
    }
}
