package stelnet.market.handler.dialog;

import stelnet.market.DialogOption;
import stelnet.market.DialogPlugin;
import stelnet.market.IntelProvider;
import stelnet.market.filter.MutableFilterManager;
import stelnet.market.provider.AdminIntelProvider;
import stelnet.market.provider.OfficerIntelProvider;

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
