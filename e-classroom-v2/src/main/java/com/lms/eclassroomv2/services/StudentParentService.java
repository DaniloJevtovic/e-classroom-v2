package com.lms.eclassroomv2.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	public Page<StudentParent> getAllParentsPagination(Pageable pageable) {
		return stParentRepository.findAll(pageable);
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
	public ResponseEntity<?> saveStParent(StudentParent parent) {
		StudentParent checkParent = getParentByEmail(parent.getEmail());

		// provjeravam da li postoji taj roditelj u sistemu
		if (checkParent != null) {
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("body", checkParent);
			return new ResponseEntity<>(res, HttpStatus.OK);
		}

		try {
			StudentParent studentParent = new StudentParent();
			studentParent.setFirstName(parent.getFirstName());
			studentParent.setLastName(parent.getLastName());
			studentParent.setEmail(parent.getEmail());
			studentParent.setUsername(parent.getUsername());
			studentParent.setPassword(passwordEncoder.encode("123"));
			studentParent.setEnabled(true);

			List<Authority> authorities = authorityService.findByname("ROLE_PARENT");
			studentParent.setAuthorities(authorities);

			Map<String, Object> res = new HashMap<String, Object>();
			res.put("body", stParentRepository.save(studentParent));
			res.put("message", "Roditelj je uspjesno dodat");

			return ResponseEntity.ok(res);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Greska u dodavanju roditelja", HttpStatus.BAD_REQUEST);
		}

	}
}
