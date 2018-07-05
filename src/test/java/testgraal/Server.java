package testgraal;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Server {

    private HazelcastInstance instance;

    @Before
    public void setup() {
        instance = Util.startServer();
    }

    @After
    public void teardown() {


        if (instance != null) {
            instance.getLifecycleService().terminate();
        }
    }

    @Test
    public void testJavaScriptEP() {
        IMap<Integer, String> myMap = instance.getMap("myMap");
        myMap.put(0, "foo");
        myMap.executeOnKey(0, new JavascriptEP("function(entry) entry.setValue('bar')"));

        assertEquals("bar", myMap.get(0));
    }

}
