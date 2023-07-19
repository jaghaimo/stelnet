package stelnet.board.query.grouping;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uilib.RenderableIntelInfo;

@Getter
@RequiredArgsConstructor
public class GroupingData {

    private final RenderableIntelInfo info;
    private final FactionAPI faction;
    private final String key;
    private final SectorEntityToken token;
}
