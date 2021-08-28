package com.example.techthink.persistence.repository;

import com.example.techthink.persistence.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
