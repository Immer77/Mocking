package controller;

import ordination.Laegemiddel;
import ordination.Ordination;
import ordination.Patient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ControllerTest {

    private StorageInterface mockedStorage;
    private Patient patient;

    @Test
    void mock_antalOrdinationerPrVægtPrLægemiddel() {
        // Arrange
        //Skaber vores mocks
        //Vi laver et mock af storageinterface for at bruge DIP Dependency-Insertion protocol, fordi de er på forskellige arkitekturlag
        mockedStorage = mock(StorageInterface.class);
        // Er det på samme arkitekturlag opretter vi bare ordinationsmock på klassen
        Ordination ordinationMock = mock(Ordination.class);
        Patient patientMock = mock(Patient.class);
        Controller controller = new Controller(mockedStorage);

        // Så længe der ikke bliver kaldt metoder må man godt init laegemiddel
        Laegemiddel laegemiddel1 = new Laegemiddel("Morfin",1.0,1.0,1.0,"Kg");
        Laegemiddel laegemiddel2 = new Laegemiddel("prut",1.0,1.0,1.0,"Kg");

        // Laver en liste med patienter og tilføjer vores patientmock
        List<Patient> patienter = new ArrayList<>();
        patienter.add(patientMock);
        patienter.add(patientMock);
        patienter.add(patientMock);


        // Opretter en liste af ordinationer og tilføjer et ordinationsmock
        List<Ordination> ordinationer = new ArrayList<>();
        ordinationer.add(ordinationMock);


        // Når mockedstorage.getallpatienter bliver kaldt skal den returnere den liste af mockedpatienter som vi oprettede tidligere
        when(mockedStorage.getAllPatienter()).thenReturn(patienter);

        // Når ordinationmock.getlaegemiddel bliver kaldt returnerer den
        // Returner lægemiddel1 første gang den bliver kaldt og laegemiddel2 anden gang den bliver kaldt.
        when(ordinationMock.getLaegemiddel()).thenReturn(laegemiddel1,laegemiddel2);


        // Når man skal have patientens ordinationer skal den returnere den liste af ordinationer vi har oprettet
        when(patientMock.getOrdinationer()).thenReturn(ordinationer);
        // Vi skal også bruge personens vægt så når vi kalder getvægt returner den 80 kg.
        when(patientMock.getVaegt()).thenReturn(80.0);


        //Act
        // Her acter vi og kalder vores metode som vi skal teste
        int actual = controller.antalOrdinationerPrVægtPrLægemiddel(80.0,85.0,laegemiddel2);
        // Det forventede resultat
        int expected = 2;
        //Assert
        assertEquals(expected,actual);

    }
}