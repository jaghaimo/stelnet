package stelnet.market.dialog.handler;

import stelnet.market.dialog.DialogOption;
import stelnet.market.dialog.DialogPlugin;
import stelnet.market.filter.MutableFilterManager;
import stelnet.market.intel.provider.AdminIntelProvider;
import stelnet.market.intel.provider.IntelProvider;
import stelnet.market.intel.provider.OfficerIntelProvider;

public class Staff extends FilterAware {

    public Staff(DialogOption o, DialogOption p) {
        super(o, p);
    }

    @Override
    protected DialogOption run(DialogPlugin plugin) {
        IntelProvider provider;
        MutableFilterManager filterManager = plugin.getFilterManager();

        if (filterManager.getStaffType().equals(DialogOption.STAFF_TYPE_ADMIN)) {
            plugin.addText("Adding intel query for freelance administrators.");
            provider = new AdminIntelProvider();
        } else {
            String personality = filterManager.getStaffOfficer().name().substring(8).toLowerCase();
            plugin.addText("Adding intel query for " + personality + " officers.");
            provider = new OfficerIntelProvider(personality);
        }

        plugin.addNewQuery(provider);
        Menu.forceMenu(plugin);
        return DialogOption.INIT;
    }
}
