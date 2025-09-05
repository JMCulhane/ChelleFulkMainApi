package com.chellefulk.api.service;

import com.chellefulk.api.model.Video;
import com.chellefulk.api.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    public Optional<Video> findById(Long id) {
        return videoRepository.findById(id);
    }

    public Video save(Video video) {
        return videoRepository.save(video);
    }

    public void deleteById(Long id) {
        videoRepository.deleteById(id);
    }
}
