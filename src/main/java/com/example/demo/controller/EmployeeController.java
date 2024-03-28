package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApiEmployeeResponse;
import com.example.demo.dto.ApiListResponse;
import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.DeleteEmployeeRequest;
import com.example.demo.dto.DeleteEmployeeResponse;
import com.example.demo.dto.EmployeeDetailRequest;
import com.example.demo.dto.EmployeeDetailResponse;
import com.example.demo.dto.EmployeeListRequest;
import com.example.demo.dto.EmployeeListResponse;
import com.example.demo.dto.EmployeeRequest;
import com.example.demo.dto.EmployeeResponse;
import com.example.demo.dto.UpdateEmployee;
import com.example.demo.dto.UpdateEmployeeRequest;
import com.example.demo.dto.UpdateEmployeeResponse;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@GetMapping("/logger-pattern")
	public void greeting(
			@RequestHeader String userid,
			@RequestHeader String api,
			@RequestHeader String status,
			@RequestHeader String error_code,
			@RequestHeader String error_message,
			@RequestHeader String clientRequestId,
			@RequestHeader String request) {

		MDC.put("userid", userid);
		MDC.put("api", api);
		MDC.put("status", status);
		MDC.put("error_code", error_code);
		MDC.put("error_message", error_message);
		MDC.put("clientRequestId", clientRequestId);
		MDC.put("request", request);

		// Log the message
		logger.debug("working");

	}

	@PostMapping("/myhr/employee/add")
	public ResponseEntity<EmployeeResponse> addEmployee(@RequestBody EmployeeRequest employeeRequest) {

		Employee employee = new Employee();
		employee.setFname(employeeRequest.getData().get("fname"));
		employee.setFullname(employeeRequest.getData().get("fullname"));
		String dateString = employeeRequest.getData().get("dob");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dob = new Date();
		try {
			dob = dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		employee.setDob(dob);
		String dateStringjoining = employeeRequest.getData().get("doj");
		Date doj = new Date();
		try {
			doj = dateFormat.parse(dateStringjoining);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		employee.setDoj(doj);
		Integer salary = Integer.parseInt(employeeRequest.getData().get("salary"));
		employee.setSalary(salary);

		Long reportsTo = Long.parseLong(employeeRequest.getData().get("reportsTo"));
		employee.setReportsTo(reportsTo);

		Long rankId = Long.parseLong(employeeRequest.getData().get("rankid"));
		employee.setRankId(rankId);

		Long departmentId = Long.parseLong(employeeRequest.getData().get("deptid"));
		employee.setDeptId(departmentId);

		employee.setClientReqId(employeeRequest.getReqid());
		employee.setEmpId(Long.parseLong(employeeRequest.getData().get("empId")));

		ApiResponse result = employeeService.addEmployee(employee);
		EmployeeResponse employeeResponse = new EmployeeResponse();
		if (result.getStatus() == "success") {
			employeeResponse.setReqid(employeeRequest.getReqid());
			employeeResponse.setStatus(HttpStatus.CREATED);
			employeeResponse.setStatus_code(HttpStatus.CREATED.value());
			employeeResponse.setStatus_msg(HttpStatus.CREATED.toString());
			employeeResponse.set_server_ts(LocalDateTime.now());
			Map<String, String> data = new HashMap<>();
			data.put("EmpId", result.getEmployee().getEmpId().toString());
			data.put("FullName", result.getEmployee().getFullname());

			logger.debug("Data saved successfully.");

			employeeResponse.setData(data);
		} else {
			employeeResponse.setReqid(employeeRequest.getReqid());
			employeeResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			employeeResponse.setStatus_code(HttpStatus.INTERNAL_SERVER_ERROR.value());
			employeeResponse.setStatus_msg(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			employeeResponse.set_server_ts(LocalDateTime.now());
			Map<String, String> data = new HashMap<>();

			logger.debug("Data Not saved successfully.");

			employeeResponse.setData(data);
		}
		return ResponseEntity.ok(employeeResponse);
	}

	@GetMapping("/myhr/employee/list")
	public ResponseEntity<EmployeeListResponse> listEmployee(@RequestBody EmployeeListRequest employeeListRequest) {
		ApiListResponse result = new ApiListResponse();
		if (employeeListRequest.getFilter() == null) {
			result = employeeService.listOfEmployees();
		} else {
			String filter = employeeListRequest.getFilter();
			result = employeeService.listofFilteredEmployee(filter);
		}
		EmployeeListResponse employeelistResponse = new EmployeeListResponse();
		if (result.getStatus() == "success") {
			employeelistResponse.setReqid(employeeListRequest.getReqid());
			employeelistResponse.setStatus(HttpStatus.OK);
			employeelistResponse.setStatus_code(HttpStatus.OK.value());
			employeelistResponse.setStatus_msg(HttpStatus.OK.toString());
			employeelistResponse.set_server_ts(LocalDateTime.now());
			List<Map<String, String>> data = new ArrayList<>();
			int size = result.getEmployeeList().size();
			for (int i = 0; i < size; i++) {
				Employee e = result.getEmployeeList().get(i);
				Map<String, String> res = new HashMap<>();
				res.put("empId", e.getEmpId().toString());
				res.put("empname", e.getFullname());
				data.add(res);
			}

			logger.debug("Data saved successfully.");

			employeelistResponse.setData(data);
		} else {
			employeelistResponse.setReqid(employeeListRequest.getReqid());
			employeelistResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			employeelistResponse.setStatus_code(HttpStatus.INTERNAL_SERVER_ERROR.value());
			employeelistResponse.setStatus_msg(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			employeelistResponse.set_server_ts(LocalDateTime.now());
			List<Map<String, String>> data = new ArrayList<>();

			logger.debug("Data Not saved successfully.");

			employeelistResponse.setData(data);
		}
		return ResponseEntity.ok(employeelistResponse);
	}

	@GetMapping("/myhr/employee/lists")
	public ResponseEntity<String> getGreeting(@RequestParam(defaultValue = "xml") String type) {
		String greeting = "<greeting>Hello, World!</greeting>";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);

		return new ResponseEntity<>(greeting, headers, HttpStatus.OK);
	}
//question no-9
	@GetMapping("/myhr/employee/get")
	public ResponseEntity<EmployeeDetailResponse> getEmployeeDetail(
			@RequestBody EmployeeDetailRequest employeeDetailRequest) {
		Long empId = Long.parseLong(employeeDetailRequest.getEmpId());
		ApiEmployeeResponse result = employeeService.findEmployeeDetail(empId);
		EmployeeDetailResponse empDetailResponse = new EmployeeDetailResponse();
		if (result.getStatus() == "success") {
			empDetailResponse.setReqid(employeeDetailRequest.getReqid());
			empDetailResponse.setStatus(HttpStatus.OK);
			empDetailResponse.setStatus_code(HttpStatus.OK.value());
			empDetailResponse.setStatus_msg(HttpStatus.OK.toString());
			empDetailResponse.set_server_ts(LocalDateTime.now());
			Map<String, String> data = new HashMap<>();
			data.put("empName", result.getEmpDetails().getEmployeeName());
			data.put("departmentName", result.getEmpDetails().getDepartmentName());
			data.put("rankDescription", result.getEmpDetails().getRankDescription());

			logger.debug("Data Getting successfully.");

			empDetailResponse.setData(data);
		} else {
			empDetailResponse.setReqid(employeeDetailRequest.getReqid());
			empDetailResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			empDetailResponse.setStatus_code(HttpStatus.INTERNAL_SERVER_ERROR.value());
			empDetailResponse.setStatus_msg(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			empDetailResponse.set_server_ts(LocalDateTime.now());
			Map<String, String> data = new HashMap<>();

			logger.debug("Data Not Getting .");

			empDetailResponse.setData(data);
		}
		return ResponseEntity.ok(empDetailResponse);
	}

	@PutMapping("/myhr/employee/update")
	public ResponseEntity<UpdateEmployeeResponse> updateEmployee(
			@RequestBody UpdateEmployeeRequest updateEmployeerequest) {
		Map<String, String> data = updateEmployeerequest.getData();
		UpdateEmployee updateEmployee = new UpdateEmployee();
		String fullname = data.get("fullname");
		if (fullname != null) {
			updateEmployee.setFullname(fullname);
		}
		String name = data.get("fname");
		if (name != null) {
			updateEmployee.setFname(fullname);
		}
		String dobString = data.get("dob");
		if (dobString != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date dob = dateFormat.parse(dobString);
				updateEmployee.setDob(dob);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		String dojString = data.get("doj");
		if (dojString != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date doj = dateFormat.parse(dojString);
				updateEmployee.setDoj(doj);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		String salarystr = data.get("salary");
		if (salarystr != null) {
			int salary = Integer.parseInt(salarystr);
			updateEmployee.setSalary(salary);
		}
		String deptstr = data.get("deptid");
		if (deptstr != null) {
			Long deptId = Long.parseLong(data.get(deptstr));
			updateEmployee.setDeptId(deptId);
		}
		String rankstr = data.get("rankid");
		if (rankstr != null) {
			Long rank = Long.parseLong(data.get(rankstr));
			updateEmployee.setRankId(rank);
		}
		String reportsToStr = data.get("reportsTo");
		if (reportsToStr != null) {
			Long reprortsTO = Long.parseLong(data.get(reportsToStr));
			updateEmployee.setReportsTo(reprortsTO);
		}
		boolean res = employeeService.updateEmployee(updateEmployee, Long.parseLong(updateEmployeerequest.getEmpId()));
		UpdateEmployeeResponse response = new UpdateEmployeeResponse();
		if (res == true) {
			response.setReqid(updateEmployeerequest.getReqid());
			response.setStatus(HttpStatus.OK);
			response.setStatus_code(HttpStatus.OK.value());
			response.setStatus_msg(HttpStatus.OK.toString());
			response.set_server_ts(LocalDateTime.now());

			logger.debug("Data Updating successfully.");

			response.setMessage("Updated Employee record for empid: " + updateEmployeerequest.getEmpId());
		} else {
			response.setReqid(updateEmployeerequest.getReqid());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setStatus_code(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setStatus_msg(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			response.set_server_ts(LocalDateTime.now());

			logger.debug("Data Not Updating.");

			response.setMessage("Could not update employee record for empid: " + updateEmployeerequest.getEmpId());
		}
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/myhr/employee/delete")
	public ResponseEntity<DeleteEmployeeResponse> deleteEmployee(
			@RequestBody DeleteEmployeeRequest deleteEmployeeRequest) {
		Long empId = Long.parseLong(deleteEmployeeRequest.getEmpId());
		boolean res = employeeService.deleteEmployee(deleteEmployeeRequest, empId);
		DeleteEmployeeResponse response = new DeleteEmployeeResponse();
		if (res == true) {
			response.setReqid(deleteEmployeeRequest.getReqid());
			response.setStatus(HttpStatus.OK);
			response.setStatus_code(HttpStatus.OK.value());
			response.setStatus_msg(HttpStatus.OK.toString());
			response.set_server_ts(LocalDateTime.now());

			logger.debug("Data Deleted successfully.");

			response.setMessage("Deleted Employee record for empid: " + deleteEmployeeRequest.getEmpId());
		} else {
			response.setReqid(deleteEmployeeRequest.getReqid());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setStatus_code(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setStatus_msg(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			response.set_server_ts(LocalDateTime.now());

			logger.debug("Data Not Deleted.");

			response.setMessage("Could not delete employee record for empid: " + deleteEmployeeRequest.getEmpId());
		}
		return ResponseEntity.ok(response);
	}
}