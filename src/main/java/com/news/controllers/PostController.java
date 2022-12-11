package com.news.controllers;

import com.news.WebApplication;
import com.news.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/add")
    public @ResponseBody String add(@RequestParam String text, @RequestParam Long idAuthor) {
        return WebApplication.GSON.toJson(postService.add(text, idAuthor));
    }

    @GetMapping("/get")
    public @ResponseBody String get(@RequestParam Long id) {
        return WebApplication.GSON.toJson(postService.get(id).get());
    }

    @GetMapping("/getAllByAuthor")
    public @ResponseBody String getAllByAuthor(@RequestParam Long idAuthor) {
        return WebApplication.GSON.toJson(postService.getAllByAuthor(idAuthor));
    }

    @GetMapping("/addLike")
    public @ResponseBody String addLike(@RequestParam Long id) {
        return WebApplication.GSON.toJson(postService.addLike(id));
    }

    @GetMapping("/removeLike")
    public @ResponseBody String removeLike(@RequestParam Long id) {
        return WebApplication.GSON.toJson(postService.removeLike(id));
    }

    @PostMapping("/rewrite")
    public @ResponseBody String rewrite(@RequestParam String text,@RequestParam Long id,@RequestParam Long idAuthor) {
        return WebApplication.GSON.toJson(postService.rewrite(text, id, idAuthor));
    }

    @DeleteMapping("/delete")
    public @ResponseBody String delete(@RequestParam Long id) {
        return WebApplication.GSON.toJson(postService.delete(id).get());
    }
}
