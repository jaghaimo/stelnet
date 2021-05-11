package stelnet.filter.commodityspec;

import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HasNotTag implements CommoditySpecFilter {

    private String tag;

    @Override
    public boolean accept(CommoditySpecAPI object) {
        return !object.hasTag(tag);
    }
}
