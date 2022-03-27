package it.lovacino.iltrotto;

import it.lovacino.iltrotto.service.SorteggiService;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

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
            String dataCorsa = args != null && args.length > 0 ? String.valueOf(args[0]) : new LocalDate().toString(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            SorteggiService.getInstance().batchSorteggi(dataCorsa);
            logger.info("[main] - end batch at " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + " - [END OK] --- [NO RETURN]");
        }catch (Exception e){
            logger.error("[main] - error batch at " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + " - [END KO] --- [ERROR]: ", e);
            e.printStackTrace();
        }
    }

}
