import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

public class SearchApi {
    public static void main(String[] args) throws UnknownHostException {
        Settings settings= Settings.builder()
                .put("cluster.name","elasticsearch").build();

        TransportClient client=new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));

        SearchResponse response = client.prepareSearch("product")
                .setTypes("_doc")
                .setQuery(QueryBuilders.matchQuery("detail", "Intel"))
                .get();

        SearchHit[] hits = response.getHits().getHits();
        for(SearchHit hit : hits){
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(sourceAsMap);
        }
    }
}
