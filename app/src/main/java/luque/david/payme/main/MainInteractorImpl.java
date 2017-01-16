package luque.david.payme.main;

/**
 * Created by david on 10/1/17.
 */

public class MainInteractorImpl implements MainInteractor {

    private MainRepository repository;

    public MainInteractorImpl() {
        repository = new MainRepositoryImpl();
    }

    @Override
    public void singOff() {
        repository.singOff();
    }
}
