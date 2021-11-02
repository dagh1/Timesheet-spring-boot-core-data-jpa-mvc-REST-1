package tn.esprit.spring.test;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.config.LoggingAspect;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;

import tn.esprit.spring.services.EmployeServiceImpl;
import tn.esprit.spring.services.IEmployeService;

import static org.junit.Assert.*;
import org.springframework.test.context.junit4.SpringRunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeTest {
    private static final Logger l = LogManager.getLogger(LoggingAspect.class);
    @Autowired
    IEmployeService iemployeservice;
    private int idEmploye;


    @Before
    public void createEmploye(){
        System.out.println("before insert");
        Employe employe=new Employe("Aloui","Omar","omar.aloui@esprit.tn",true, Role.INGENIEUR);
        l.info("testing adding employe");
        idEmploye=iemployeservice.ajouterEmploye(employe);
        //test
    }


    @Test
    public void testAjouterEmploye() {

        System.out.println("ajout employe");
        assertTrue(idEmploye>0);
    }

    @Test
    public void testModifierEmploye() {

        System.out.println("modifier employe");
        iemployeservice.mettreAjourEmailByEmployeId("b@b",idEmploye);
        //assertTrue(idEmploye>0);
    }





























    

    @After()
    public  void deleteEmploye(){
        System.out.println("Delete after");
        iemployeservice.deleteEmployeById(idEmploye);
    }



//    @Around("execution(* tn.esprit.spring.services.*.*(..))")
//    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
//        long start = System.currentTimeMillis();
//        Object obj = pjp.proceed();
//        long elapsedTime = System.currentTimeMillis() - start;
//        if(elapsedTime<3000)
//        l.info("Method execution time: " + elapsedTime + " milliseconds.");
//        return obj;
//    }
}
