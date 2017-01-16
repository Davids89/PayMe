package luque.david.payme.main;

import luque.david.payme.domain.FirebaseHelper;

/**
 * Created by david on 10/1/17.
 */

public class MainRepositoryImpl implements MainRepository {

    FirebaseHelper helper;

    public MainRepositoryImpl() {
        helper = FirebaseHelper.getInstance();
    }

    @Override
    public void singOff() {
        helper.SignOff();
    }
}
