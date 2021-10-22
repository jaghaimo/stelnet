package stelnet.filter.submarket;

import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.impl.campaign.submarkets.BaseSubmarketPlugin;
import lombok.extern.log4j.Log4j;

@Log4j
public class NeedsRefresh implements SubmarketFilter {

    @Override
    public boolean accept(SubmarketAPI object) {
        try {
            return (
                (BaseSubmarketPlugin) object.getPlugin()
            ).okToUpdateShipsAndWeapons();
        } catch (Exception exception) {
            log.warn(
                String.format(
                    "Failed to cast submarket %s of %s",
                    object.getName(),
                    object.getMarket().getName()
                )
            );
            return false;
        }
    }
}
