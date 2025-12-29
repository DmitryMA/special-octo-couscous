package com.dmakaroff.courseinfo.repository;

import com.dmakaroff.courseinfo.domian.Course;

import java.util.List;

public interface CourseRepository {
    void saveCourse(Course course);
    List<Course> getAllCourses();
}
