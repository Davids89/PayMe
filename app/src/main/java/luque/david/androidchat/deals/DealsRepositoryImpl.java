package luque.david.androidchat.deals;
import luque.david.androidchat.domain.FirebaseHelper;

/**
 * Created by david on 30/9/16.
 */

public class DealsRepositoryImpl implements DealsRepository {

    private FirebaseHelper helper;

    public DealsRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
    }

    @Override
    public void signOff() {
        helper.SignOff();
    }

}
