package com.crio.starter.repository;

import com.crio.starter.data.Meme;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemeRepository extends MongoRepository<Meme, String> {

    Meme findByNameAndCaptionAndUrl(String name, String caption, String url);

    List<Meme> findTop100ByOrderByIdDesc();
}