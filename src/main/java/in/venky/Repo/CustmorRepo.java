package in.venky.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.venky.Entity.Custmor;

public interface CustmorRepo extends JpaRepository<Custmor,Integer>{
  public Custmor findByEmail(String Email);
}