package it.morfoza;

import com.lodz360.Product;
import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by michalina on 12/08/16.
 */
public class Assertion2Test {
    @Test
    public void should() {
        long a = 4;
        long b = 4;
        Assert.assertEquals(a,b);

        float c = 4;
        float d = 3;
        float e = 1;
        Assert.assertEquals(c,d,e);
        long f = 8;
        Assert.assertNotEquals(a,f);
        Product product = new Product("m",2,2,0);
        Assert.assertNotNull(product);

        double ad = 1.2;
        double bd = 2.0;
        double cd = 0.8;
        Assert.assertEquals(ad,bd,cd);
        float x23 = (float) (0.1 + 0.1 + 0.1+ 0.1+ 0.1+ 0.1+ 0.1+ 0.1+ 0.1+ 0.1);
        double x22 = 0.1 + 0.1 + 0.1+ 0.1+ 0.1+ 0.1+ 0.1+ 0.1+ 0.1+ 0.1;
        System.out.println(x23);
        System.out.println(x22);

        String as = "gggg";
        assertThat(as).isEmpty();



    }


}
