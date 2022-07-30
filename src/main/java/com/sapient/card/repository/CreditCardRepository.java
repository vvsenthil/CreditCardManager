package com.sapient.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.card.entity.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, String> {
	CreditCard findByNumber(String number);
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("update CreditCard c SET c.balance = :balance WHERE c.number = :number")
	int updateCreditCardUsingQueryAnnotation(@Param("balance") int balance, @Param("number") String number);
}
