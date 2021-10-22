package tn.esprit.spring.test;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.config.LoggingAspect;

import tn.esprit.spring.entities.Entreprise;

import tn.esprit.spring.services.IEntrepriseService;

import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.springframework.test.context.junit4.SpringRunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepriseTest {
    private static final Logger l = LogManager.getLogger(LoggingAspect.class);
    @Autowired
    IEntrepriseService iEntrepriseservice;
    private int idEntreprise;


    @Before
    public void createEntreprise(){
        System.out.println("before Create Entreprise");
        Entreprise entreprise=new Entreprise("dalicode","ariana");
        idEntreprise=iEntrepriseservice.ajouterEntreprise(entreprise);
      
    }


    @Test
    public void testAjouterEntreprise() {
    	
        System.out.println("ajouter entreprise");
        assertTrue(idEntreprise>0);
    }

    @Test
    public void testgetEntreprise() {

        System.out.println("get entreprise by id");
        Entreprise entreprise= iEntrepriseservice.getEntrepriseById(idEntreprise);
        assertNotNull(entreprise);
    }

    @After()
    public  void deleteEntreprise(){
        System.out.println("Delete after");
        iEntrepriseservice.deleteEntrepriseById(idEntreprise);
    }

    @Around("execution(* tn.esprit.spring.services.*.*(..))")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        if(elapsedTime<3000)
        l.info("Method execution time: " + elapsedTime + " milliseconds.");
        return obj;
    }
}
