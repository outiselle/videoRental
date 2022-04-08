package it.rea.videoRental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rea.videoRental.entity.Customer;
import it.rea.videoRental.payload.response.FilmRentResponse;
import it.rea.videoRental.repository.CustomerRepository;
import it.rea.videoRental.repository.RentalRepository;

@Service
public class CustomerService {
	
	@Autowired RentalRepository rentalRepository;
	@Autowired CustomerRepository customerRepository;
	
	/**
	 * <p>Method related to findByCustomerId query {@link it.rea.videoRental.repository.CustomerRepository#findById(Long)}
	 * @param id of customer. Type long
	 * @return optional Customer Entity
	 */
	public Optional<Customer> getCustomerById(long id){
		return customerRepository.findByCustomerId(id);
	}
	
	/**
	 * <p>Method related to filmRentByCustomerId query {@link it.rea.videoRental.repository.RentalRepository#filmsRentByCustomerId(long)}
	 * @param id long
	 * @return FilmRentResponse list of object
	 */
	public List<FilmRentResponse> filmsRentByCustomerId (long id){
		return rentalRepository.filmsRentByCustomerId(id);
	}

}
