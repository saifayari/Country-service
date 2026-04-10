package com.example.countryservice.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.countryservice.beans.Country;

public interface CountryRepository extends JpaRepository<Country,Integer>{

}
