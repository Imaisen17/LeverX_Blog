package com.leverx.Blog.repositories;

import com.leverx.Blog.models.Posts;
import org.springframework.data.repository.CrudRepository;

public interface PostsRepository extends CrudRepository<Posts, Long> {
}
