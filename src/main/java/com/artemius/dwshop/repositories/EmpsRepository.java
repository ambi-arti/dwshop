package com.artemius.dwshop.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.artemius.dwshop.entities.*;

public interface EmpsRepository extends JpaRepository<Emps,Long>{
    List<Emps> findAllByfirstName(String firstName);
}
