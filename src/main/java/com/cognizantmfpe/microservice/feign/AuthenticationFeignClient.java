package com.cognizantmfpe.microservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizantmfpe.microservice.model.JwtResponse;


@FeignClient(name = "auth-service", url ="http://3.7.59.200:8083")
public interface AuthenticationFeignClient {


	@GetMapping(path = "/auth/validate")
	public JwtResponse verifyToken(@RequestHeader(name = "Authorization", required = true) String token);

}