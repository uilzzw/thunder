import com.thunder.core.Lightning;
import com.thunder.core.Thunder;

/**
 * Created by icepoint1999 on 3/1/16.
 */
public class App implements Lightning {
    @Override
    public void init(Thunder thunder) {
        Index index = new Index();
        thunder.addRoute("/index.do", "index", index);
    }
}
