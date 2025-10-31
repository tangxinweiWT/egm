package com.pyming.demo.rest;

import com.pyming.demo.domain.po.QCourse;
import com.pyming.demo.domain.po.QTeacher;
import com.pyming.demo.infrastructure.common.exception.InvalidParamException;
import com.pyming.demo.infrastructure.common.http.HttpResult;
import com.pyming.demo.infrastructure.common.page.PageInfo;
import com.pyming.demo.infrastructure.common.page.PageUtil;
import com.pyming.demo.infrastructure.vendors.jpaquery.DslHelper;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Api(tags = "courses")
@RequestMapping("/courses")
@RestController
@AllArgsConstructor
public class CourseController {
    private CourseRepository courseRepository;
    private TeacherRepository teacherRepository;
    private CourseFactory courseFactory;
    private DslHelper dslHelper;

    @PostMapping
    @ApiOperation("创建")
    @Transactional
    public HttpResult create(@Valid Course course) {
        teacherRepository.findById(course.getTeacherId()).orElseThrow(() -> {
            throw new InvalidParamException("教师不存在");
        });

        courseRepository.save(course);
        return HttpResult.ok();
    }

    @GetMapping("/{id}")
    public HttpResult get(@PathVariable("id") String id) {
        Course course = courseRepository.findById(id).orElseThrow();
        return HttpResult.ok(courseFactory.toVO(course));
    }

    @PutMapping("/{id}")
    public HttpResult update(@PathVariable("id") String id, @Valid Course course) {
        courseRepository.findById(id).orElseThrow();
        course.setId(id);
        courseRepository.save(course);
        return HttpResult.ok();
    }
    @GetMapping
    public HttpResult all(String keyword, PageInfo pageInfo) {
        Pageable pageable = pageInfo.of();
        QCourse qCourse = QCourse.course;
        QTeacher qTeacher = QTeacher.teacher;
        JPAQuery<Course> query = dslHelper.queryFactory().selectFrom(qCourse)
                .join(qTeacher).on(qCourse.teacherId.eq(qTeacher.id));

        if (StringUtils.isNotEmpty(keyword)) {
            String key = "%" + keyword + "%";
            BooleanExpression expression = qCourse.courseName.like(key).or(qTeacher.name.like(key));
            query.where(expression);
        }
        Page<Course> page = dslHelper.page(query, pageable);
        return HttpResult.ok(PageUtil.toPage(page.map(courseFactory::toVO)));
    }
}
