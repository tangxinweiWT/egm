package com.pyming.demo.service.Impl;

import com.pyming.demo.domain.dto.BaseJson;
import com.pyming.demo.domain.dto.DishesDTO;
import com.pyming.demo.domain.mapper.BaseMapper;
import com.pyming.demo.domain.mapper.DishesMapper;
import com.pyming.demo.domain.po.Dishes;
import com.pyming.demo.domain.po.DishesRel;
import com.pyming.demo.domain.po.Ingredient;
import com.pyming.demo.domain.po.Seasoning;
import com.pyming.demo.infrastructure.common.page.PageResult;
import com.pyming.demo.infrastructure.utils.JsonUtils;
import com.pyming.demo.repository.DishesRelRepository;
import com.pyming.demo.repository.DishesRepository;
import com.pyming.demo.repository.IngredientRepository;
import com.pyming.demo.repository.SeasoningRepository;
import com.pyming.demo.service.DishesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class DishesServiceImpl implements DishesService {
    private final BaseMapper baseMapper;
    private final DishesMapper dishesMapper;
    private final DishesRepository dishesRepository;
    private final DishesRelRepository dishesRelRepository;
    private final IngredientRepository ingredientRepository;
    private final SeasoningRepository seasoningRepository;
    
    @Transactional
    @Override
    public void created(DishesDTO dto) {
        Dishes po = dishesMapper.toPO(dto);
        Dishes dishes = dishesRepository.saveAndFlush(po);
        DishesRel dishesRel = new DishesRel();
        dishesRel.setDishesId(dishes.getId());
        String ingredientJsonInfo = JsonUtils.toJson(baseMapper.toBaseJsonByIngredient(ingredientRepository.findBYIdIN(dto.getIngredientIds())));
        String seasoningJsonInfo = JsonUtils.toJson(baseMapper.toBaseJsonBySeasoning(seasoningRepository.findBYIdIN(dto.getIngredientIds())));
        dishesRel.setIngredientJSONInfo(ingredientJsonInfo);
        dishesRel.setSeasoningJSONInfo(seasoningJsonInfo);
        dishesRelRepository.saveAndFlush(dishesRel);
    }

    @Transactional
    @Override
    public void update(String id, DishesDTO dishes) {
    }

    @Transactional
    @Override
    public void delete(String id) {

    }

    @Override
    public DishesDTO get(String id) {
        return null;
    }

    @Override
    public PageResult queryAll(Pageable pageable) {
        return null;
    }
}
