package com.mathias.primenum.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface PrimeService {

	void calculateNumbersInInterval(int first, int last);
	List<Integer> returnList();
	ResponseEntity<?> returnListInInterval(int first, int last);
	public Boolean getIsRunning();
	public void SetIsRunning(Boolean isRunning);
	public void StopProcess();
	

}
