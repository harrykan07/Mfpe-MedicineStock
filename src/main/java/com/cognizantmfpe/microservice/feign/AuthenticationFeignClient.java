package com.cognizantmfpe.microservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizantmfpe.microservice.model.JwtResponse;


<<<<<<< HEAD
@FeignClient(name = "auth-service", url ="http://3.7.59.200:8083")
=======
@FeignClient(name = "auth-service", url ="65.0.119.9:8083")
>>>>>>> 9dae0c9eadb71492dc42ac767f731af76ee283bf
public interface AuthenticationFeignClient {


	@GetMapping(path = "/auth/validate")
	public JwtResponse verifyToken(@RequestHeader(name = "Authorization", required = true) String token);

}
