package ru.javaops.bootjava;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestService {
    public static final String EXC_SIGN = "exc sing";
    @GetMapping
    public void test(){
       // try {
            throw new SingException(EXC_SIGN);
       // }
        /*catch (SingException e){
            System.out.println(e.getMessage());
           e.printStackTrace();
        }*/

    }
}
