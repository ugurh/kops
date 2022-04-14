package io.ugurh.kops.dao;

import io.ugurh.kops.KopsApplication;
import io.ugurh.kops.entity.Feature;
import io.ugurh.kops.entity.Item;
import io.ugurh.kops.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = KopsApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
@Transactional
public class ItemTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testCRUD() {
        Item item1 = new Item();
        item1.setName("Book");
        List<Feature> list = new ArrayList<>();
        Feature feature1 = new Feature();
        feature1.setName("Core Java");
        feature1.setItem(item1);
        Feature feature2 = new Feature();
        feature2.setName("Spring");
        feature2.setItem(item1);
        list.add(feature1);
        list.add(feature2);
        item1.setFeatures(list);

        itemRepository.save(item1);

        Item item2 = new Item();
        item2.setName("Bags");
        List<Feature> list2 = new ArrayList<>();
        Feature feature3 = new Feature();
        feature3.setName("SkyBags");
        feature3.setItem(item2);
        Feature feature4 = new Feature();
        feature4.setName("WildCraft");
        feature4.setItem(item2);
        list2.add(feature3);
        list2.add(feature4);
        item2.setFeatures(list2);

        itemRepository.save(item2);

        List<Item> items = itemRepository.findAll();
        assertEquals(2L, items.size());

        Item item = items.get(0);
        item.setName("BookList");
        itemRepository.save(item);

        List<Item> itemList = itemRepository.findAll();
        Item pItem = itemList.get(0);
        assertEquals("Bags", pItem.getName());

        itemRepository.delete(pItem);

        List<Item> ppItem = itemRepository.findAll();
        assertEquals(1L, ppItem.size());
    }
}
