
package com.chellefulk.api.controller;

import com.chellefulk.api.model.Recording;
import com.chellefulk.api.service.RecordingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

import java.util.List;


@RestController
@RequestMapping("/api/recordings")
public class RecordingController {
    @Autowired
    private RecordingService recordingService;

    @GetMapping
    public List<Recording> getAllRecordings() {
        return recordingService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recording> getRecordingById(@PathVariable Long id) {
        return recordingService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> createRecordingMultipart(
            @RequestParam("title") String title,
            @RequestParam("yearPublished") int yearPublished,
            @RequestParam("description") String description,
            @RequestParam("trackCount") int trackCount,
            @RequestParam("link") String link,
            @RequestParam Map<String, String> allParams,
            @RequestParam(value = "performers[]", required = false) List<String> performers,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestParam Map<String, MultipartFile> fileMap
    ) {
        // Example: parse samples and audio files from allParams and fileMap
        // This is a minimal example, you should add error handling and storage logic
        Recording rec = new Recording();
        rec.setTitle(title);
        rec.setYearPublished(yearPublished);
        rec.setDescription(description);
        rec.setTrackCount(trackCount);
        rec.setLink(link);
        if (performers != null) rec.setPerformers(performers);
        // Save image file as BLOB
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                rec.setImage(imageFile.getBytes());
            } catch (Exception e) {
                rec.setImage(null);
            }
        }
        // Parse samples and store audio as BLOB
        List<com.chellefulk.api.model.Sample> samples = new java.util.ArrayList<>();
        int i = 0;
        while (true) {
            String trackName = allParams.get("samples[" + i + "][trackName]");
            String duration = allParams.get("samples[" + i + "][duration]");
            MultipartFile audioFile = fileMap.get("audioFiles[" + i + "]");
            if (trackName == null && audioFile == null) break;
            com.chellefulk.api.model.Sample sample = new com.chellefulk.api.model.Sample();
            sample.setName(trackName != null ? trackName : "");
            if (audioFile != null && !audioFile.isEmpty()) {
                try {
                    sample.setAudio(audioFile.getBytes());
                } catch (Exception e) {
                    sample.setAudio(null);
                }
            } else {
                sample.setAudio(null);
            }
            samples.add(sample);
            i++;
        }
        rec.setSamples(samples);
        Recording saved = recordingService.save(rec);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recording> updateRecording(@PathVariable Long id, @RequestBody Recording recording) {
        return recordingService.findById(id)
                .map(existing -> {
                    recording.setId(id);
                    return ResponseEntity.ok(recordingService.save(recording));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecording(@PathVariable Long id) {
        if (recordingService.findById(id).isPresent()) {
            recordingService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
