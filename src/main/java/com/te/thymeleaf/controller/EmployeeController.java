package com.te.thymeleaf.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.te.thymeleaf.entity.Employee;
import com.te.thymeleaf.repository.EmployeeRepository;
import com.te.thymeleaf.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping({"/showEmployee","/","/list"})
	private ModelAndView showEmployee() {
		ModelAndView mav=new ModelAndView("list-employees");
		List<Employee> list=repository.findAll();
		mav.addObject("employees",list);
		return mav;
	}
	
	
	
	@GetMapping("/addEmployeeForm")
	public ModelAndView addEmployeeForm() {
		ModelAndView mav=new ModelAndView("add-employee-form");
		Employee newemployee= new Employee();
		mav.addObject("employee",newemployee);
		return mav;
		
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute Employee  employee) {
		repository.save(employee);
		return "redirect:/list";   //we have to call redirect to call the list //
	}
	
	@GetMapping("/showUpdateForm")
	public ModelAndView showUpdateForm(@RequestParam Integer employeeId) {
		ModelAndView mav=new ModelAndView("add-employee-form");
		Employee employee = repository.findById(employeeId).get();
		mav.addObject("employee",employee);
		return mav;
	}
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam Integer employeeId) {
		repository.deleteById(employeeId);
		return "redirect:list";
	}
	
	
	@GetMapping("/excel")
	public void generateExcelReport(HttpServletResponse response) throws Exception {

		response.setContentType("application/octet-stream"); // this method is responsible to download the excel file//

		String headerKey = "Content-Disposition"; // for set response header we have to use key and value//
		String headerValue = "attachment;filename=employee.xls";

		response.setHeader(headerKey, headerValue);

		employeeService.generateExcel(response);
	}
	
	
}
