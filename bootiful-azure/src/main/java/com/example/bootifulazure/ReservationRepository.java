package com.example.bootifulazure;

import com.microsoft.azure.spring.data.cosmosdb.repository.DocumentDbRepository;

interface ReservationRepository extends DocumentDbRepository<Reservation, String> {
}
