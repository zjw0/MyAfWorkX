package zm.bus.event;

import com.squareup.otto.Bus;

/**
 * 事件总线
 */
public class BusProvider {
    private static Bus instance;

    private BusProvider() {
    }

    public static Bus getInstance() {
        if (instance == null) {
            Class var0 = Bus.class;
            synchronized (Bus.class) {
                if (instance == null) {
                    instance = new Bus();
                }
            }
        }

        return instance;
    }
}
