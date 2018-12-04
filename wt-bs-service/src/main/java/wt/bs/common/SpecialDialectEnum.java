package wt.bs.common;

import wt.bs.xml.parseResponse.ChunkType;

import java.util.List;

public enum SpecialDialectEnum {
    DL01("へん", "ない（例文：明日東京に行かへん）", "助動詞"){
        @Override
        public Boolean isAnswer(String sentence) {
            List<ChunkType> chunkList = SpecialDialectHandler.sentenceAnalysis(sentence);

            for (ChunkType chunk : chunkList){
                if (SpecialDialectHandler.isAuxiliary(chunk, this.getDialect(), this.getNominal())){
                    return true;
                }
            }
            return false;
        }
    },
    DL02("ひん", "ない（例文：なかなか成績が伸びへん）", "助動詞"){
        @Override
        public Boolean isAnswer(String sentence) {
            List<ChunkType> chunkList = SpecialDialectHandler.sentenceAnalysis(sentence);

            for (ChunkType chunk : chunkList){
                if (SpecialDialectHandler.isAuxiliary(chunk, this.getDialect(), this.getNominal())){
                    return true;
                }
            }
            return false;
        }
    },
    DL03("せん", "しない（例文：掃除せんといけない）", "助動詞"){
        @Override
        public Boolean isAnswer(String sentence) {
            List<ChunkType> chunkList = SpecialDialectHandler.sentenceAnalysis(sentence);

            for (ChunkType chunk : chunkList){
                if (SpecialDialectHandler.isDL03(chunk)){
                    return true;
                }
            }
            return false;
        }
    },
    DL04("てや", "てね、てよ（例文：見ててや）", "終助詞"){
        @Override
        public Boolean isAnswer(String sentence) {
            List<ChunkType> chunkList = SpecialDialectHandler.sentenceAnalysis(sentence);

            for (ChunkType chunk : chunkList){
                if (SpecialDialectHandler.isDL04(chunk)){
                    return true;
                }
            }
            return false;
        }
    },
    DL05("や", "だ（例文：そうや）", "終助詞"){
        @Override
        public Boolean isAnswer(String sentence) {
            List<ChunkType> chunkList = SpecialDialectHandler.sentenceAnalysis(sentence);

            for (ChunkType chunk : chunkList){
                if (SpecialDialectHandler.isDL05(chunk)){
                    return true;
                }
            }
            return false;
        }
    },
    DL06("はる","尊敬の表現（例文：先生が食べはる）", "動詞") {
        @Override
        public Boolean isAnswer(String sentence) {
            List<ChunkType> chunkList = SpecialDialectHandler.sentenceAnalysis(sentence);

            for (ChunkType chunk : chunkList){
                if (SpecialDialectHandler.isDL06(chunk)){
                    return true;
                }
            }
            return false;
        }
    };

    private String dialect;
    private String translation;
    private String nominal;
    SpecialDialectEnum(String dialect, String translation, String nominal){
        this.dialect = dialect;
        this.translation = translation;
        this.nominal = nominal;
    }

    public String getDialect(){
        return this.dialect;
    }

    public  String getTranslation(){
        return this.translation;
    }

    public String getNominal(){
        return this.nominal;
    }

    public abstract Boolean isAnswer(String sentence);
}
