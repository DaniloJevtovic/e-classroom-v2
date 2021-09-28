package com.lms.eclassroomv2.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.model.Quiz;
import com.lms.eclassroomv2.model.QuizStatus;
import com.lms.eclassroomv2.model.StudentQuizResult;
import com.lms.eclassroomv2.model.dto.QuizDto;
import com.lms.eclassroomv2.repository.QuizRepository;

@Service
public class QuizService {

	@Autowired
	QuizRepository quizRepository;

	@Autowired
	CourseService courseService;

	@Autowired
	StudentQuizResultService stQuizResultService;

	public List<Quiz> getAllQuizzes() {
		return quizRepository.findAll();
	}

	public Quiz getQuizById(Long quizId) {
		return quizRepository.findById(quizId).orElse(null);
	}

	public List<Quiz> getQuizzesForCourse(Long courseId) {
		return quizRepository.findByCourseId(courseId);
	}

	// svi kvizovi za predmet koje ucenik NIJE rjesavao
	// mozes da dodas i samo aktivne kvizove po potrebi
	public List<Quiz> getCoursQuizzesStudNotSolve(Long courseId, Long studentId) {

		// lista svih kvizova za predmet
		List<Quiz> courseQuizzes = getQuizzesForCourse(courseId);

		// lista svih rezultata za ucenika za odredjeni predmet
		List<StudentQuizResult> stResForCourse = stQuizResultService.getAllResultsForCourseAndStudent(courseId,
				studentId);

		// nova lista kvizova za rezultate
		List<Quiz> listQuizzesForRes = new ArrayList<Quiz>();

		//prolazim kroz listu rezultata i izvlacim kvizove iz nje
		for (StudentQuizResult studentQuizResult : stResForCourse) {
			listQuizzesForRes.add(studentQuizResult.getQuiz());
		}

		// prazna list u koju dodajem kvizove koje ucenik NIJE rjesavao
		List<Quiz> notSolvedQuizzes = new ArrayList<>(courseQuizzes);

		notSolvedQuizzes.removeAll(listQuizzesForRes);

		return notSolvedQuizzes;

	}

	public ResponseEntity<?> newQuiz(QuizDto quizDto) {
		try {
			Quiz quiz = new Quiz();
			quiz.setName(quizDto.getName());
			quiz.setInstructions(quizDto.getInstructions());
			quiz.setDuration(quizDto.getDuration());
			quiz.setTotalPoints(0);
			quiz.setCourse(courseService.getCourseById(quizDto.getCourseId()));
			quiz.setQuizStatus(QuizStatus.INACTIVE); // da ne bi odma ucenici mogli da ga rjesavaju kad se kreira kviz

			// quizRepository.save(quiz);
			// return new ResponseEntity<>("Kviz uspjesno kreiran", HttpStatus.CREATED);

			Map<String, Object> res = new HashMap<String, Object>();
			res.put("body", quizRepository.save(quiz));
			res.put("message", "Kviz uspjesno kreiran");

			return ResponseEntity.ok(res);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Greska u kreiranju kviza", HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<?> updateQuiz(Long quizId, QuizDto quizDto) {
		try {
			Quiz quiz = getQuizById(quizId);
			quiz.setName(quizDto.getName());
			quiz.setInstructions(quizDto.getInstructions());
			quiz.setDuration(quizDto.getDuration());
			quiz.setQuizStatus(QuizStatus.valueOf(quizDto.getQuizStatus()));

			Map<String, Object> res = new HashMap<String, Object>();
			res.put("body", quizRepository.save(quiz));
			res.put("message", "Kviz uspjesno izmjenjen");

			return ResponseEntity.ok(res);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Greska u kreiranju kviza", HttpStatus.BAD_REQUEST);
		}

	}

	// ideja - svaki put kad se azurira broj poena u pitanju prvo se oduzmu stari
	// poeni (od pitanja)
	// a dodaju novi poeni pitanja
	public Quiz updatePointsToQuizAndQuestNum(Long quizId, int oldPoints, int newPoints, int questionsNumb) {
		Quiz quiz = getQuizById(quizId);
		quiz.setTotalPoints(quiz.getTotalPoints() - oldPoints + newPoints);
		quiz.setNumbOfQuestions(quiz.getNumbOfQuestions() + questionsNumb);
		return quizRepository.save(quiz);
	}

	// logicko brisanje!
	public void deleteQuiz(Long quizId) {
		Quiz quiz = getQuizById(quizId);
		quiz.setQuizStatus(QuizStatus.DELETED);
		quizRepository.save(quiz);
	}
}
