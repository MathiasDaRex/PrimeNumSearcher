package com.mathias.primenum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.mathias.primenum.service.PrimeService;

@RestController
public class PrimeController {
	
	@Autowired
	private PrimeService pService;

	// swagger ui felület
	// http://localhost:8080/swagger-ui/index.html#/
	
	@GetMapping("/primeslist/{first}/{last}")
	public String calculatePrimes(@PathVariable int first, @PathVariable int last){
		if(!pService.getIsRunning()) {
			
			pService.calculateNumbersInInterval(first, last);
			return "A keresés elindult";
		} else {
			return "A keresés már fut.";
		}
	}
	
	@GetMapping("/primeslist/stopCalc")
	public String StopCalculating() {
		pService.StopProcess();
		return "A folyamat leállt";
	}
	
	@GetMapping("/primeslist/getlist")
	public List<Integer> getList(){
		
		return pService.returnList();
	}
	
	@GetMapping("/primeslist/getlist/{first}/{last}")
	public ResponseEntity<?> getListInInterval(@PathVariable int first, @PathVariable int last){
		return pService.returnListInInterval(first, last);
	}
}