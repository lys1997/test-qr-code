package cn.lys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.lys.entity.User;

public interface UserRepository extends JpaRepository<User,String>{

}
