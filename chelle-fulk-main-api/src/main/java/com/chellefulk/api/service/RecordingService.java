package com.chellefulk.api.service;

import com.chellefulk.api.model.Recording;
import com.chellefulk.api.repository.RecordingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordingService {
    @Autowired
    private RecordingRepository recordingRepository;

    public List<Recording> findAll() {
        return recordingRepository.findAll();
    }

    public Optional<Recording> findById(Long id) {
        return recordingRepository.findById(id);
    }

    public Recording save(Recording recording) {
        return recordingRepository.save(recording);
    }

    public void deleteById(Long id) {
        recordingRepository.deleteById(id);
    }
}
