package com.cognizantmfpe.microservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizantmfpe.microservice.model.JwtResponse;


@FeignClient(name = "auth-service", url ="65.0.119.9:8083")
public interface AuthenticationFeignClient {


	@GetMapping(path = "/validate")
	public JwtResponse verifyToken(@RequestHeader(name = "Authorization", required = true) String token);

}
