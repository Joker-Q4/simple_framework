package com.joker.service.solo;

import com.joker.dto.Result;
import com.joker.entity.HeadLine;

import java.util.List;

public interface HeadLineService {

    Result<Boolean> addHeadLine(HeadLine headLine);
    Result<Boolean> removeHeadLine(int headLineId);
    Result<Boolean> modifyHeadLine(HeadLine headLine);
    Result<HeadLine> queryHeadLineById(int headLineId);
    Result<List<HeadLine>> queryHeadLine(HeadLine headLineId, int pageIndex, int pageSize);
}
