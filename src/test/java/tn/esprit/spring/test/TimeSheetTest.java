package tn.esprit.spring.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.services.EntrepriseServiceImpl;
import tn.esprit.spring.services.TimesheetServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeSheetTest {

	@Autowired
	MissionRepository missionRepository;
	@Autowired
	EntrepriseServiceImpl entrepriseService;
	@Autowired
	TimesheetServiceImpl timesheetService;
	public static int dep,mis;
	
	@Before
	public void affecterParametre(){
		System.out.println("here");
		// Arrange	
		Departement dep1 = new Departement("dev");
		Mission mission1 = new Mission("mission1");
		// Act 
		dep = entrepriseService.ajouterDepartement(dep1);
		mis = timesheetService.ajouterMission(mission1);
		System.out.println(dep1.getId());
		System.out.println(mis);
	}
	
	
	@Test
	public void affecterMissionADepTest(){
		// Assert
		assertNotNull("departement non affecter",timesheetService.affecterMissionADepartement(mis,dep));
	}
	
	@After
	public void deleteData(){
		System.out.println("here2");
		// delete data
		timesheetService.deleteMissionById(mis);
		entrepriseService.deleteDepartementById(dep);
	}

}
