package it.lovacino.iltrotto;

import it.lovacino.iltrotto.service.SorteggiService;

import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Batch che invia mail sorteggi
 *
 */
public class App 
{

    private static final Logger logger = Logger.getRootLogger();
//    private static final Logger logger = Logger.getLogger(App.class);
    
    

    public static void main( String[] args )
    {
        try {
            logger.info("[main] - start batch at " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + " - [START] --- [INPUT]: " + args );
            String dataCorsa = String.valueOf(args[1]);
            SorteggiService sorteggioService = SorteggiService.getInstance();
            sorteggioService.batchSorteggi(dataCorsa);
            logger.info("[main] - end batch at " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + " - [END OK] --- [NO RETURN]");
        }catch (Exception e){
            logger.error("[main] - error batch at " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + " - [END KO] --- [ERROR]: ", e);
            e.printStackTrace();
        }
    }

}
