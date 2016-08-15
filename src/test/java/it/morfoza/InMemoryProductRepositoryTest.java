package it.morfoza;


import com.lodz360.InMemoryProductRepository;
import com.lodz360.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by michalina on 12/08/16.
 */
public class InMemoryProductRepositoryTest {
    @Test
    public void should(){
        Assert.assertTrue(true);
        Assert.assertFalse(false);
            int[] tab1 =new int[3];
        int[] tab2 =new int[3];
        InMemoryProductRepository inMemoryProductRepository = new InMemoryProductRepository();
        List<Product> productList = inMemoryProductRepository.getAllProducts();

        List<Product> productList2 = new ArrayList<>();
        productList2.add(new Product("Milk", 3.4, 3, 0));
        productList2.add(new Product("Egg", 13, 11, 0));
        productList2.add(new Product("Cereal", 8, 0.4, 84));
        productList2.add(new Product("Butter", 0.9, 89, 0.1));

        Assert.assertEquals(productList, productList2);



    }
    @Test
    public void shouldReturnNotEmptyList(){
       //Given
        InMemoryProductRepository inMemoryProductRepository = new InMemoryProductRepository();
        //When
        List<Product> productList = inMemoryProductRepository.getAllProducts();
        //Then
        assertThat(productList).isNotEmpty();
    }

    @Test
    public void shouldCheckIfThereIsThisObjectInList(){
        //Given
        InMemoryProductRepository inMemoryProductRepository = new InMemoryProductRepository();
        List<Product> productList = inMemoryProductRepository.getAllProducts();
        Product product = new Product("Milk", 3.4, 3, 0);

        assertThat(product).isIn(productList);

    }

}
