package com.dental.app.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dental.app.web.models.entities.Doctor;
import com.dental.app.web.models.service.IDoctorService;

@Controller
@RequestMapping(value="/doctor")
public class DoctorController {
	
	@Autowired
	private IDoctorService service;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		
		Doctor doctor = new Doctor();
		model.addAttribute("doctor", doctor);
		model.addAttribute("title", "Registro de nuevo doctor");
		
		return "doctor/form";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		
		Doctor doctor = service.findById(id);
		model.addAttribute("doctor", doctor);
		
		return "doctor/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		
		Doctor doctor = service.findById(id);
		model.addAttribute("doctor", doctor);
		model.addAttribute("title", "Actualizando el registro de " + doctor.getApellidos());
		
		return "doctor/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model, RedirectAttributes flash) {
		
		try {
			
			service.delete(id);
			flash.addFlashAttribute("success", "El registro se eliminó exitosamente");
			
		}catch(Exception ex) {
			
			flash.addFlashAttribute("error", "El registro no se eliminó");
			
		}
		
		return  "redirect:/doctor/list";
	
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		
		List<Doctor> list = service.findAll();
		model.addAttribute("title", "Listado de doctores");
		model.addAttribute("list", list);
		
		return "doctor/list";
	}
	
	@PostMapping(value="/save")
	public String save(@Valid Doctor doctor,BindingResult result, Model model,
			RedirectAttributes flash) {
		
		try {
			if(result.hasErrors())
			{
				if(doctor.getIdpersona() == null) {
					model.addAttribute("tittle","Registro de un nuevo Paciente");					
				}
				else {
					model.addAttribute("tittle","Actualizando el registro de " 
							+ doctor.getNombres());
				}
				
				return"doctor/form";
			}
			service.save(doctor);
			flash.addFlashAttribute("success", "Registro guardado con éxito");
			
		}
		catch(Exception ex){
			
			flash.addFlashAttribute("error", "no se pudo guardar");
			
		}
		
		return "redirect:/doctor/list";
	}


}
