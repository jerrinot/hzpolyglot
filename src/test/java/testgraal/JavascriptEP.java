package testgraal;

import com.hazelcast.map.AbstractEntryProcessor;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import java.io.IOException;
import java.util.Map;

public final class JavascriptEP extends AbstractEntryProcessor<Integer, String> implements IdentifiedDataSerializable {
    private String source;

    JavascriptEP() {

    }

    JavascriptEP(String source) {
        this.source = source;
    }

    public Object process(Map.Entry<Integer, String> entry) {
        Context context = Context.newBuilder("js").allowAllAccess(true).build();
        Value function = context.eval("js", source);
        Value result = function.execute(entry);
        return result.as(Object.class);
    }

    public int getFactoryId() {
        return EPFactory.F_ID;
    }

    public int getId() {
        return EPFactory.JavascriptEP_ID;
    }

    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeUTF(source);
    }

    public void readData(ObjectDataInput in) throws IOException {
        source = in.readUTF();
    }
}
