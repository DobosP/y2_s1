package model;


import ExceptionHandling.MyException;

import java.io.IOException;

public interface IntStatement {


    ProgState execute(ProgState state) throws MyException;

}
