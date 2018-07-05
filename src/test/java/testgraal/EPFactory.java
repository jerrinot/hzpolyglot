package testgraal;

import com.hazelcast.nio.serialization.DataSerializableFactory;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

public class EPFactory implements DataSerializableFactory {
    public static final int F_ID = 1;

    public static final int JavascriptEP_ID = 1;

    public IdentifiedDataSerializable create(int typeId) {
        switch (typeId) {
            case JavascriptEP_ID:
                return new JavascriptEP();
            default:
                throw new AssertionError();
        }
    }
}
