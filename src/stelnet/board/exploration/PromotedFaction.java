package stelnet.board.exploration;

import com.fs.starfarer.api.campaign.FactionAPI;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

@RequiredArgsConstructor
public class PromotedFaction implements IdAware {

    @Delegate
    private final FactionAPI faction;
}
