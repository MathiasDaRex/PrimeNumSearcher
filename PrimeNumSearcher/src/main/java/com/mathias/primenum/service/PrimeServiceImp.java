package com.mathias.primenum.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


@Service
public class PrimeServiceImp implements PrimeService{
	
	public List<Integer> primes;
	private Boolean isRunning = false;
	
	@Override
	public Boolean getIsRunning() {
		return isRunning;
	}
	
	@Override
	public void SetIsRunning(Boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	public PrimeServiceImp() {
		primes = new ArrayList<>();
	}
	
	@Override
	@Async
	public void calculateNumbersInInterval(@PathVariable int first,@PathVariable int last) {
		for (int i = first; i < last+1; i++) {
				isRunning = true;
				boolean isPrime = true;
				for (int j = 2; j < i; j++) {
					if(i%j==0) {
						isPrime = false;
						break;
					}
					try {
						 Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(isPrime) {
					System.out.println(i + " is a prime number");
					if(!primes.contains(i)) {
						primes.add(i);
					}
				}
				if(!isRunning) {
					break;
				}
			}
		isRunning = false;
	}
	
	@Override
	public void StopProcess() {
		isRunning = false;
	}
	
	@Override
	public synchronized List<Integer> returnList() {
		return primes;
	}

	@Override
	public ResponseEntity<?> returnListInInterval(@PathVariable int first, @PathVariable int last) {
		int firstOfList = primes.get(0);
		int lastOfList = primes.get(primes.size()-1);
		String errorResponse = "Az intervallum nem tartozik az előzőleg kiszámolt intervallumba";
		String tooBigInterval = "Az lekérdezett intervallum maximum 1000 lehet";
		
		if(last-first > 1000) {
			return new ResponseEntity<String>(tooBigInterval, HttpStatus.BAD_REQUEST);
		}
		if(first >= firstOfList && last <= lastOfList) {
			
			List<Integer> result = new ArrayList<>();
			for (Integer i : primes) {
				if(i>=first && i<=last) {
					result.add(i);
				}
			}
			
			return new ResponseEntity<List<Integer>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(errorResponse, HttpStatus.BAD_REQUEST);
		}	
	}
}
	
