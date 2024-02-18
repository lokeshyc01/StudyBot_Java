package com.app.dao;
import com.app.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TagRepsitory extends JpaRepository<Tag, Long>{

}
