package com.lms.eclassroomv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	// svi rezultati za odredjeni kviz - da profesor moze da vidi rezultate i komentarise ih
	public List<StudentQuizResult> getAllResultsForQuiz(Long quizId) {
		return stQuizResultRepository.findByQuizId(quizId);
	}

	public StudentQuizResult saveResult(StudentQuizResultDto stQuizResDto) {
		StudentQuizResult studentQuizResult = new StudentQuizResult();
		studentQuizResult.setPoints(0);
		studentQuizResult.setStudent(studentService.getStudentById(stQuizResDto.getStudentId()));
		studentQuizResult.setQuiz(quizService.getQuizById(stQuizResDto.getQuizId()));

		return stQuizResultRepository.save(studentQuizResult);
	}
	
	//promjena poena kad ucenik bude oznacavao tacne/netacne odgovore
	//moze se iskoristi i ako profesor hoce da promjeni poene uceniku
	public StudentQuizResult updateResult(Long stQuizRes, int points) {
		StudentQuizResult stQuizResult = getStQuizResById(stQuizRes);
		stQuizResult.setPoints(points);
		return stQuizResultRepository.save(stQuizResult);
	}

}
