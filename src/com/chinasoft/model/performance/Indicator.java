package com.chinasoft.model.performance;

public class Indicator {
	private int id;
	private String name;
	private IndicatorType type;
	private int weight;
	private long mid;
	private int score1;
	private int score2;
	private int totalScore;
	private String desc;
	public long getMid() {
		return mid;
	}
	public void setMid(long l) {
		this.mid = l;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public IndicatorType getType() {
		return type;
	}
	public void setType(IndicatorType type) {
		this.type = type;
	}
	public int getScore1() {
		return score1;
	}
	public void setScore1(int score1) {
		this.score1 = score1;
	}
	public int getScore2() {
		return score2;
	}
	public void setScore2(int score2) {
		this.score2 = score2;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
