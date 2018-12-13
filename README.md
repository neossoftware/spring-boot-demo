----
STEPS :shipit:
----
1. modify pom.xm and add the nex configuration:
*       <dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
		</dependency>

2. On the DemoWebApplication class add the next Annotation:
* @EnableHystrixDashboard

3. On the class GoogleServicesImpl add the next Annotation and add new Method reliable(String isbn) .......
*           @HystrixCommand(fallbackMethod = "reliable", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") })
	        public String getDataBookGoogleCloud(String isbn) ......

    public String reliable(String isbn) .......

4. modify application.properties add netx configuration:
*   management.endpoint.shutdown.enabled=true
    management.endpoint.health.show-details=always
    management.endpoints.web.exposure.include=*

5. Execute  in a browser : http://localhost:8080/hystrix
![Image of dashboard](https://github.com/neossoftware/spring-boot-demo/blob/spring-boot-dashboardhystrix/src/main/resources/images/login_dashboard.JPG)

6. Configure parameters on the dashboard: URL --> http://localhost:8080/actuator/hystrix.stream  and Title --> MyHistrix -->Click monitor Streams button

7. Execute many times  in a browser : http://localhost:8080/book/1449374646

8. Return to browser dashboard of hystrix

![Image of dashboard-hystrix](https://github.com/neossoftware/spring-boot-demo/blob/spring-boot-dashboardhystrix/src/main/resources/images/dashboard.JPG)