import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

public class GetApi {
    public static void main(String[] args) {
        Settings settings= Settings.builder()
                .put("cluster.name","elasticsearch").build();

        TransportClient client= null;
        try {
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        GetResponse response = client.prepareGet("product", "_doc", "1").get();

        Map<String, Object> source = response.getSource();

        String name = (String) source.get("name");
        String price = (String) source.get("price");
        String detail = (String) source.get("detail");
        String provider = (String) source.get("provider");

        System.out.println("name = "+name);
        System.out.println("price = "+price);
        System.out.println("detail = "+detail);
        System.out.println("provider = "+provider);
    }
}
