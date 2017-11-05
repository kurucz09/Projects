package com.stupariu.controllers;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stupariu.DB.ConsultDatabase;
import com.stupariu.entity.Account;
import com.stupariu.entity.Consultation;
import com.stupariu.entity.Message;
import com.stupariu.entity.Patient;
import com.stupariu.enums.AccountType;
import com.stupariu.service.AuthToken;
import com.stupariu.service.DoctorService;

@RestController
@RequestMapping("/secretary")
@CrossOrigin
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	private Boolean checkRole(HttpServletRequest request, AccountType type) {
		String token = request.getHeader("token");
		AuthToken authToken = new AuthToken();
		String role = authToken.getRole(token);

		if (role.contentEquals(type.toString())) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Account> getAllAccounts(HttpServletRequest request, HttpServletResponse response) {
		if (checkRole(request,AccountType.Secretary) ) {
			return this.doctorService.getAllDoctors();
		} else {
			try {
				response.sendError(401, "error_role_not_given_GET");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}
	
	@RequestMapping(value = "/doctor/{doctor}/patient/{patient}", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Consultation> getAllConsultsOfDoctorPatient(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("doctor") String doctor,@PathVariable("patient") String patient) {
		if (checkRole(request,AccountType.Doctor) ) {
			return this.doctorService.getAllConsultsOfDoctorPatient(doctor,patient);
		} else {
			try {
				response.sendError(401, "error_role_not_given_GET");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}
	
	@RequestMapping(value = "/doctor", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Collection<Consultation> getConsultationOfDoctor(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Message msg) {
		if (checkRole(request,AccountType.Secretary ) || checkRole(request,AccountType.Doctor)) {
			return this.doctorService.getConsultationOfDoctor(msg.getMsg());
		} else {
			try {
				response.sendError(401, "error_role_not_given_GET");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}
	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Patient> getAllPatients(HttpServletRequest request, HttpServletResponse response) {
		if (checkRole(request,AccountType.Secretary) || checkRole(request,AccountType.Doctor)) {
			return this.doctorService.getAllPatients();
		} else {
			try {
				response.sendError(401, "error_role_not_given_GET");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void insertPatient(HttpServletRequest request, HttpServletResponse response, @RequestBody Patient patient) {
		if (checkRole(request,AccountType.Secretary)) {
			this.doctorService.insertPatient(patient);
		} else {
			try {
				response.sendError(401, "error_role_not_given");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void updatePatient(HttpServletRequest request, HttpServletResponse response, @RequestBody Patient patient) {
		if (checkRole(request,AccountType.Secretary)) {
			this.doctorService.updatePatient(patient);
		} else {
			try {
				response.sendError(401, "error_role_not_given");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = "/consultation",method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void updateConsultation(HttpServletRequest request, HttpServletResponse response, @RequestBody ConsultDatabase consultation) {
		if (checkRole(request,AccountType.Secretary) || checkRole(request,AccountType.Doctor)) {
			this.doctorService.updateConsultation(consultation);
		} else {
			try {
				response.sendError(401, "error_role_not_given");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = "/{pnc}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deletePatient(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("pnc") String pnc) {
		if (checkRole(request,AccountType.Secretary)) {
			this.doctorService.deletePatient(pnc);
		} else {
			try {
				response.sendError(401, "error_role_not_given");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = "consultation/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteConsultation(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String id) {
		if (checkRole(request,AccountType.Secretary)) {
			this.doctorService.deleteConsultation(id);
		} else {
			try {
				response.sendError(401, "error_role_not_given");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	

	@RequestMapping(value = "/consult",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void addConsultation(HttpServletRequest request, HttpServletResponse response, @RequestBody Consultation consultation) {
		if (checkRole(request,AccountType.Secretary)) {
			this.doctorService.addConsultation(consultation);
		} else {
			try {
				response.sendError(401, "error_role_not_given");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
