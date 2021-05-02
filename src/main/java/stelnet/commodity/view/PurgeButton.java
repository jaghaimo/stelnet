package stelnet.commodity.view;

import java.util.List;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.commodity.CommodityIntel;
import stelnet.helper.IntelHelper;
import stelnet.ui.Button;
import stelnet.ui.EventHandler;
import stelnet.ui.Location;
import stelnet.ui.Size;

public class PurgeButton extends Button {

    public PurgeButton() {
        super(new Size(200, 24), "Delete All", true, Misc.getNegativeHighlightColor());
        setLocation(Location.BOTTOM_RIGHT);
        setHandler(new EventHandler() {

            @Override
            public boolean hasPrompt() {
                return true;
            }

            @Override
            public void onConfirm(IntelUIAPI ui) {
                List<IntelInfoPlugin> intels = IntelHelper.getAll(CommodityIntel.class);
                for (int i = intels.size(); i > 0; i--) {
                    CommodityIntel commodityIntel = (CommodityIntel) intels.get(i - 1);
                    commodityIntel.delete();
                }
            }

            @Override
            public void onPrompt(TooltipMakerAPI tooltipMaker) {
                tooltipMaker.addPara("Are you sure you want to delete ALL intel for ALL commodities?",
                        Misc.getTextColor(), 0f);
            }
        });
    }
}
