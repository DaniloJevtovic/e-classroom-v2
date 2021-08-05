	package com.lms.eclassroomv2.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@Autowired
	StudentClassService studentClassService;

	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	public Course getCourseById(Long courseId) {
		return courseRepository.findById(courseId).orElse(null);
	}

	// svi predmeti koje profesor predaje
	public List<Course> getAllCoursesForTeacher(Long teacherId) {
		return courseRepository.findByTeacherId(teacherId);
	}

	// svi predmeti za razred
	public List<Course> getAllCoursesForSchoolClass(Long scClassId) {
		return courseRepository.findBySchoolClassId(scClassId);
	}

	// predmeti za odjeljenje - moz i za ucenike - iz ucenika izvuces id odjeljenja
	// kojem pripada
	public List<Course> getCoursesForStudentClass(Long stClassId) {
		// pronadjem id razreda kojem odjeljenje pripada i onda za taj id razreda
		// pronadjem sve kurseve
		Long scClassId = studentClassService.getStudentClassById(stClassId).getSchoolClass().getId();
		return getAllCoursesForSchoolClass(scClassId);
	}

	public ResponseEntity<?> addCourse(CourseDto courseDto) {
		try {
			Course course = new Course();
			course.setName(courseDto.getName());
			course.setDescription(courseDto.getDescription());
			course.setDeleted(false);
			course.setTeacher(teacherService.getTeacherById(courseDto.getTeacherId()));
			// poslati mejl profesoru da je dodat na predmet
			course.setSchoolClass(schoolClassService.getSchoolClassById(courseDto.getSchoolClassId()));
			
			//courseRepository.save(course);
			//return new ResponseEntity<>("Predmet uspjesno kreiran!", HttpStatus.CREATED);
			
			//return ResponseEntity.ok(courseRepository.save(course));
			
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("body", courseRepository.save(course));
			res.put("message", "Predmet je uspjesno kreiran");
			
			return ResponseEntity.ok(res);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Nije moguce kreirati prdmet!", HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<?> updateCourse(Long courseId, CourseDto courseDto) {
		try {
			Course course = getCourseById(courseId);
			course.setName(courseDto.getName());
			course.setDescription(courseDto.getDescription());
			course.setTeacher(teacherService.getTeacherById(courseDto.getTeacherId()));
			course.setSchoolClass(schoolClassService.getSchoolClassById(courseDto.getSchoolClassId()));

			//courseRepository.save(course);
			//return new ResponseEntity<>("Predmet uspjesno azuriran", HttpStatus.OK);
			
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("body", courseRepository.save(course));
			res.put("message", "Predmet uspjesno azuriran");
			
			return ResponseEntity.ok(res);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Nije moguce azurirati predmet", HttpStatus.BAD_REQUEST);
		}

	}

	// logicko brisanje! - mozes da napravis kao arhiviranje kursa - pa samo da mu
	// mjenjas taj status
	public void deleteCourse(Long courseId) {
		Course course = getCourseById(courseId);
		course.setDeleted(true);
		courseRepository.save(course);
	}

}
