package cn.iiss.openAI.face.factory;


import cn.iiss.common.core.exception.ServiceException;
import cn.iiss.openAI.face.model.OpenAIErrorCode;

import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.ServerException;

public abstract class BaseOpenAIOperation implements OpenAIOperation {
    protected final String url;
    protected final HttpURLConnection con;
    // 连接超时(豪秒)
    private static final int CONNECT_TIMEOUT = 60000;
    // 接收数据超时(豪秒)
    private static final int RECEIVE_TIMEOUT = 60000;

    protected BaseOpenAIOperation(String url) {
        try {
            if (url.isEmpty()) {
                throw new ServerException(OpenAIErrorCode.OPEN_AI_API_URL_NOTFOUND.getName());
            }
            this.url = url;
            con = (HttpURLConnection) new URL(url).openConnection();
            con.setConnectTimeout(CONNECT_TIMEOUT);
            con.setReadTimeout(RECEIVE_TIMEOUT);
        } catch (Exception e) {
            throw new ServiceException(OpenAIErrorCode.OPEN_AI_NETWORK_SEND_ERROR.getName());
        }
    }

    protected final void init(String method, String jsonObject) {
        try {
            con.setRequestMethod(method);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer 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");
            con.setDoOutput(true);
            con.getOutputStream().write(jsonObject.getBytes());
        } catch (Exception e) {
            throw new ServiceException(OpenAIErrorCode.OPEN_AI_NETWORK_SEND_ERROR.getName());
        }
    }


}
