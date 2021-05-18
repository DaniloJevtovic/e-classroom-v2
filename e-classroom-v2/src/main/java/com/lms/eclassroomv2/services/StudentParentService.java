package com.lms.eclassroomv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.model.Authority;
import com.lms.eclassroomv2.model.StudentParent;
import com.lms.eclassroomv2.repository.StudentParentRepository;

@Service
public class StudentParentService {

	@Autowired
	StudentParentRepository stParentRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	AuthorityService authorityService;

	public List<StudentParent> getAllParents() {
		return stParentRepository.findAll();
	}

	public StudentParent getStParentById(Long stParentId) {
		return stParentRepository.findById(stParentId).orElse(null);
	}

	public StudentParent getParentByEmail(String parentEmail) {
		return stParentRepository.findByEmail(parentEmail);
	}

	/**
	 * neki od roditelja moze da ima 2 ili vise djeteta u skoli - da se ne bi cuvao
	 * svaki put roditelj kad se upisuje neko od njegove djece vrsi se provjera da
	 * li postoji taj roditelj, ako postoji samo ce da vrati vec postojecg
	 */
	public StudentParent saveStParent(StudentParent parent) {
		StudentParent checkParent = getParentByEmail(parent.getEmail());

		if (checkParent != null) {
			return checkParent;
		}

		StudentParent studentParent = new StudentParent();
		studentParent.setFirstName(parent.getFirstName());
		studentParent.setLastName(parent.getLastName());
		studentParent.setEmail(parent.getEmail());
		studentParent.setUsername(parent.getUsername());
		studentParent.setPassword(passwordEncoder.encode("123"));
		studentParent.setEnabled(true);

		List<Authority> authorities = authorityService.findByname("ROLE_PARENT");
		studentParent.setAuthorities(authorities);

		return stParentRepository.save(studentParent);
	}
}
