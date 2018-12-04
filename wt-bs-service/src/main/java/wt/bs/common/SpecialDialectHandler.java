package wt.bs.common;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import wt.bs.xml.parseResponse.ChunkType;
import wt.bs.xml.parseResponse.MorphemType;
import wt.bs.xml.parseResponse.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class SpecialDialectHandler {

    public static List<String> getAnswers(String sentence){

        // 答案
        List<String> answers = new ArrayList<>();

        // 找出哪些特殊方言
        for (SpecialDialectEnum dialectTarget : SpecialDialectEnum.values())
        {
            // 如果句子包含特殊方言
            if (StringUtils.contains(sentence, dialectTarget.getDialect())){
                // 判断是否是答案
                if (dialectTarget.isAnswer(sentence)){
                    answers.add(dialectTarget.getDialect());
                }
            }
        }
        return answers;
    }

    /**
     * 得到句子分析结果
     * @param sentence
     * @return
     */
    public static List<ChunkType> sentenceAnalysis(String sentence){
        // 请求api分析句子
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jlp.yahooapis.jp/DAService/V1/parse";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        LinkedMultiValueMap body = new LinkedMultiValueMap();
        body.add("appid", "dj00aiZpPXd6VkxoajE1dGp1OCZzPWNvbnN1bWVyc2VjcmV0Jng9YmY-");
        body.add("sentence", sentence);
        HttpEntity entity = new HttpEntity(body, headers);
        // 得到分析结果
        ResponseEntity<ResultSet> resultSet = restTemplate.exchange(url, HttpMethod.POST, entity, ResultSet.class);
        return  resultSet.getBody().getResult().getChunkList().getChunk();
    }

    /**
     * 判断：助動詞
     */
    public static boolean isAuxiliary(ChunkType chunk, String dialect){
        for (MorphemType morphem : chunk.getMorphemList().getMorphem()){
            if (morphem.getSurface().equals(dialect) && morphem.getPOS().equals("助動詞")){
                return true;
            }
        }
        return  false;
    }
}
