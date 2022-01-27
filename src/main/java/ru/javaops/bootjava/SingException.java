package ru.javaops.bootjava;

public class SingException extends RuntimeException {
    public SingException(String msg){
        super(msg+" ошибка подписи");
    }
}
