package com.airlines.mvc.repository;

import com.airlines.mvc.model.Tourist;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristRepository extends PagingAndSortingRepository<Tourist, Long> {

}
