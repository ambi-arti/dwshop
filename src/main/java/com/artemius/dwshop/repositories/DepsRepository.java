package com.artemius.dwshop.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.artemius.dwshop.entities.*;

public interface DepsRepository extends JpaRepository<Deps,Long>{
    List<Deps> findAllByname(String name);
}
