package stelnet.filter;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketSpecAPI;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class AnyHasId extends MarketFilter {

    private final String id;

    @Override
    public boolean accept(Object object) {
        if (object instanceof SkillSpecAPI) {
            return acceptSkillSpec((SkillSpecAPI) object);
        }
        if (object instanceof SubmarketAPI) {
            return acceptSubmarket((SubmarketAPI) object);
        }
        if (object instanceof SubmarketSpecAPI) {
            return acceptSubmarketSpec((SubmarketSpecAPI) object);
        }
        return super.accept(object);
    }

    @Override
    protected boolean acceptMarket(MarketAPI market) {
        return id.equalsIgnoreCase(market.getId());
    }

    protected boolean acceptSkillSpec(SkillSpecAPI skill) {
        return id.equalsIgnoreCase(skill.getId());
    }

    protected boolean acceptSubmarket(SubmarketAPI submarket) {
        return id.equalsIgnoreCase(submarket.getSpecId());
    }

    protected boolean acceptSubmarketSpec(SubmarketSpecAPI submarketSpec) {
        return id.equalsIgnoreCase(submarketSpec.getId());
    }
}
