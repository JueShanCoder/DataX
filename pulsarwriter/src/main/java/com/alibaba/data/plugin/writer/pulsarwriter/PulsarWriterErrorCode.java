package com.alibaba.data.plugin.writer.pulsarwriter;

import com.alibaba.datax.common.spi.ErrorCode;

public enum PulsarWriterErrorCode implements ErrorCode {

    SERVICE_URL_ERROR("PulsarWriter-00","Pulsar服务地址配置错误！"),
    CONN_PULSAR_ERROR("PulsarWriter-01","连接Pulsar服务失败！"),
    CLOSE_PULSAR_ERROR("PulsarWriter-02","关闭Pulsar组件失败！"),
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