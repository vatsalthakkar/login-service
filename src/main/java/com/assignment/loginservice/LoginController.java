package com.assignment.loginservice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class LoginController {

	@Autowired
	private RegistrationServiceProxy proxy;
	
	@PostMapping("/login")
	public String userLogin(@RequestBody UserBean user) {
		
		
		String uri="http://localhost:8000//validate/{username}/{password}";
		Map<String,String> uriVariables = new HashMap<>();
		uriVariables.put("username", user.getUsername());
		uriVariables.put("password", user.getPassword());
		ResponseEntity<UserBean> responseEntity = new RestTemplate().getForEntity(uri,UserBean.class,uriVariables);
		UserBean response = responseEntity.getBody();
		if(response==null) {
			return "Login failed";
		}
		return "Hello "+ user.getUsername()+"\nLogin successful";
	}
	
	@PostMapping("/feignlogin")
	public String userLoginFeign(@RequestBody UserBean user) {
		
		UserBean response = proxy.validateUser(user.getUsername(), user.getPassword());
		if(response==null) {
			return "Login failed";
		}
		return "Hello "+ user.getUsername()+"\nLogin successful";
	}
}
