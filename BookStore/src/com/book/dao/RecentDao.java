package com.book.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.book.domain.Recent;

public interface RecentDao {
	List<Recent> selectRecent(Integer uid);

	void addRecent(@Param("bid")Integer bid, @Param("uid")Integer uid);

    boolean ifRidIsExists(@Param("bid")Integer bid, @Param("uid")Integer uid);

}
