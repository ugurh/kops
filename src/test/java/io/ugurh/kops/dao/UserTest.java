package io.ugurh.kops.dao;

import io.ugurh.kops.KopsApplication;
import io.ugurh.kops.entity.Group;
import io.ugurh.kops.entity.User;
import io.ugurh.kops.repository.GroupRepository;
import io.ugurh.kops.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KopsApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
@Transactional
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void testCRUD() {
        User u1 = new User();
        u1.setName("A");

        User u2 = new User();
        u2.setName("B");

        userRepository.save(u1);
        userRepository.save(u2);

        u1.setName("C");
        userRepository.save(u1);

        List<User> list = userRepository.findAll();
        assertEquals(2L, list.size());

        userRepository.delete(u1);

        List<User> list2 = userRepository.findAll();
        assertEquals(1L, list2.size());

        Group g1 = new Group();
        g1.setName("A");

        Group g2 = new Group();
        g2.setName("B");

        groupRepository.save(g1);
        groupRepository.save(g2);

        User u3 = list2.get(0);
        u3.getGroups().add(g1);
        u3.getGroups().add(g2);
        userRepository.save(u3);

        List<User> list3 = userRepository.findAll();
        assertEquals(1L, list3.size());
        assertEquals(2L, list3.get(0).getGroups().size());
    }

}
