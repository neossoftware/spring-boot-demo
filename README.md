----
STEPS :shipit:
----
1. modify pom.xm and add the nex configuration:
*       <dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
		</dependency>
*       <dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>Finchley.SR2</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	    </dependencyManagement>

2. On the DemoWebApplication class add the next Annotation:
* @EnableCircuitBreaker

3. On the class GoogleServicesImpl add the next Annotation and add new Method reliable(String isbn) .......
*   @HystrixCommand(fallbackMethod = "reliable")
	public String getDataBookGoogleCloud(String isbn) ......

    public String reliable(String isbn) .......

4. Execute in a browser : http://localhost:8080/book/1449374646

![Image of payara5](https://github.com/neossoftware/spring-boot-demo/blob/spring-boot-circuitbreaker/src/main/resources/images/circuitbreaker.JPG)

