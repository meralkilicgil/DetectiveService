package com.meri.murdermysterygame.repository;

import com.meri.murdermysterygame.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview, Long> {

    List<Interview> findAllByOrderById();
}
