package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.converter.LikeConverter;
import org.example.dto.LikeDTO;
import org.example.entity.Like;
import org.example.exceptions.LikeException;
import org.example.service.LikeService;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/likes")
public class LikeController {
    private final LikeConverter likeConverter;
    private final LikeService likeService;

    @PostMapping
    public Like save(@RequestBody LikeDTO likeDTO) throws LikeException {
        Like like = likeConverter.toModel(likeDTO);
        return likeService.save(like);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        likeService.deleteById(id);
    }
}
