package writeDB;

import exceptions.FileSizeException;

import java.io.IOException;
import java.io.InputStream;

public class ConvertSize {
    public static String CSize(InputStream inputStream) throws FileSizeException {
        String Size = null ;
        try{
            if(inputStream.available() < 50){
                Size = "S" ;
            } else  if (inputStream.available() >= 50 && inputStream.available() <= 70 ){
                Size = "M" ;
            } else {
                Size = "L" ;
            }
        } catch (IOException e){
            throw  new FileSizeException("Failed convert size ..") ;
        }
        return Size ;
    }
}
