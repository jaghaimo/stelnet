package stelnet.board.query;

import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.experimental.Delegate;

public class ResultList {

    @Delegate
    @Getter
    private List<Result> resultList = new LinkedList<>();
}
