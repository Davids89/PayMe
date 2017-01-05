package luque.david.androidchat.addDeal.events;

/**
 * Created by david on 25/10/16.
 */

public class AddDealEvents {
    boolean error = false;

    public boolean isError(){
        return error;
    }

    public void setError(boolean error){
        this.error = error;
    }
}
