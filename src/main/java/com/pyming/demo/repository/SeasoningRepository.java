package com.pyming.demo.repository;

import com.pyming.demo.domain.po.Seasoning;
import com.pyming.demo.infrastructure.common.base.BaseRepository;

import java.util.List;

public interface SeasoningRepository extends BaseRepository<Seasoning> {
    List<Seasoning> findBYIdIN(List<String> ids);
    
}