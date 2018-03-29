package com.assignment.loginservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="registration-service",url="localhost:8000")
public interface RegistrationServiceProxy {

	@GetMapping("/validate/{username}/{password}")
	public UserBean validateUser(@PathVariable("username") String username,@PathVariable("password") String password);
}
