package stelnet.storage;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;

import lombok.Getter;
import lombok.Setter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.L10n;
import stelnet.helper.GlobalSettingsHelper;
import stelnet.helper.IntelHelper;
import stelnet.helper.StorageHelper;
import stelnet.helper.Tagger;
import stelnet.storage.view.StorageTabViewFactory;
import stelnet.ui.Renderable;
import stelnet.ui.property.Size;

@Getter
@Setter
public class StorageBoard extends BaseBoard {

    private final FilterManager filterManager = new FilterManager();
    private final ButtonManager buttonManager = new ButtonManager(filterManager);
    private StorageTab activeTab = StorageTab.ITEMS;
    private StorageView activeView = StorageView.UNIFIED;

    public static StorageBoard getInstance() {
        IntelInfoPlugin intel = IntelHelper.getFirstIntel(StorageBoard.class);
        if (intel == null) {
            StorageBoard board = new StorageBoard();
            IntelHelper.addIntel(board, true);
        }
        return (StorageBoard) intel;
    }

    @Override
    public String getIcon() {
        return GlobalSettingsHelper.getSpriteName("storage");
    }

    @Override
    protected List<Renderable> getRenderables(Size size) {
        return Arrays.<Renderable>asList(
                new StorageTabViewFactory(buttonManager, filterManager, activeTab, activeView).createContainer(size),
                activeView.getNextButton()
        );
    }

    @Override
    protected BoardInfo getBoardInfo() {
        return new BoardInfo(L10n.get("storageBoardTitle"), getDescription());
    }

    @Override
    protected String getTag() {
        return Tagger.STORAGE;
    }

    private String getDescription() {
        int itemCount = StorageHelper.getAllItemCount();
        int shipCount = StorageHelper.getAllShipCount();
        if (itemCount == 0 && shipCount == 0) {
            return L10n.get("storageBoardNoContent");
        }
        return L10n.get("storageBoardContent", itemCount, shipCount);
    }
}
