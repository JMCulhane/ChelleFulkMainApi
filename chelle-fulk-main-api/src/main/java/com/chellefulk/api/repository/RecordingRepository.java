package com.chellefulk.api.repository;

import com.chellefulk.api.model.Recording;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordingRepository extends JpaRepository<Recording, Long> {}
