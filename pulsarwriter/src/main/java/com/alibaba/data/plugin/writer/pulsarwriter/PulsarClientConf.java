package com.alibaba.data.plugin.writer.pulsarwriter;

import com.alibaba.datax.common.exception.DataXException;
import com.alibaba.datax.common.util.Configuration;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.alibaba.data.plugin.writer.pulsarwriter.PulsarWriterErrorCode.*;

public class PulsarClientConf {
    private static final Logger log = LoggerFactory.getLogger(PulsarClientConf.class);

    private PulsarClient pulsarClient;

    private Producer producer;

    public PulsarClient getClient() {
        return pulsarClient;
    }

    public void createPulsarClient(Configuration configuration) {
        String serviceUrl = configuration.getNecessaryValue("serviceUrl", SERVICE_URL_ERROR);
        try {
            pulsarClient =  PulsarClient.builder()
                    .serviceUrl(serviceUrl)
                    .build();
        } catch (PulsarClientException e) {
            throw DataXException.asDataXException(CONN_PULSAR_ERROR,e.toString());
        }
    }

    public void closePulsarComponent() {
        if (pulsarClient != null){
            try {
                pulsarClient.close();
            } catch (PulsarClientException e) {
                throw DataXException.asDataXException(CLOSE_PULSAR_ERROR,e.toString());
            }
        }
    }

    public void closePulsarComponentAsync(){

    }


}