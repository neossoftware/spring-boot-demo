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

3. On the class GoogleServicesImpl add the next Annotation and add new Method
*   @HystrixCommand(fallbackMethod = "reliable")
	public String getDataBookGoogleCloud(String isbn) ......

    public String reliable(String isbn) .......

4. 

5. in command console execute payara5\bin\ --> $ asadmin.bat start-domain

6. Excute in a browser http://localhost:4848

![Image of payara5](https://github.com/hhugohm/microprofile-service/blob/master/src/main/resources/payara.JPG)


7. application option --> deploy -->select a file (war) -->ok

8. Execute on the browser http://localhost:8080/microprofile-example/api/students and http://localhost:8080/microprofile-example/

![Image of rest service students](https://github.com/hhugohm/microprofile-service/blob/master/src/main/resources/students.JPG)