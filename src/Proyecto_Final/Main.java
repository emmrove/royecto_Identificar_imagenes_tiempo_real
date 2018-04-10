package Proyecto_Final;

import java.io.IOException;
import org.opencv.core.Core;

public class Main {

    public static void main(String[] args) throws IOException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        Principal init = new Principal();
        init.setVisible(true);
    }
}
