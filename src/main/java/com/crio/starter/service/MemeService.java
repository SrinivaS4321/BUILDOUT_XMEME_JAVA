package com.crio.starter.service;

import com.crio.starter.data.Meme;
import com.crio.starter.repository.MemeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MemeService {

    private final MemeRepository memeRepository;

    public List<Meme> getAllMemes() {
        return memeRepository.findTop100ByOrderByIdDesc();
    }

    public Meme getMemeById(String id) {
        Meme meme = memeRepository.findById(id).orElse(null);

        if (meme == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return meme;
    }

    public Meme createMeme(Meme meme) {

        if (meme == null
                || meme.getName() == null
                || meme.getCaption() == null
                || meme.getUrl() == null
                || meme.getName().trim().isEmpty()
                || meme.getCaption().trim().isEmpty()
                || meme.getUrl().trim().isEmpty()) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Meme existing = memeRepository.findByNameAndCaptionAndUrl(
                meme.getName(),
                meme.getCaption(),
                meme.getUrl()
        );

        if (existing != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        return memeRepository.save(meme);
    }
}