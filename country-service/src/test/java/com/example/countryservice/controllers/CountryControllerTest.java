package com.example.countryservice.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.countryservice.beans.Country;
import com.example.countryservice.services.CountryService;

@SpringBootTest(classes= {CountryControllerTest.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CountryControllerTest {
	@Mock
	CountryService countryService;
	
	@InjectMocks
	CountryController countryController;
	
	public List<Country> mycountries;
	Country country;

	@Test
	@Order(1)
	void testGetCountries() {
		mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1,"India","Delhi"));
		mycountries.add(new Country(2,"USA","Washington"));	
		
		when(countryService.getAllCountries()).thenReturn(mycountries);//Mocking
		ResponseEntity<List<Country>> res = countryController.getCountries();
		
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(2, res.getBody().size());
	}

	@Test
	@Order(2)
	void testGetCountryById() {
        country = new Country(1,"India","Delhi");
		
		int countryID=1;
		when(countryService.getCountryById(countryID)).thenReturn(country);//Mocking
		ResponseEntity<Country> res = countryController.getCountryById(countryID);
		
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(1, res.getBody().getIdCountry());
	}

	@Test
	@Order(3)
	void testGetCountryByName() {
        country = new Country(1,"India","Delhi");
		
		String countryName="India";
		when(countryService.getCountryByName(countryName)).thenReturn(country);//Mocking
		ResponseEntity<Country> res = countryController.getCountryByName(countryName);
		
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(countryName, res.getBody().getName());
			}

	@Test
	@Order(4)
	void testAddCountry() {
		country = new Country(3,"Germany","Berlin");
		
		when(countryService.addCountry(country)).thenReturn(country);//Mocking
		ResponseEntity<Country> res = countryController.addCountry(country);
		
		assertEquals(HttpStatus.CREATED, res.getStatusCode());
		assertEquals(country, res.getBody());
	}

	@Test
	@Order(5)
	void testUpdateCountry() {
		country = new Country(3,"Japan","Tokyo");
		int countryID = 3;
		
		when(countryService.getCountryById(countryID)).thenReturn(country);//Mocking
		when(countryService.updateCountry(country)).thenReturn(country);//Mocking
		
		ResponseEntity<Country> res = countryController.updateCountry(countryID, country);
		
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals("Japan", res.getBody().getName());
		assertEquals("Tokyo", res.getBody().getCapital());
	}

	@Test
	@Order(6)
	void testDeleteCountry() {
		country = new Country(3,"Japan","Tokyo");
		int countryID = 3;
		
		when(countryService.getCountryById(countryID)).thenReturn(country);//Mocking
		
		ResponseEntity<Country> res = countryController.deleteCountry(countryID);
		
		assertEquals(HttpStatus.OK, res.getStatusCode());
	}

}
