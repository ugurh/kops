package io.ugurh.kops.dao;

import io.ugurh.kops.entity.Category;
import io.ugurh.kops.repository.CategoryRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CategoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @SuppressWarnings("unchecked")
    public void testCRUD() {
        Category rootCategory = new Category();
        rootCategory.setName("Java");
        sessionFactory.getCurrentSession().save(rootCategory);

        List<Category> categories = sessionFactory.getCurrentSession().createQuery("select category from Category category order by id desc").list();

        Category parentCategory = categories.get(0);
        Category subCategory = new Category();
        subCategory.setName("JEE");
        subCategory.setCategory(parentCategory);
        sessionFactory.getCurrentSession().save(subCategory);

        categories = sessionFactory.getCurrentSession().createQuery("select category from Category category order by id desc").list();

        assertEquals(2L, categories.size());

        rootCategory = categories.get(1);
        subCategory = categories.get(0);
        assertEquals(rootCategory.getName(), subCategory.getCategory().getName());

        subCategory.setName("Ejb 2.1");
        subCategory.setCategory(null);
        sessionFactory.getCurrentSession().merge(subCategory);

        categories = sessionFactory.getCurrentSession().createQuery("select category from Category category order by id desc").list();
        assertEquals(null, categories.get(0).getCategory());

        sessionFactory.getCurrentSession().delete(subCategory);
        categories = sessionFactory.getCurrentSession().createQuery("select category from Category category order by id desc").list();
        assertEquals(1L, categories.size());
    }
}
