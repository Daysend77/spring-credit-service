//Класс для ограничения поступления заявок от конкретной страны за определенный промежуток

package com.credit.inceptors;

import com.credit.entities.Country;
import com.credit.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



@Component
public class RateLimitInterceptor extends HandlerInterceptorAdapter {

		@Autowired
		CountryService countryService;

		private Map<String, Integer> limiters = new ConcurrentHashMap<>();

		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
			//String countryName = request.getParameter("country");

//			if (countryName == null) {
//			return true;
//			}

//			Country country = countryService.findByName(countryName);
//			//SimpleRateLimiter rateLimiter = getRateLimiter(countryName);
//
//
//			boolean allowRequest = rateLimiter.tryAcquire();
//
//			if (!allowRequest) {
//				response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
//			}
//			return allowRequest;

			return true;
		}

//	private SimpleRateLimiter getRateLimiter(String countryName) {
//			Country country = countryService.findByName(countryName);
//			return limiters.put(countryName, SimpleRateLimiter.create(country.getClaimLimit()));
//	}
	}
