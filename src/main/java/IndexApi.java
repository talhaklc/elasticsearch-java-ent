import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class IndexApi {
    public static void main(String[] args) throws UnknownHostException {
        Settings settings= Settings.builder()
                .put("cluster.name","elasticsearch").build();

        TransportClient client=new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));

        Map<String,Object> json = new HashMap<>();
        json.put("name","Apple Macbook Air");
        json.put("detail","Intel Core i5, 16G Ram, 128GB SSD");
        json.put("price","5420");
        json.put("provider","Apple Türkiye");

        IndexResponse ındexResponse = client.prepareIndex("product", "_doc", "3")
                .setSource(json, XContentType.JSON)
                .get();
    }
}
