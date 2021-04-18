package com.lms.eclassroomv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.model.Course;
import com.lms.eclassroomv2.model.dto.CourseDto;
import com.lms.eclassroomv2.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	TeacherService teacherService;

	@Autowired
	SchoolClassService schoolClassService;

	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	public Course getCourseById(Long courseId) {
		return courseRepository.findById(courseId).orElse(null);
	}
	
	//svi predmeti koje profesor predaje
	public List<Course> getAllCoursesForTeacher(Long teacherId) {
		return courseRepository.findByTeacherId(teacherId);
	}
	
	//svi predmeti za razred
	public List<Course> getAllCoursesForSchoolClass(Long scClassId) {
		return courseRepository.findBySchoolClassId(scClassId);
	}

	public Course addCourse(CourseDto courseDto) {
		Course course = new Course();
		course.setName(courseDto.getName());
		course.setDescription(courseDto.getDescription());
		course.setDeleted(false);
		course.setTeacher(teacherService.getTeacherById(courseDto.getTeacherId()));
		course.setSchoolClass(schoolClassService.getSchoolClassById(courseDto.getSchoolClassId()));

		return courseRepository.save(course);
	}

	public Course updateCourse(Long courseId, CourseDto courseDto) {
		Course course = getCourseById(courseId);
		course.setName(courseDto.getName());
		course.setDescription(courseDto.getDescription());
		course.setTeacher(teacherService.getTeacherById(courseDto.getTeacherId()));
		course.setSchoolClass(schoolClassService.getSchoolClassById(courseDto.getSchoolClassId()));
		
		return courseRepository.save(course);
	}

	// logicko brisanje! - mozes da napravis kao arhiviranje kursa - pa samo da mu
	// mjenjas taj status
	public void deleteCourse(Long courseId) {
		Course course = getCourseById(courseId);
		course.setDeleted(true);
		courseRepository.save(course);
	}
	
	
}
