import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.MissingResourceException;

public class I18NManagerTest {

    I18NManager i18n;

    @Before
    public void setUp() throws Exception{
        this.i18n=I18NManager.getInstance();
    }

    //Anem a comprobar certs parametres per diferentes claus que introduim
    @Test
    public void testGetText() throws Exception{

        String message = I18NManager.getInstance().getText("es","l3");
        Assert.assertEquals("Usuario creado en la base de datos: Héctor",message);

    }

    //Fem un altre per esperar que no estigui el llenguatge i "exploti" la consola
    @Test(expected = MissingResourceException.class)
    public void testGetTextBundleNotFound() throws Exception{

        String errorMessage= I18NManager.getInstance().getText("fr","l3");

    }

}
