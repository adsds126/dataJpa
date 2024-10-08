package com.study.data_jpa.repository;

import com.study.data_jpa.dto.MemberDto;
import com.study.data_jpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    List<Member> findByUsername(@Param("username") String username);
    @Query("select m from Member m where m.username= :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);
    @Query(value = "select m from Member m",
            countQuery = "select count(m.username) from Member m")
    Page<Member> findByAge(int age, Pageable pageable);
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);
    @Query("select m.username from Member m")
    List<String> findUsernameList();
    @Query("select new com.study.data_jpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Modifying(clearAutomatically = true) // 이 쿼리가 나가고 em.clear 자동으로 해줌
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    List<UsernameOnly> findProjectionsByUsername(@Param("username") String username);
}
