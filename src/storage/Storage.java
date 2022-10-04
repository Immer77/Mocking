package storage;

import java.util.List;

import controller.StorageInterface;
import ordination.Patient;

public class Storage implements StorageInterface {

    /**
     * Returnerer en liste med alle gemte patienter
     */
    @Override
    public List<Patient> getAllPatienter() {
        throw new UnsupportedOperationException();
    }
}
