package wt.bs.common;

import wt.bs.xml.parseResponse.ChunkType;

import java.util.List;

public enum SpecialDialectEnum {
    DL01("へん", "ない（例文：明日東京に行かへん）", "助動詞") {
        @Override
        public Boolean isAnswer(String sentence) {
            List<ChunkType> chunkList = SpecialDialectHandler.sentenceAnalysis(sentence);

            for (ChunkType chunk : chunkList) {
                if (SpecialDialectHandler.isAuxiliary(chunk, this.getDialect(), this.getNominal())) {
                    return true;
                }
            }
            return false;
        }
    },
    DL02("ひん", "ない（例文：なかなか成績が伸びへん）", "助動詞") {
        @Override
        public Boolean isAnswer(String sentence) {
            List<ChunkType> chunkList = SpecialDialectHandler.sentenceAnalysis(sentence);

            for (ChunkType chunk : chunkList) {
                if (SpecialDialectHandler.isAuxiliary(chunk, this.getDialect(), this.getNominal())) {
                    return true;
                }
            }
            return false;
        }
    },
    DL03("せん", "しない（例文：掃除せんといけない）", "助動詞") {
        @Override
        public Boolean isAnswer(String sentence) {
            List<ChunkType> chunkList = SpecialDialectHandler.sentenceAnalysis(sentence);

            for (ChunkType chunk : chunkList) {
                if (SpecialDialectHandler.isDL03(chunk, this.getNominal())) {
                    return true;
                }
            }
            return false;
        }
    },
    DL04("てや", "てね、てよ（例文：見ててや）", "終助詞") {
        @Override
        public Boolean isAnswer(String sentence) {
            List<ChunkType> chunkList = SpecialDialectHandler.sentenceAnalysis(sentence);

            for (ChunkType chunk : chunkList) {
                if (SpecialDialectHandler.isDL04(chunk, this.getNominal())) {
                    return true;
                }
            }
            return false;
        }
    },
    DL05("や", "だ（例文：明日東京に行くんや）", "終助詞") {
        @Override
        public Boolean isAnswer(String sentence) {
            List<ChunkType> chunkList = SpecialDialectHandler.sentenceAnalysis(sentence);

            for (ChunkType chunk : chunkList) {
                if (SpecialDialectHandler.isDL05(chunk, this.getNominal())) {
                    return true;
                }
            }
            return false;
        }
    },
    DL06("はる", "なさる（例文：先生が食べはる）", "動詞") {
        @Override
        public Boolean isAnswer(String sentence) {
            List<ChunkType> chunkList = SpecialDialectHandler.sentenceAnalysis(sentence);

            for (ChunkType chunk : chunkList) {
                if (SpecialDialectHandler.isDL06(chunk, this.getNominal())) {
                    return true;
                }
            }
            return false;
        }
    },
    DL07("ねん", "のだ（例文：めっちゃ楽しいねん）", "終助詞") {
        @Override
        public Boolean isAnswer(String sentence) {
            List<ChunkType> chunkList = SpecialDialectHandler.sentenceAnalysis(sentence);

            for (ChunkType chunk : chunkList) {
                if (SpecialDialectHandler.isAuxiliary(chunk, this.getDialect(), this.getNominal())) {
                    return true;
                }
            }
            return false;
        }
    },
    DL08("ん", "ない（例文：分からん）", "助動詞") {
        @Override
        public Boolean isAnswer(String sentence) {
            List<ChunkType> chunkList = SpecialDialectHandler.sentenceAnalysis(sentence);

            for (ChunkType chunk : chunkList) {
                if (SpecialDialectHandler.isAuxiliary(chunk, this.getDialect(), this.getNominal())) {
                    return true;
                }
            }
            return false;
        }
    },
    DL09("とる", "ている（例文：電気がついとる）", "助動詞") {
        @Override
        public Boolean isAnswer(String sentence) {
            List<ChunkType> chunkList = SpecialDialectHandler.sentenceAnalysis(sentence);

            for (ChunkType chunk : chunkList) {
                if (SpecialDialectHandler.isAuxiliary(chunk, this.getDialect(), this.getNominal())) {
                    return true;
                }
            }
            return false;
        }
    },
    DL10("どる", "でいる（例文：雨が止んどる）", "助動詞") {
        @Override
        public Boolean isAnswer(String sentence) {
            List<ChunkType> chunkList = SpecialDialectHandler.sentenceAnalysis(sentence);

            for (ChunkType chunk : chunkList) {
                if (SpecialDialectHandler.isAuxiliary(chunk, this.getDialect(), this.getNominal())) {
                    return true;
                }
            }
            return false;
        }
    },
    DL11("やん", "じゃん（例文：べっぴんやん）", "助動詞") {
        @Override
        public Boolean isAnswer(String sentence) {
            List<ChunkType> chunkList = SpecialDialectHandler.sentenceAnalysis(sentence);

            for (ChunkType chunk : chunkList) {
                if (SpecialDialectHandler.isDL11(chunk, this.getNominal())) {
                    return true;
                }
            }
            return false;
        }
    },
    DL12("やんか", "じゃないか（例文：ええやんか）", "終助詞") {
        @Override
        public Boolean isAnswer(String sentence) {
            List<ChunkType> chunkList = SpecialDialectHandler.sentenceAnalysis(sentence);

            for (ChunkType chunk : chunkList) {
                if (SpecialDialectHandler.isAuxiliary(chunk, this.getDialect(), this.getNominal())) {
                    return true;
                }
            }
            return false;
        }
    };

    private String dialect;
    private String translation;
    private String nominal;

    SpecialDialectEnum(String dialect, String translation, String nominal) {
        this.dialect = dialect;
        this.translation = translation;
        this.nominal = nominal;
    }

    public String getDialect() {
        return this.dialect;
    }

    public String getTranslation() {
        return this.translation;
    }

    public String getNominal() {
        return this.nominal;
    }

    public abstract Boolean isAnswer(String sentence);
}
