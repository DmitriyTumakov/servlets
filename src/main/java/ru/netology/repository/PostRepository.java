package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

// Stub
public class PostRepository {
  private final List<Post> allPosts = new ArrayList<>();
  private static int counter = 1;
  public List<Post> all() {
    return allPosts;
  }

  public Optional<Post> getById(long id) {
      for (Post postObject : allPosts) {
          if (id == postObject.getId()) {
              return Optional.of(postObject);
          }
      }
    throw new NotFoundException();
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      post.setId(counter);
      allPosts.add(post);
      counter++;
      return post;
    } else if (post.getId() >= 1) {
      int postId = (int) post.getId();

      for (Post postObject : allPosts) {
        if (postObject.getId() == postId) {
          allPosts.get(postId - 1).setContent(post.getContent());
          return post;
        }
      }
      throw new NotFoundException();
    }
    return null;
  }

  public void removeById(long id) {
    for (int i = 0; i < allPosts.size(); i++) {
      if (id == allPosts.get(i).getId()) {
        allPosts.remove(i);
        return;
      }
    }
    throw new NotFoundException();
  }
}
