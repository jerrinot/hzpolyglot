package testgraal;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public final class Util {
    private Util() {
    }

    public static HazelcastInstance startServer() {
        Config config = new Config();
        config.getSerializationConfig().addDataSerializableFactoryClass(EPFactory.F_ID, EPFactory.class);
        return Hazelcast.newHazelcastInstance(config);
    }
}
