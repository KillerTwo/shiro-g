package org.lwt.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lwt.entity.Member;
import org.lwt.entity.Users;
import org.lwt.enums.Sex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Test01 {
  
  @Autowired
  private MemberMapperBak memberMapperBak;
  @Autowired
  private MemberMapper memberMapper;
  
  @Autowired
  private UsersMapper userMapper;
  
  @Test
  @Ignore
  public void testTypeHandler() {
    List<Member> list = memberMapperBak.queryAll();
    for (Member member : list) {
      System.err.println(member.getSex().getStatus());
    }
    //System.err.println(Sex.getSexForCode(1).getStatus());
  }
  @Test
  @Ignore
  public void testTypeHandler2() {
    List<Member> list = memberMapper.selectAll();
    System.err.println("查询到的值：");
    for (Member member : list) {
      System.err.println(member.getSex().getStatus());
    }
    //System.err.println(Sex.getSexForCode(1).getStatus());
  }
  @Test
  @Ignore
  public void testInsert() {
    Member member = new Member();
    member.setName("marry");
    member.setSex(Sex.FMAN);
    memberMapper.insert(member);
    System.err.println(member.getId());
  }
  @Test
  @Ignore
  public void testTypeHandler3() {
      Users user = new Users();
      user.setUsername("aeiou");
      user.setIslock(0);
      user.setSalt("");
      user.setPassword("123456");
      userMapper.insert(user);
  }
  @Test
  public void testTypeHandlerQuery() {
      Users user = userMapper.selectByPrimaryKey(6);
      System.out.println(user.getPassword());
  }
  
}
