package it.lovacino.iltrotto;

import it.lovacino.iltrotto.service.SorteggiService;

import org.apache.log4j.Logger;

import java.sql.Date;
import java.time.LocalDate;
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dataCorsa = args!=null && args.length>0 ? String.valueOf(args[0]) : formatter.format(LocalDate.now());;
            SorteggiService.getInstance().batchSorteggi(dataCorsa);
            logger.info("[main] - end batch at " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + " - [END OK] --- [NO RETURN]");
        }catch (Exception e){
            logger.error("[main] - error batch at " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + " - [END KO] --- [ERROR]: ", e);
            e.printStackTrace();
        }
    }

}
