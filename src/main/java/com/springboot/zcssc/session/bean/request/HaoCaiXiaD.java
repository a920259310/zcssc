package com.springboot.zcssc.session.bean.request;

import java.util.List;

public class HaoCaiXiaD {

    /**
     * lottery : BJPK10    //游戏类型
     * drawNumber : 722839 //游戏编号
     * 下注类容
     * bets : [{"game":"B1","contents":"1","amount":1,"odds":9.95},{"game":"B1","contents":"2","amount":1,"odds":9.95},{"game":"B1","contents":"3","amount":1,"odds":9.95},{"game":"B1","contents":"4","amount":1,"odds":9.95},{"game":"B1","contents":"5","amount":1,"odds":9.95},{"game":"B2","contents":"1","amount":1,"odds":9.95},{"game":"B3","contents":"1","amount":1,"odds":9.95},{"game":"B4","contents":"1","amount":1,"odds":9.95},{"game":"B5","contents":"1","amount":1,"odds":9.95}]
     * uniqueId : 0
     */

    private String lottery;
    private String drawNumber;
    private int uniqueId;
    private List<BetsBean> bets;

    public String getLottery() {
        return lottery;
    }

    public void setLottery(String lottery) {
        this.lottery = lottery;
    }

    public String getDrawNumber() {
        return drawNumber;
    }

    public void setDrawNumber(String drawNumber) {
        this.drawNumber = drawNumber;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public List<BetsBean> getBets() {
        return bets;
    }

    public void setBets(List<BetsBean> bets) {
        this.bets = bets;
    }

    public static class BetsBean {
        /**
         * game : B1
         * contents : 1
         * amount : 1
         * odds : 9.95
         */

        private String game;
        private String contents;
        private int amount;
        private double odds;

        public String getGame() {
            return game;
        }

        public void setGame(String game) {
            this.game = game;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public double getOdds() {
            return odds;
        }

        public void setOdds(double odds) {
            this.odds = odds;
        }
    }
}
