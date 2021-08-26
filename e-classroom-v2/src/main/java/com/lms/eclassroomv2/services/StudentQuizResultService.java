package com.lms.eclassroomv2.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.model.StudentQuizResult;
import com.lms.eclassroomv2.model.dto.StudentQuizResultDto;
import com.lms.eclassroomv2.repository.StudentQuizResultRepository;

@Service
public class StudentQuizResultService {

	@Autowired
	StudentQuizResultRepository stQuizResultRepository;

	@Autowired
	StudentService studentService;

	@Autowired
	QuizService quizService;

	public List<StudentQuizResult> getAllStQuizResults() {
		return stQuizResultRepository.findAll();
	}

	public StudentQuizResult getStQuizResById(Long stQuizResId) {
		return stQuizResultRepository.findById(stQuizResId).orElse(null);
	}

	// svi rezultati za ucenika za sve kvizove
	public List<StudentQuizResult> getAllResultsForStudent(Long studentId) {
		return stQuizResultRepository.findByStudentId(studentId);
	}

	// rezultat za odredjenog ucenika i odredjeni kviz
	public StudentQuizResult getResultForStudentAndQuiz(Long studentId, Long quizId) {
		return stQuizResultRepository.findByStudentIdAndQuizId(studentId, quizId);
	}

	// svi rezultati za odredjeni kviz - da profesor moze da vidi rezultate i
	// komentarise ih
	public List<StudentQuizResult> getAllResultsForQuiz(Long quizId) {
		return stQuizResultRepository.findByQuizId(quizId);
	}

	public ResponseEntity<?> saveResult(StudentQuizResultDto stQuizResDto) {
		try {
			StudentQuizResult studentQuizResult = new StudentQuizResult();
			studentQuizResult.setPoints(0);
			studentQuizResult.setStudent(studentService.getStudentById(stQuizResDto.getStudentId()));
			studentQuizResult.setQuiz(quizService.getQuizById(stQuizResDto.getQuizId()));
			studentQuizResult.setSolveDuration(stQuizResDto.getSolveDuration());
			studentQuizResult.setDate(new Timestamp(new Date().getTime()));

			Map<String, Object> res = new HashMap<String, Object>();
			res.put("body", stQuizResultRepository.save(studentQuizResult));
			res.put("message", "Rezultat sacuvan");

			return ResponseEntity.ok(res);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Nije moguce sacuvati rezultat", HttpStatus.BAD_REQUEST);
		}

	}

	// promjena poena kad ucenik bude oznacavao tacne/netacne odgovore
	// moze se iskoristi i ako profesor hoce da promjeni poene uceniku
	public StudentQuizResult updateResult(Long stQuizRes, int points) {
		StudentQuizResult stQuizResult = getStQuizResById(stQuizRes);
		stQuizResult.setPoints(points);
		return stQuizResultRepository.save(stQuizResult);
	}
	
	//za poziv rest-a
	public ResponseEntity<?> updateResPointsRest(Long resId, int points) {
		try {
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("body", updateResult(resId, points));
			res.put("message", "Rezultat izmjenjen");

			return ResponseEntity.ok(res);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Nije moguce izmjeniti rezultat", HttpStatus.BAD_REQUEST);
		}
		
		
	}

}
