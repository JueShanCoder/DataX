package com.alibaba.data.plugin.writer.pulsarwriter;

import com.alibaba.datax.common.exception.DataXException;
import com.alibaba.datax.common.util.Configuration;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static com.alibaba.data.plugin.writer.pulsarwriter.PulsarWriterErrorCode.*;

public class PulsarClientConf {
    private static final Logger log = LoggerFactory.getLogger(PulsarClientConf.class);

    private PulsarClient pulsarClient;

    private Producer<byte[]> producer;

    public PulsarClient getClient() {
        return pulsarClient;
    }

    public Producer<byte[]> getProducer() {
        return producer;
    }

    public void createPulsarClient(Configuration configuration) {
        String serviceUrl = configuration.getNecessaryValue(Key.serviceUrl, CONFIG_ERROR);
        try {
            pulsarClient =  PulsarClient.builder()
                    .serviceUrl(serviceUrl)
                    .build();
        } catch (PulsarClientException e) {
            throw DataXException.asDataXException(CONN_PULSAR_ERROR,e.toString());
        }
    }

    public void createPulsarProducer(Configuration configuration) {
        String topicName = configuration.getNecessaryValue(Key.topicName, CONFIG_ERROR);
        String producerName = configuration.getNecessaryValue(Key.producerName, CONFIG_ERROR);
        long sendTimeoutMs = Long.parseLong(configuration.getNecessaryValue(Key.sendTimeoutMs, CONFIG_ERROR));
        Boolean blockIfQueueFull = configuration.getNecessaryBool(Key.blockIfQueueFull, CONFIG_ERROR);
        try {
            producer = pulsarClient.newProducer()
                    .topic(topicName)
                    .producerName(producerName)
                    .blockIfQueueFull(blockIfQueueFull)
                    .sendTimeout(Math.toIntExact(sendTimeoutMs), TimeUnit.SECONDS)
                    .create();
        } catch (PulsarClientException e) {
            throw DataXException.asDataXException(CREATE_PRODUCER_ERROR,e.toString());
        }
    }

    public void closePulsarComponent() {
        try {
            if (pulsarClient != null)
                pulsarClient.close();

            if (producer != null)
                producer.close();
        } catch (PulsarClientException e) {
            throw DataXException.asDataXException(CLOSE_PULSAR_ERROR,e.toString());
        }
    }
}