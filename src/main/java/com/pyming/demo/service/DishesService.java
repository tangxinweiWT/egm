package com.pyming.demo.service;

import com.pyming.demo.domain.dto.DishesDTO;
import com.pyming.demo.infrastructure.common.page.PageResult;
import org.springframework.data.domain.Pageable;

public interface DishesService {
    void created (DishesDTO dishes);
    
    void update (String id, DishesDTO dishes);
    
    void delete (String id);
    
    DishesDTO get (String id);

    PageResult queryAll(Pageable pageable);
}
