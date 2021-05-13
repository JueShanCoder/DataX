package com.alibaba.data.plugin.writer.pulsarwriter;

import com.alibaba.datax.common.plugin.RecordReceiver;
import com.alibaba.datax.common.spi.Writer;
import com.alibaba.datax.common.util.Configuration;
import org.apache.pulsar.client.api.PulsarClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PulsarWriter extends Writer {

    public static class Job extends Writer.Job {
        private static final Logger log = LoggerFactory.getLogger(Job.class);

        private Configuration conf = null;

        private PulsarClient pulsarClient = null;

        @Override
        public void init() {
            this.conf = super.getPluginJobConf();
        }

        @Override
        public void prepare() {
            // 初始化PulsarClient
            PulsarClientConf pulsarClient = new PulsarClientConf();
            pulsarClient.createPulsarClient(conf);
            //TODO 判断 Topic 是否存在，namespace 是否存在，如果不存在则创建
        }

        @Override
        public void destroy() {

        }

        @Override
        public List<Configuration> split(int mandatoryNumber) {
            return null;
        }
    }

    public static class Task extends Writer.Task {

        private static final Logger log = LoggerFactory.getLogger(Job.class);

        private Configuration conf = null;

        @Override
        public void init() {
            this.conf = super.getPluginJobConf();
        }

        @Override
        public void prepare() {
            // 初始化PulsarClient
            PulsarClientConf pulsarClient = new PulsarClientConf();
            pulsarClient.createPulsarClient(conf);
        }

        @Override
        public void destroy() {

        }

        @Override
        public void startWrite(RecordReceiver lineReceiver) {

        }
    }
}