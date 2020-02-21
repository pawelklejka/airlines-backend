package com.example.airlines.repository;

import com.example.airlines.model.Tourist;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristRepository extends PagingAndSortingRepository<Tourist, Long> {

}
