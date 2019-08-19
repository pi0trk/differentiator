/**
 * Magic numbers differentiator
 *
 * @author Piotr K.
 **/

package pl.karnas.differentiator;

import java.util.logging.Logger;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        if (args.length != 1) {
            LOGGER.warning("required one path to a file, return");
            return;
        }

        LOGGER.info(new Differentiator().checkFile(args[0]));
    }
}
