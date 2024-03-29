package cn.iiss.openAI.face.factory.completions;

import cn.iiss.openAI.face.factory.BaseOpenAIOperation;
import cn.iiss.openAI.face.factory.OpenAIExecutor;
import cn.iiss.openAI.face.factory.Post;
import cn.iiss.openAI.face.model.ResponseModel;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.function.Consumer;

@Slf4j
public class Completions<T> extends BaseOpenAIOperation implements OpenAIExecutor<T>, Post<T> {
    private CompletionsRequestModel openAiRequest;

    private Consumer<T> successHook = t -> log.info("save success");
    private Consumer<? super Throwable> errorHook = e -> e.printStackTrace();

    public Completions(Consumer<CompletionsRequestModel> consumer) {
        super("https://api.openai.com/v1/completions");
        openAiRequest = new CompletionsRequestModel("");
        consumer.accept(openAiRequest);
    }

    @Override
    public Optional<T> execute() {
        T save = Try.of(() -> {
                    String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                            .reduce((a, b) -> a + b).get();
                    TypeReference<ResponseModel> typeReference = new TypeReference<>() {
                    };
                    JSONObject jsonObject = JSON.parseObject(output);
                    return jsonObject.<T>to(typeReference);
                })
                .onSuccess(successHook)
                .onFailure(errorHook).getOrNull();
        return Optional.ofNullable(save);
    }

    @Override
    public OpenAIExecutor<T> successHook(Consumer<T> consumer) {
        this.successHook = consumer;
        return this;
    }

    @Override
    public OpenAIExecutor<T> errorHook(Consumer<? super Throwable> consumer) {
        this.errorHook = consumer;
        return this;
    }

    @Override
    public OpenAIExecutor<T> post() {
        String s = JSON.toJSONString(openAiRequest);
        init("POST", s);
        return this;
    }
}
