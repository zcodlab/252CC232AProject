package uni.aed.tda.arraylistTDA.fortunecookie.inicio;

import java.util.Random;
import uni.aed.tda.listTDA.ListTDA;

public class FortuneCookieManager {
    private ListTDA<String> fortunas;    

    public FortuneCookieManager() {
        FortuneCookieFile fortuneCookieFile=new FortuneCookieFile();
        fortunas=fortuneCookieFile.getLista();        
    }
    
    public String nexFortune() {
        Random random = new Random();
        int index = random.nextInt(fortunas.size());
        return fortunas.get(index);
        
    }
    
    @Override
    public String toString() {
        return "FortuneCookieManager{" + "fortunas=" + fortunas + '}';
    }
    
}
