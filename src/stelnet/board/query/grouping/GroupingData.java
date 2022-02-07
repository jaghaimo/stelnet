package stelnet.board.query.grouping;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import lombok.Data;
import uilib.RenderableIntelInfo;

@Data
public class GroupingData {

    private final RenderableIntelInfo info;
    private final FactionAPI faction;
    private final String key;
    private final SectorEntityToken token;
}
