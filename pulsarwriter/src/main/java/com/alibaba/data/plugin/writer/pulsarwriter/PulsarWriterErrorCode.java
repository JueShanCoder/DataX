package com.alibaba.data.plugin.writer.pulsarwriter;

import com.alibaba.datax.common.spi.ErrorCode;

public enum PulsarWriterErrorCode implements ErrorCode {

    CONFIG_ERROR("PulsarWriter-00","您配置的值不合法！"),
    CONN_PULSAR_ERROR("PulsarWriter-01","连接Pulsar服务失败！"),
    CREATE_PRODUCER_ERROR("PulsarWriter-02","创建Pulsar Producer失败！"),
    CLOSE_PULSAR_ERROR("PulsarWriter-03","关闭Pulsar组件失败！"),
    ;
    private final String code;
    private final String description;

    PulsarWriterErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }
}