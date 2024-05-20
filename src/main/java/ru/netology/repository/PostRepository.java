package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
//  private final List<Post> allPosts = new ArrayList<>();
  private final Map<Long, Post> allPosts = new ConcurrentHashMap<>();
  private static AtomicLong counter = new AtomicLong(1);

  public List<Post> all() {
    return new ArrayList<Post>(allPosts.values());
  }

  public Optional<Post> getById(long id) {
    if (allPosts.containsKey(id)) {
      return Optional.of(allPosts.get(id));
    }
    throw new NotFoundException();
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      post.setId(counter.get());
      allPosts.put(post.getId(), post);
      counter.getAndIncrement();
      return post;
    } else if (post.getId() >= 1) {
      if (allPosts.containsKey(post.getId())) {
        allPosts.get(post.getId()).setContent(post.getContent());
        return post;
      }
      throw new NotFoundException();
    }
    return null;
  }

  public void removeById(long id) {
    if (allPosts.containsKey(id)) {
      allPosts.remove(id);
      return;
    }
    throw new NotFoundException();
  }
}
