package com.example.bootifulazure;


import com.microsoft.azure.spring.data.cosmosdb.repository.DocumentDbRepository;
import org.springframework.data.repository.CrudRepository;

// todo restore this
interface ReservationRepository extends CrudRepository<Reservation, String>{
//	extends DocumentDbRepository<Reservation, String> {
}

