package com.joker.service.solo.impl;

import com.joker.entity.dto.Result;
import com.joker.entity.bo.HeadLine;
import com.joker.service.solo.HeadLineService;
import org.simpleframework.core.annotation.Service;

import java.util.List;

@Service
public class HeadLineServiceImpl implements HeadLineService {

    @Override
    public Result<Boolean> addHeadLine(HeadLine headLine) {
        return null;
    }

    @Override
    public Result<Boolean> removeHeadLine(int headLineId) {
        return null;
    }

    @Override
    public Result<Boolean> modifyHeadLine(HeadLine headLine) {
        return null;
    }

    @Override
    public Result<HeadLine> queryHeadLineById(int headLineId) {
        return null;
    }

    @Override
    public Result<List<HeadLine>> queryHeadLine(HeadLine headLineId, int pageIndex, int pageSize) {
        return null;
    }
}
