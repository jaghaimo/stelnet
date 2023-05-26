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
import stelnet.filter.AnyHasTag;
import stelnet.filter.FactionIsShown;
import stelnet.filter.HullModIsHidden;
import stelnet.filter.LogicalNot;
import stelnet.filter.ShipHullIsInCodex;
import stelnet.settings.BooleanSettings;
import stelnet.util.CollectionUtils;

public class FactionProvider {

    private static transient List<FactionAPI> factions;

    public static void resetCache() {
        factions = null;
    }

    public List<FighterWingSpecAPI> getAllFighters() {
        if (BooleanSettings.MARKET_CODEX_ITEMS.get()) {
            List<FighterWingSpecAPI> fighterWingSpecs = Global.getSettings().getAllFighterWingSpecs();
            CollectionUtils.reduce(fighterWingSpecs, new LogicalNot(new AnyHasTag("restricted")));
            return fighterWingSpecs;
        }
        List<FighterWingSpecAPI> fighterWingSpecs = new LinkedList<>();
        for (String fighterId : getAllFighterIds()) {
            fighterWingSpecs.add(Global.getSettings().getFighterWingSpec(fighterId));
        }
        return fighterWingSpecs;
    }

    public List<HullModSpecAPI> getAllHullMods() {
        if (BooleanSettings.MARKET_CODEX_ITEMS.get()) {
            List<HullModSpecAPI> hullMods = Global.getSettings().getAllHullModSpecs();
            CollectionUtils.reduce(hullMods, new LogicalNot(new HullModIsHidden()));
            return hullMods;
        }
        List<HullModSpecAPI> hullMods = new LinkedList<>();
        for (String hullModId : getAllHullModIds()) {
            hullMods.add(Global.getSettings().getHullModSpec(hullModId));
        }
        return hullMods;
    }

    public List<ShipHullSpecAPI> getAllShips() {
        if (BooleanSettings.MARKET_CODEX_SHIPS.get()) {
            List<ShipHullSpecAPI> ships = Global.getSettings().getAllShipHullSpecs();
            CollectionUtils.reduce(ships, new ShipHullIsInCodex());
            return ships;
        }
        List<ShipHullSpecAPI> ships = new LinkedList<>();
        for (String shipId : getAllShipIds()) {
            ships.add(Global.getSettings().getHullSpec(shipId));
        }
        return ships;
    }

    public List<WeaponSpecAPI> getAllWeapons() {
        if (BooleanSettings.MARKET_CODEX_ITEMS.get()) {
            List<WeaponSpecAPI> weapons = Global.getSettings().getAllWeaponSpecs();
            CollectionUtils.reduce(weapons, new LogicalNot(new AnyHasTag("restricted")));
            return weapons;
        }
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
            ships.addAll(faction.getAlwaysKnownShips());
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
            CollectionUtils.reduce(factions, new FactionIsShown());
        }
        return factions;
    }
}
