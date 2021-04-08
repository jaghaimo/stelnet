package stelnet.market.handler;

import java.util.List;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stelnet.market.IntelQuery;

public interface ButtonHandler {

    public void handle(List<IntelQuery> queries, IntelUIAPI ui);
}
