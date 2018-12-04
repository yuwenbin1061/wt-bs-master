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

    public static List<String> getAnswers(String sentence) {

        // 答案
        List<String> answers = new ArrayList<>();

        // 找出哪些特殊方言
        for (SpecialDialectEnum dialectTarget : SpecialDialectEnum.values()) {
            // 如果句子包含特殊方言
            if (StringUtils.contains(sentence, dialectTarget.getDialect())) {
                // 判断是否是答案
                if (dialectTarget.isAnswer(sentence)) {
                    answers.add(dialectTarget.getDialect());
                }
            }
        }
        return answers;
    }

    /**
     * 得到句子分析结果
     *
     * @param sentence
     * @return
     */
    public static List<ChunkType> sentenceAnalysis(String sentence) {
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
        return resultSet.getBody().getResult().getChunkList().getChunk();
    }

    /**
     * 判断学生答案是否正确
     *
     * @param sentence     问题句子
     * @param realAnswer   学生回答
     * @param exceptAnswer 期待答案
     */
    public static boolean isDoneAnswer(String sentence, String realAnswer, String exceptAnswer) {
        // 分析句子
        List<ChunkType> chunkList = SpecialDialectHandler.sentenceAnalysis(sentence);

        // 找出答案所在chunk
        for (ChunkType chunk : chunkList) {
            String chunkString = subChunk(chunk);
            if (chunkString.contains(exceptAnswer)) {
                if (chunkString.contains(realAnswer)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 拼接chunk
     * @param chunk
     * @return
     */
    public static String subChunk(ChunkType chunk) {
        StringBuilder result = new StringBuilder();
        for (MorphemType morphem : chunk.getMorphemList().getMorphem()) {
            result.append(morphem.getSurface());
        }
        return result.toString();
    }

    /**
     * 判断：词性
     */
    public static boolean isAuxiliary(ChunkType chunk, String dialect, String nominal) {
        for (MorphemType morphem : chunk.getMorphemList().getMorphem()) {
            if (morphem.getSurface().equals(dialect) && morphem.getPOS().equals(nominal)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断DL03：せん
     */
    public static boolean isDL03(ChunkType chunk, String nominal) {
        int index = 0;
        for (MorphemType morphem : chunk.getMorphemList().getMorphem()) {
            if (morphem.getSurface().equals("せ") && morphem.getPOS().equals(nominal)) {
                if ((index + 1) < chunk.getMorphemList().getMorphem().size()
                        && chunk.getMorphemList().getMorphem().get(index + 1).getSurface().equals("ん")) {
                    return true;
                }
            }
            index++;
        }
        return false;
    }

    /**
     * 判断DL04：てや
     */
    public static boolean isDL04(ChunkType chunk, String nominal) {
        int index = 0;
        for (MorphemType morphem : chunk.getMorphemList().getMorphem()) {
            if (morphem.getSurface().equals("や") && morphem.getPOS().equals(nominal)) {
                if ((index - 1) >= 0 && chunk.getMorphemList().getMorphem().get(index - 1).getSurface().equals("て")) {
                    return true;
                }
            }
            index++;
        }
        return false;
    }

    /**
     * 判断DL05：や
     */
    public static boolean isDL05(ChunkType chunk, String nominal) {
        int index = 0;
        for (MorphemType morphem : chunk.getMorphemList().getMorphem()) {
            if (morphem.getSurface().equals("や") && morphem.getPOS().equals(nominal)) {
                if ((index - 1) >= 0
                        && (index + 1) < chunk.getMorphemList().getMorphem().size()
                        && !chunk.getMorphemList().getMorphem().get(index - 1).getSurface().equals("て")
                        && chunk.getMorphemList().getMorphem().get(index + 1).getPOS().equals("特殊")) {
                    return true;
                }
            }
            index++;
        }
        return false;
    }

    /**
     * 判断DL06：はる
     */
    public static boolean isDL06(ChunkType chunk, String nominal) {
        int index = 0;
        for (MorphemType morphem : chunk.getMorphemList().getMorphem()) {
            if (morphem.getSurface().equals("はる") && morphem.getPOS().equals(nominal)) {
                if ((index - 1) >= 0 && chunk.getMorphemList().getMorphem().get(index - 1).getPOS().equals("動詞")) {
                    return true;
                }
            }
            index++;
        }
        return false;
    }

    /**
     * 判断DL08：ん
     */
    public static boolean isDL08(ChunkType chunk, String nominal) {
        int index = 0;
        for (MorphemType morphem : chunk.getMorphemList().getMorphem()) {
            if (morphem.getSurface().equals("ん") && morphem.getPOS().equals(nominal)) {
                if ((index + 1) < chunk.getMorphemList().getMorphem().size()
                        && chunk.getMorphemList().getMorphem().get(index + 1).getPOS().equals("特殊")) {
                    return true;
                }
            }
            index++;
        }
        return false;
    }

    /**
     * 判断DL11：やん
     */
    public static boolean isDL11(ChunkType chunk, String nominal) {
        int index = 0;
        for (MorphemType morphem : chunk.getMorphemList().getMorphem()) {
            if (morphem.getSurface().equals("やん") && morphem.getPOS().equals(nominal)) {
                if ((index + 1) < chunk.getMorphemList().getMorphem().size()) {
                    if (!chunk.getMorphemList().getMorphem().get(index + 1).getSurface().equals("か")) {
                        return true;
                    }
                } else {
                    return true;
                }

            }
            index++;
        }
        return false;
    }

}
