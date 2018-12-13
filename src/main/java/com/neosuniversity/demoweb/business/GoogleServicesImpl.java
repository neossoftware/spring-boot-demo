package com.neosuniversity.demoweb.business;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class GoogleServicesImpl implements GoogleServices {

	private RestTemplate restTemplate;

	public GoogleServicesImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
	@HystrixCommand(fallbackMethod = "reliable", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") })
	public String getDataBookGoogleCloud(String isbn) {
		return this.restTemplate
				.exchange("http://localhost:8080/books/v2/volumes?q=isbn:" + isbn, HttpMethod.GET, null, String.class)
				.getBody();
	}

	public String reliable(String isbn) {
		return "Cloud Native Google " + isbn
				+ " (momentarily the service is out of line!! please try again en 5 minutes )";
	}

}
