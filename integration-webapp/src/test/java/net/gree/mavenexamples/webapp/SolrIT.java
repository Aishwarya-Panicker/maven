package net.gree.mavenexamples.webapp;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class SolrIT {

    @Test
    public void testThatSolrWorks() throws IOException, SolrServerException {

        SolrServer solrServer = new HttpSolrServer("http://localhost:8983/solr/collection1");

        solrServer.deleteByQuery("id:mikoto");
        solrServer.commit();

        SolrInputDocument solrInputDocument = new SolrInputDocument();
        solrInputDocument.addField("id", "mikoto");

        solrServer.add(solrInputDocument);
        solrServer.commit();

        SolrQuery query = new SolrQuery();
        query.setQuery("id:mikoto");

        QueryResponse response = solrServer.query(query);

        SolrDocumentList documents = response.getResults();

        assertEquals(1, documents.size());

        SolrDocument document = documents.get(0);

        assertEquals("mikoto", document.get("id"));
    }
}
