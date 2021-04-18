package stelnet.commodity.view;

import java.util.List;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.commodity.CommodityIntel;
import stelnet.helper.IntelHelper;
import stelnet.ui.Button;
import stelnet.ui.Callable;
import stelnet.ui.Size;

public class DeleteButton extends Button {

    public DeleteButton(final String commodityId) {
        super(new Size(200, 24), "Delete this", true, Misc.getButtonTextColor());
        setCallback(new Callable() {

            @Override
            public void confirm(IntelUIAPI ui) {
                boolean needRefresh = false;
                List<IntelInfoPlugin> intels = IntelHelper.getAll(CommodityIntel.class);
                for (int i = intels.size(); i > 0; i--) {
                    // for (IntelInfoPlugin intel : intels) {
                    CommodityIntel commodityIntel = (CommodityIntel) intels.get(i - 1);
                    if (commodityIntel.getCommodityId().equals(commodityId)) {
                        commodityIntel.delete();
                        needRefresh = true;
                    }
                }
                if (needRefresh) {
                    ui.recreateIntelUI();
                }
            }

            @Override
            public void cancel() {
            }

            @Override
            public void prompt(TooltipMakerAPI tooltipMaker) {
                tooltipMaker.addPara("Are you sure you want to delete all intel for this commodity?",
                        Misc.getTextColor(), 0f);
            }
        });
    }
}
