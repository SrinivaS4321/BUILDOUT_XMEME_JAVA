package com.crio.starter.controller;

import com.crio.starter.data.Meme;
import com.crio.starter.service.MemeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memes")
public class MemeController {

    private final MemeService memeService;

    @GetMapping
    public ResponseEntity<List<Meme>> getAllMemes() {
        return ResponseEntity.ok(memeService.getAllMemes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meme> getMemeById(@PathVariable String id) {
        return ResponseEntity.ok(memeService.getMemeById(id));
    }

    @PostMapping
    public ResponseEntity<Meme> createMeme(@RequestBody Meme meme) {
        Meme savedMeme = memeService.createMeme(meme);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMeme);
    }
}