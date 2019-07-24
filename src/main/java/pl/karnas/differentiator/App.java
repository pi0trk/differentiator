package pl.karnas.differentiator;

import java.util.logging.Logger;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args){

        String filePath = "E:\\egit\\differentiator\\src\\main\\resources\\file\\textDoc.txt"; //calculator.gif
        LOGGER.info(new Differentiator().checkFile(filePath));


    }
}
