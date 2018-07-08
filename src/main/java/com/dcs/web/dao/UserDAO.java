package com.dcs.web.dao;

import com.dcs.web.pojo.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<UserPO, Long> {
    UserPO getByUsername(String username);
}
