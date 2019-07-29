package pl.karnas.differentiator;

import java.util.logging.Logger;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args){

        LOGGER.info(new Differentiator().checkFile(args[0]));


    }
}
