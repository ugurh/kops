package io.ugurh.kops.dao;


import io.ugurh.kops.KopsApplication;
import io.ugurh.kops.entity.Member;
import io.ugurh.kops.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KopsApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
public class MemberTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testIsPresent() {
        Member m1 = new Member();
        m1.setName("Alexander Mahone");
        memberRepository.save(m1);

        Member m2 = new Member();
        m2.setName("Credit Member");
        memberRepository.save(m2);

        List<Member> memberList = memberRepository.findAll();
        Member member = memberList.get(0);

        List<Member> memberList2 = memberRepository.findAll();
        Member member2 = memberList2.get(0);

        member.getFriends().add(member2);

        memberRepository.save(member);

    }
}
