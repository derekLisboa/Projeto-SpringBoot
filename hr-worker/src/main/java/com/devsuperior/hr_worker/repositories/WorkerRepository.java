package com.devsuperior.hr_worker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.hr_worker.entities.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long>{

}
