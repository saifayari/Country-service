package com.example.countryservice.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.countryservice.repositories.CountryRepository;
import com.example.countryservice.beans.Country;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CountryServiceTest {
	@Mock
	CountryRepository countryRepository;
	
	@InjectMocks
	CountryService countryService;

	public List<Country> mycountries;

	@Test
	@Order(1)
	void testGetAllCountries() {
		List<Country> mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1,"India","Delhi"));
		mycountries.add(new Country(2,"USA","Washington"));		
		
		when(countryRepository.findAll()).thenReturn(mycountries);//Mocking
		assertEquals(2, countryService.getAllCountries().size());
	}

	@Test
	@Order(2)
	void testGetCountryById() {
		List<Country> mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1,"India","Delhi"));
		mycountries.add(new Country(2,"USA","Washington"));
		
		int countryID=1;
		when(countryRepository.findAll()).thenReturn(mycountries);//Mocking
		assertEquals(countryID, countryService.getCountryById(countryID).getIdCountry());
	}

	@Test
	@Order(3)
	void testGetCountryByName() {
		List<Country> mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1,"India","Delhi"));
		mycountries.add(new Country(2,"USA","Washington"));
		
		String countryName="USA";
		when(countryRepository.findAll()).thenReturn(mycountries);//Mocking
		assertEquals(countryName, countryService.getCountryByName(countryName).getName());
	}

	@Test
	@Order(4)
	void testAddCountry() {
		Country country = new Country(3,"France","Paris");
		when(countryRepository.save(country)).thenReturn(country);//Mocking
		assertEquals(country, countryService.addCountry(country));
	}

	@Test
	@Order(5)
	void testUpdateCountry() {
		Country country = new Country(3,"Germany","Berlin");
		when(countryRepository.save(country)).thenReturn(country);//Mocking
		assertEquals(country, countryService.updateCountry(country));
	}

	@Test
	@Order(6)
	void testDeleteCountry() {
		Country country = new Country(3,"Germany","Berlin");
		countryService.deleteCountry(country);
		verify(countryRepository,times(1)).delete(country);
	}

}
