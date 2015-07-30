package com.possiblelabs.serverspringtest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by possiblelabs on 7/30/15.
 */
@RestController
public class PostController {


    private static List<Post> posts = new ArrayList<Post>();
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public @ResponseBody List<Post> listPosts(@RequestParam(value = "order", defaultValue = "asc") String order) {
        return posts;
    }

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public @ResponseBody Post addPost(@RequestBody Post post){
        post.setId(counter.incrementAndGet() +"");
        posts.add(post);
        return posts.get(posts.size() -1);
    }

    private Post getPostById(String id){
        for(Post post: posts){
            if(post.getId().equals(id)){
                return post;
            }
        }
        return null;
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public @ResponseBody Post getPost(@PathVariable String id){
        return getPostById(id);
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.PUT)
    public @ResponseBody Post updatePost(@PathVariable String id, @RequestBody Post post){
        Post ref = getPostById(id);
        if(ref != null)
            ref.updatePost(post);
        return ref;
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.PATCH)
    public @ResponseBody Post modifyPost(@PathVariable String id, @RequestBody Post post){
        Post ref = getPostById(id);
        if(ref != null)
            ref.modifyPost(post);
        return ref;
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Post deletePost(@PathVariable String id){
        Post ref = getPostById(id);
        if(ref != null)
            posts.remove(ref);
        return ref;
    }

}
