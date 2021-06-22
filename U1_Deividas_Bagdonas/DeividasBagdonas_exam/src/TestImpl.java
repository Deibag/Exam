import lt.vtmc.exam.TransportManager;
import lt.vtmc.exam.test.BaseTest;

public class TestImpl extends BaseTest {

    @Override
    protected TransportManager createTransportManager() {
        return new createTransportManagerImpl();
    }
}
