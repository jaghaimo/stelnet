package stelnet.board.query.view.manage;

import com.fs.starfarer.api.loading.HullModSpecAPI;
import java.util.LinkedList;
import java.util.List;
import stelnet.CommonL10n;
import stelnet.board.query.provider.DmodProvider;
import stelnet.board.query.view.FilteringButton;
import stelnet.filter.FleetMemberHasDMod;
import stelnet.filter.FleetMemberHasDModCount;

public class ButtonUtils {

    public static FilteringButton[] getDMods(DmodProvider provider) {
        List<FilteringButton> dMods = new LinkedList<>();
        for (HullModSpecAPI dMod : provider.getDMods()) {
            dMods.add(
                new FilteringButton(dMod.getDisplayName(), new FleetMemberHasDMod(dMod.getId(), dMod.getDisplayName()))
            );
        }
        return dMods.toArray(new FilteringButton[] {});
    }

    public static FilteringButton[] getDModsCount() {
        List<FilteringButton> dModCount = new LinkedList<>();
        dModCount.add(new FilteringButton(CommonL10n.NONE, new FleetMemberHasDModCount(0)));
        for (int i = 1; i < 6; i++) {
            dModCount.add(new FilteringButton(String.valueOf(i), new FleetMemberHasDModCount(i)));
        }
        return dModCount.toArray(new FilteringButton[] {});
    }
}
