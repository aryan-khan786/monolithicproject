package com.te.thymeleaf.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.thymeleaf.entity.Employee;
import com.te.thymeleaf.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	public void generateExcel(HttpServletResponse response) throws IOException { // when user send a request directly// that excel file should download//
		
		List<Employee> employees = repository.findAll(); //// it will give list of empoloyee details whatever in my// entity class//
	
HSSFWorkbook workbook = new HSSFWorkbook(); // workbook object is created//
HSSFSheet sheet = workbook.createSheet("Employee Info");
HSSFRow row = sheet.createRow(0); // the row start from 0 in header row//


row.createCell(0).setCellValue("Id"); //in row index 0 i have taking for storing header data//
row.createCell(1).setCellValue("Name");
row.createCell(2).setCellValue("Email");
row.createCell(3).setCellValue("Department");




int dataRowIndex=1; //data row start from 1st index//

for(Employee employee :employees) { //for every row will be creating for row index //
	HSSFRow dataRow= sheet.createRow(dataRowIndex);
	dataRow.createCell(0).setCellValue(employee.getId());
	dataRow.createCell(1).setCellValue(employee.getName());
	dataRow.createCell(2).setCellValue(employee.getEmail());
	dataRow.createCell(3).setCellValue(employee.getDepartment());
	dataRowIndex++ ; //it is used to increment the row index//
}

ServletOutputStream outputStream=response.getOutputStream(); //get output stream is used to give the output in stream object//
workbook.write(outputStream);    //whatever the data is available in the workbook so in this i have to write the data in output stream//

workbook.close();
outputStream.close();














	}
}
