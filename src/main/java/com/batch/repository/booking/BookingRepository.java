package com.batch.repository.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
