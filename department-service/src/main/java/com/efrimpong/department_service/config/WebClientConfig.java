package com.efrimpong.department_service.config;

import com.efrimpong.department_service.client.EmployeeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {
	private static  final Logger LOGGER  = LoggerFactory.getLogger(WebClientConfig.class);
	@Autowired
	private LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction;
	@Bean
	public WebClient employeeWebClient(){

		try {
			return WebClient.builder()
					.baseUrl("http://employee-service")
					.filter(loadBalancedExchangeFilterFunction)
					.build();
		} catch (WebClientRequestException e) {
			LOGGER.error("Request failed: ", e);
		}
		return null;
	}

	@Bean
	public EmployeeClient employeeClient(){
		LOGGER.debug("Called");
		HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
				.builderFor(WebClientAdapter.create(employeeWebClient()))
				.build();
		return httpServiceProxyFactory.createClient(EmployeeClient.class);
	}

}
