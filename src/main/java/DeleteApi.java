import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DeleteApi {
    public static void main(String[] args) throws UnknownHostException {
        Settings settings= Settings.builder()
                .put("cluster.name","elasticsearch").build();

        TransportClient client=new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));

        //Delete Api By ID
        DeleteResponse deleteResponse = client.prepareDelete("product", "_doc", "1").get();

        System.out.println(deleteResponse.getId());

        //Delete Api By Query
        BulkByScrollResponse response =
                DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                        .filter(QueryBuilders.matchQuery("name", "Apple"))
                        .source("product")
                        .get();
        System.out.println(response.getDeleted());
    }
}
