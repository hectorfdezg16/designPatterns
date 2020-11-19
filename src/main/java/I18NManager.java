import java.util.HashMap;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

//SINGLETON
//constructor privat
//exercici d'internacionalització de textos(I18N)
public class I18NManager {

    //implementar factoria de comandes que al mateix temps serà singleton

    //crear variable logger
    final static Logger logger = Logger.getLogger(I18NManager.class.getName());

    //segon punt / referencia privada, estatica a la unica isntancia d'aquella clase / enrutador
    //primera vegada es null
    private static I18NManager instance;

    //quart punt gestió de dades internes
    //gestionarem textos / contenidor array hash,etc.
    //data[] certa informació que hem de gestionar
    HashMap<String, ResourceBundle> data;

    //primer punt / constructor privat
    private I18NManager(){
        this.data= new HashMap<String, ResourceBundle>();
    }

    //tercer punt / metòde accés públic static / getter proporciona l'única instància / retorna referència de l'única instància
    public static I18NManager getInstance(){
        //jo soc l'únic que pot crear aquesta instància / l'única isntància
        if(instance==null){
            logger.info("Nova instància I18N!");
            //aquí ja no serà null
            instance=new I18NManager();
        }
        return instance;
    }

    /*aquí posarem operacions de lectura i escriptura sobre aquestes dades
    metodès no estàtics
    aquí implementarem funció donar llenguatge especificant clau
     */
    //quan algú li demana per un valor associat a una clau d'un determinat llenguatge
    public String getText(String language, String key){
        logger.info("language: "+language+" key: "+key);
        String result=null;
        //data es una estructura de dades que actua com una cache
        //tens el recurs associat a aquest idioma?
        ResourceBundle rb=this.data.get(language);
        //resposta NO
        if(rb==null){
            logger.info("Bundle '"+language+".properties' no existeix en cache de textos");
            rb=ResourceBundle.getBundle(language);
            //dins del Hash guardem el valor obtingut associat a una determinada clau
            this.data.put(language, rb);
            logger.info("S'afegeix bundle a cache de textos");
        }
        else logger.warn("Bundle '"+language+ ".properties' SI existeix en cache de textos");
        //quin es el resultat associat a aquesta clau
        result=rb.getString(key);
        logger.info("resultat: "+result);
        return result;
    }


    public static void main(String[] args) {
        String i1=I18NManager.getInstance().getText("ca","l2");
        String i2=I18NManager.getInstance().getText("en","l4");

        logger.info(i1);
        logger.info(i2);
        //System.out.println(i1);
        //System.out.println(i2);

    }





}
