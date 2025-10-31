package com.pyming.demo.infrastructure.vendors.jpaquery;


import com.pyming.demo.infrastructure.vendors.jpaquery.annotation.PMQuery;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class QueryHelper {
    public static <R, T> Predicate toPredicate(Root<R> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb, T criteria) {
        // 查询条件为空
        List<Predicate> list = new ArrayList<>();
        if (criteria == null) {
            return cb.and(list.toArray(new Predicate[0]));
        }

        // 为每个字段构建查询
        List<Field> fields = getAllField(criteria.getClass());

        for (Field field : fields) {
            boolean accessible = field.canAccess(criteria);
            // 设置对象的访问权限，保证对private的属性的访
            field.setAccessible(true);
            PMQuery pmQuery = field.getAnnotation(PMQuery.class);
            // 没有Query注解，就跳过
            if (pmQuery == null) {
                continue;
            }
            // 获取注解相关信息
            String propName = pmQuery.propName();
            // 要查询的字段名
            String attrName = StringUtils.isEmpty(propName) ? field.getName() : propName;
            // 模糊查询时要用到的值
            String[] blurry = pmQuery.blurry();
            // 字段值的类型
            Class<?> fieldType = field.getType();
            // 查询类型
            PMQuery.Type type = pmQuery.type();
            PMQuery.Type[] nullOpt = {PMQuery.Type.NOT_NULL, PMQuery.Type.IS_NULL};
            try {
                // 获取字段值
                Object fieldValue = field.get(criteria);
                if (ObjectUtils.isEmpty(fieldValue) && !ArrayUtils.contains(nullOpt, type)) {
                    continue;
                }
                // 如果时模糊查询,目前单表
                if (!ArrayUtils.isEmpty(blurry)) {
                    List<Predicate> blurryList = new ArrayList<>();
                    for (String s : blurry) {
                        if (StringUtils.isEmpty(s)) {
                            continue;
                        }
                        blurryList.add(cb.like(root.get(s).as(String.class), "%" + fieldValue.toString() + "%"));
                    }
                    list.add(cb.or(blurryList.toArray(new Predicate[blurryList.size()])));
                    continue;
                }

                // 时间查询
                PMQuery.Type[] timeOpt = {PMQuery.Type.TIME_GE, PMQuery.Type.TIME_LE};
                if (ArrayUtils.contains(timeOpt, type)) {
                    // 时间的字段名字
                    String timeAttr = pmQuery.timeAttr();
                    Expression<? extends Comparable> expression = getExpression(timeAttr, root).as((Class<? extends Comparable>) fieldType);
                    Comparable compareVal = (Comparable) fieldValue;
                    switch (type) {
                        case TIME_GE:
                            list.add(cb.greaterThanOrEqualTo(expression, compareVal));
                            break;
                        case TIME_LE:
                            list.add(cb.lessThanOrEqualTo(expression, compareVal));
                            break;
                    }
                    continue;
                }
                // 其余字段，正常查询
                PMQuery.Type[] compareOpt = {PMQuery.Type.EQ, PMQuery.Type.NE, PMQuery.Type.LT, PMQuery.Type.LE, PMQuery.Type.GE, PMQuery.Type.GT};
                PMQuery.Type[] collectOpt = {PMQuery.Type.IN, PMQuery.Type.NOT_IN};
                // 比较操作
                if (ArrayUtils.contains(compareOpt, type)) {
                    Expression<? extends Comparable> expression = getExpression(attrName, root).as((Class<? extends Comparable>) fieldType);
                    Comparable compareVal = (Comparable) fieldValue;
                    switch (type) {
                        case LT:
                            list.add(cb.lessThan(expression, compareVal));
                            break;
                        case LE:
                            list.add(cb.lessThanOrEqualTo(expression, compareVal));
                            break;
                        case EQ:
                            list.add(cb.equal(expression, compareVal));
                            break;
                        case NE:
                            list.add(cb.notEqual(expression, compareVal));
                            break;
                        case GT:
                            list.add(cb.greaterThan(expression, compareVal));
                            break;
                        case GE:
                            list.add(cb.greaterThanOrEqualTo(expression, compareVal));
                            break;
                    }
                }
                // 集合操作
                if (ArrayUtils.contains(collectOpt, type)) {
                    Collection<Object> compareVal = (Collection<Object>) fieldValue;
                    switch (type) {
                        case IN:
                            if (!CollectionUtils.isEmpty(compareVal)) {
                                list.add(getExpression(attrName, root).in(compareVal));
                            }
                            break;
                        case NOT_IN:
                            if (!CollectionUtils.isEmpty(compareVal)) {
                                list.add(getExpression(attrName, root).in(compareVal).not());
                            }

                    }
                }
                // 其他情况
                switch (type) {
                    case BETWEEN:
                        List<Object> between = new ArrayList<>((List<Object>) fieldValue);
                        list.add(cb.between(getExpression(attrName, root).as((Class<? extends Comparable>) between.get(0).getClass()),
                                (Comparable) between.get(0), (Comparable) between.get(1)));
                        break;
                    case LIKE:
                        list.add(cb.like(getExpression(attrName, root)
                                .as(String.class), "%" + fieldValue.toString() + "%"));
                        break;
                    case IS_NULL:
                        list.add(cb.isNull(getExpression(attrName, root)));
                        break;
                    case NOT_NULL:
                        list.add(cb.isNotNull(getExpression(attrName, root)));
                        break;
                }
                field.setAccessible(accessible);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("构建查询条件失败");
            }
        }
        // 拼接条件
        int size = list.size();
        return cb.and(list.toArray(new Predicate[size]));
    }

    private static <T, R> Expression<T> getExpression(String attributeName, Root<R> root) {
        return root.get(attributeName);
    }

    /**
     * 获取类的所有属性
     *
     * @param clazz
     * @return
     */
    private static List<Field> getAllField(Class<?> clazz) {
        List<Field> allFields = new ArrayList<>();
        if (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            allFields.addAll(Arrays.asList(fields));
            // 获取父类信息
            List<Field> superFields = getAllField(clazz.getSuperclass());
            allFields.addAll(superFields);
        }
        return allFields;
    }
}
